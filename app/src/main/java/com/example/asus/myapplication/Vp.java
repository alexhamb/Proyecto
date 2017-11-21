package com.example.asus.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by ASUS on 18/11/2017.
 */



public class Vp  extends AppCompatActivity implements AdaptadorP.OnPacientesClickListener{
    private RecyclerView listado;
    private ArrayList<Pacientes> pacientes;
    private Resources res;
    private AdaptadorP adapter;
    private LinearLayoutManager llm;
    private DatabaseReference databaseReference;
    private final String BD="Pacientes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listado = (RecyclerView)findViewById(R.id.lstPersonas);
        res = this.getResources();

        pacientes = new ArrayList<>();
        adapter = new AdaptadorP(this.getApplicationContext(),pacientes,this);
        llm = new LinearLayoutManager(this);
        listado.setLayoutManager(llm);
        listado.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(BD).addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                pacientes.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Pacientes p= snapshot.getValue(Pacientes.class);
                        pacientes.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setPacientes(pacientes);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void agregar(View v){
        Intent i = new Intent(Vp.this,Crear_paciente.class);
        startActivity(i);

    }
    @Override
    public void onPacientesClick(Pacientes p) {
        Intent i = new Intent(Vp.this,Modificar_paciente.class);
        Bundle b = new Bundle();
        b.putString("id",p.getId());
        b.putString("foto",p.getFoto());
        b.putString("cedula",p.getCedula());
        b.putString("nombre",p.getNombre());
        b.putString("apellido",p.getEdad());
        b.putString("valoracion_medica",p.getValoracion_medica());

        i.putExtra("datos",b);
        startActivity(i);

    }
}
