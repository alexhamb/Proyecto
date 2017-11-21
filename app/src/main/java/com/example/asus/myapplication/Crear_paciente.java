package com.example.asus.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

/**
 * Created by ASUS on 18/11/2017.
 */

public class Crear_paciente extends AppCompatActivity {

    private EditText txtCedulap;
    private EditText txtNombrep;
    private EditText txtEdadp;
    private EditText txtVmp;
    private ArrayList<Integer> fotos;
    private Resources res;
    private Uri uri;
    private ImageView foto;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp);

        foto = (ImageView)findViewById(R.id.fotoInicial2);
        txtCedulap = (EditText)findViewById(R.id.txtCedulap);
        txtNombrep = (EditText)findViewById(R.id.txtNombrep);
        txtEdadp = (EditText)findViewById(R.id.txtEdadp);
        txtVmp = (EditText)findViewById(R.id.txtVmp);

        res = this.getResources();


        storageReference = FirebaseStorage.getInstance().getReference();

    }



    public boolean validar(){
        String aux = res.getString(R.string.msg_error_vacio);
        if(Metodos.validar_aux(txtCedulap ,aux))return false;
        else if(Metodos.validar_aux(txtNombrep,aux))return false;
        else if(Metodos.validar_aux(txtEdadp,aux))return false;
        else if(Metodos.validar_aux(txtVmp,aux))return false;
        return true;
    }

    public void agregar(View v){
        if(validar()){

            String id = Datos.getId();
            String foto = id+".jpg";

            Pacientes p = new Pacientes(id,foto,
                    txtCedulap.getText().toString(),
                    txtNombrep.getText().toString(),
                    txtEdadp.getText().toString());

            p.GuardarP();
            subir_foto(foto);
            Snackbar.make(v,res.getString(R.string.msg_persona_guardada),Snackbar.LENGTH_LONG)
                    .setAction("Action",null).show();
            limpiar();

        }
    }

    public void limpiar(View v){
        limpiar();
    }
    public void limpiar(){
        txtCedulap.setText("");
        txtNombrep.setText("");
        txtEdadp.setText("");
        txtVmp.setText("");
        txtCedulap.requestFocus();
        foto.setImageDrawable(ResourcesCompat
                .getDrawable(res,android.R.drawable.ic_menu_gallery,null));

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,getString(R.string.mensaje_escoja_imagen)),1);
    }


    public void onBackPressed(){
        finish();
        Intent i = new Intent(Crear_paciente.this,MainActivity.class);
        startActivity(i);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data){
        super.onActivityResult(requesCode,resultCode,data);
        if(requesCode==1){
            uri = data.getData();
            if(uri!=null){
                foto.setImageURI(uri);
            }
        }
    }

    public void subir_foto(String foto){
        StorageReference childRef = storageReference.child(foto);
        UploadTask uploadTask = childRef.putFile(uri);
    }

}
