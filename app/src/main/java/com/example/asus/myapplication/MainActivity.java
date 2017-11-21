package com.example.asus.myapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private ListView opciones;
    private String[] opc;
    private ArrayAdapter adapter;
    private Intent i;
    private Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcionesprin);
        res = this.getResources();
        opciones = (ListView) findViewById(R.id.opc_prin);
        opc = getResources().getStringArray(R.array.Menu_P);
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, opc);
        opciones.setAdapter(adapter);
        opciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent i = new Intent(MainActivity.this, Vp.class);
                        startActivity(i);
                        break;
                    case 1:
                        i = new Intent(MainActivity.this, Crear_paciente.class);
                        startActivity(i);
                        break;
                }
            }

        });
    }
}