package com.example.asus.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Modificar_Doctor extends AppCompatActivity {

    private ImageView mfotoInicial2;
    private EditText mtxtCedulaD;
    private EditText mtxtNombreD;
    private EditText mtxtEdadD;
    private EditText mtxtVmD;
    private EditText mtxtRollD;
    private EditText mtxtEmailD;
    private Intent i;
    private Bundle b;
    private StorageReference storageReference;
    private Uri uri;

    public Modificar_Doctor (){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcp);

        mfotoInicial2 = (ImageView)findViewById(R.id.mfotoInicial2);
        mtxtCedulaD = (EditText)findViewById(R.id.mtxtCedulaD);
        mtxtNombreD = (EditText)findViewById(R.id.mtxtNombreD);
        mtxtEdadD = (EditText)findViewById(R.id.mtxtEdadD);
        mtxtRollD = (EditText)findViewById(R.id.mtxtRollD);
        mtxtEmailD = (EditText)findViewById(R.id.mtxtEmailD);
        storageReference = FirebaseStorage.getInstance().getReference();


        i = getIntent();
        b = i.getBundleExtra("datos");

        mtxtCedulaD.setText(b.getString("cedula"));
        mtxtNombreD.setText(b.getString("nombre"));
        mtxtEdadD.setText(b.getString("edad"));
        mtxtEmailD.setText(b.getString("email"));
        mtxtRollD.setText(b.getString("especializacion"));

        storageReference.child(b.getString("foto")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(Modificar_Doctor.this).load(uri).into(mfotoInicial2);
            }
        });


    }

    public void modificar(View v){
        String cedula =  mtxtCedulaD.getText().toString();
        String nombre = mtxtNombreD.getText().toString();
        String edad = mtxtEdadD.getText().toString();
        String email = mtxtEmailD.getText().toString();
        String roll = mtxtRollD.getText().toString();
        Doctores d = new Doctores(b.getString("id"),b.getString("foto"),cedula,nombre,edad);

        if(cedula.equals(b.getString("cedula"))){
            d.ModificarD();
            if(uri!=null)subir_foto(b.getString("foto"));
            Snackbar.make(v, R.string.msgs_doctor_modificado_exitosamente,Snackbar.LENGTH_SHORT).setAction("action",null).show();


        }else{
            if(Metodos.existencia_paciente(Datos.obtenerPacientes(),cedula)){
                mtxtCedulaD.setError(getString(R.string.msg_error_cedula_existente));

                mtxtCedulaD.requestFocus();
            }else{
                d.ModificarD();
                if(uri!=null)subir_foto(b.getString("foto"));
                Snackbar.make(v, R.string.msgs_doctor_modificado_exitosamente,Snackbar.LENGTH_SHORT)
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
                Doctores d = new Doctores(b.getString("id"));
                d.EliminarD();
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
        Intent i = new Intent(Modificar_Doctor.this,MainActivity.class);
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