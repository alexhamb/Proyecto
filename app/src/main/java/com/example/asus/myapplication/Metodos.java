package com.example.asus.myapplication;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ASUS on 19/11/2017.
 */

public class Metodos {
    public static int fotoAleatoria(ArrayList<Integer> fotos) {
        int fotoSeleccionada;
        Random r = new Random();
        fotoSeleccionada = r.nextInt(fotos.size());
        return fotos.get(fotoSeleccionada);
    }

    public static boolean validar_aux(TextView t, String mensaje){
        if(t.getText().toString().isEmpty()){
            t.requestFocus();
            t.setError(mensaje);

        }
        return  false;
    }

    public static boolean existencia_paciente(ArrayList<Pacientes> pacientes, String cedula){
        for (int i = 0; i < pacientes.size(); i++) {
            if(pacientes.get(i).getCedula().equals(cedula)) return true;
        }
        return false;
    }
}
