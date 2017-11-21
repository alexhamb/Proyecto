package com.example.asus.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

/**
 * Created by ASUS on 18/11/2017.
 */

public class Modificar_paciente extends AppCompatActivity {

    private ImageView mfotoInicial2;
    private EditText mtxtCedulap;
    private EditText mtxtNombrep;
    private EditText mtxtEdadp;
    private EditText mtxtVmp;
    private Intent i;
    private Bundle b;
    private StorageReference storageReference;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcp);

        mfotoInicial2 = (ImageView)findViewById(R.id.mfotoInicial2);
        mtxtCedulap = (EditText)findViewById(R.id.mtxtCedulap);
        mtxtNombrep = (EditText)findViewById(R.id.mtxtNombrep);
        mtxtEdadp = (EditText)findViewById(R.id.mtxtEdadp);
        mtxtVmp = (EditText)findViewById(R.id.mtxtVmp);
        storageReference = FirebaseStorage.getInstance().getReference();


        i = getIntent();
        b = i.getBundleExtra("datos");

        mtxtCedulap.setText(b.getString("cedula"));
        mtxtNombrep.setText(b.getString("nombre"));
        mtxtEdadp.setText(b.getString("edad"));
        mtxtVmp.setText(b.getString("valoracion_medica"));

        storageReference.child(b.getString("foto"))
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Modificar_paciente.this).load(uri).into(mfotoInicial2);
            }
        });


    }

    public void modificar(View v){
        String cedula =  mtxtCedulap.getText().toString();
        String nombre = mtxtNombrep.getText().toString();
        String edad = mtxtEdadp.getText().toString();
        String valoracionm = mtxtVmp.getText().toString();
        Pacientes p = new Pacientes(b.getString("id"),b.getString("foto"),cedula,
                nombre,edad, valoracionm);

        if(cedula.equals(b.getString("cedula"))){
            p.ModificarP();
            if(uri!=null)subir_foto(b.getString("foto"));
            Snackbar.make(v, R.string.msgs_paciente_modificada_exitosamente,Snackbar.LENGTH_SHORT)
                    .setAction("action",null).show();


        }else{
            if(Metodos.existencia_paciente(Datos.obtenerPacientes(),cedula)){
                mtxtCedulap.setError(getString(R.string.msg_error_cedula_existente));

                mtxtCedulap.requestFocus();
            }else{
                p.ModificarP();
                if(uri!=null)subir_foto(b.getString("foto"));
                Snackbar.make(v, R.string.msgs_paciente_modificada_exitosamente,Snackbar.LENGTH_SHORT)
                        .setAction("action",null).show();

            }
        }
    }

    public void eliminar(View v){
        String p, n;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.eliminar));
        builder.setMessage(R.string.msg_ventana_eliminar);
        p=getString(R.string.opc_positivo);
        n=getString(R.string.opc_negativo);

        builder.setPositiveButton(p , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Pacientes p = new Pacientes(b.getString("id"));
                p.EliminarP();
                onBackPressed();
            }
        });

        builder.setNegativeButton(n , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,getString(R.string.mensaje_escoja_imagen)),1);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(Modificar_paciente.this,MainActivity.class);
        startActivity(i);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data){
        super.onActivityResult(requesCode,resultCode,data);
        if(requesCode==1){
            uri = data.getData();
            if(uri!=null){
                mfotoInicial2.setImageURI(uri);
            }
        }
    }
    public void subir_foto(String foto){
        StorageReference childRef = storageReference.child(foto);
        UploadTask uploadTask = childRef.putFile(uri);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                onBackPressed();
            }
        });
    }
}
