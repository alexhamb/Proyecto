package com.example.asus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Registrar_Doctor extends AppCompatActivity {
    private EditText txtCedulaD;
    private EditText txtNombreD;
    private EditText txtEmailD;
    private EditText txtEdadD;
    private EditText txtRollD;
    private ArrayList<Integer> fotos;
    private ArrayList<String> adapter;
    private String opc[];
    private Resources res;
    private Uri uri;
    ImageView foto;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar__doctor);

        foto = (ImageView) findViewById(R.id.fotoInicial2);
        txtCedulaD = (EditText) findViewById(R.id.txtCedulaD);
        txtNombreD = (EditText) findViewById(R.id.txtNombreD);
        txtEdadD = (EditText) findViewById(R.id.txtEdadD);
        txtRollD = (EditText) findViewById(R.id.txtRollD);
        txtEmailD = (EditText) findViewById(R.id.txtEmailD);
        res = this.getResources();

        inicializar_fotos();
        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void inicializar_fotos() {
        fotos = new ArrayList<>();
        fotos.add(R.drawable.imagen);
        fotos.add(R.drawable.imagen2);


    }

    public boolean validar() {
        String aux = res.getString(R.string.msg_error_vacio);
        if (Metodos.validar_aux(txtCedulaD, aux)) return false;
        else if (Metodos.validar_aux(txtNombreD, aux)) return false;
        else if (Metodos.validar_aux(txtEmailD, aux)) return false;
        else if (Metodos.validar_aux(txtEdadD, aux)) return false;
        else if (Metodos.validar_aux(txtRollD, aux)) return false;
        return true;
    }

    public void agregar(View v) {
        if (validar()) {

            String id = Datos.getId();
            String foto = id + ".jpg";

            Doctores d = new Doctores(id, foto,
                    txtCedulaD.getText().toString(),
                    txtNombreD.getText().toString(),
                    txtEmailD.getText().toString());
                    txtEdadD.getText().toString();
                    txtRollD.getText().toString();

            d.GuardarD();
            subir_foto(foto);
            Snackbar.make(v, res.getString(R.string.msg_doctor_guardado), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            limpiar();
        }
    }
    public void limpiar(View v) {
        limpiar();
    }

    public void limpiar() {
        txtCedulaD.setText("");
        txtNombreD.setText("");
        txtEmailD.setText("");
        txtRollD.setText("");
        txtEdadD.setText("");
        txtCedulaD.requestFocus();
        foto.setImageDrawable(ResourcesCompat.getDrawable(res,android.R.drawable.ic_menu_gallery,null));

        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

    }

    public void seleccionar_foto(View v) {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, getString(R.string.mensaje_escoja_imagen)), 1);
    }



    public void onBackPressed() {
        finish();
        Intent i = new Intent(Registrar_Doctor.this, MainActivity.class);
        startActivity(i);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data) {
        super.onActivityResult(requesCode, resultCode, data);
        if (requesCode == 1) {
            uri = data.getData();
            if (uri != null) {
                foto.setImageURI(uri);

            }
        }
    }

    public void  subir_foto(String foto){
        StorageReference childRef = storageReference.child(foto);
        UploadTask uploadTask = childRef.putFile(uri);
    }

}

