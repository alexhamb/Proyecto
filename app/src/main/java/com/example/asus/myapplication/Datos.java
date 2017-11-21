package com.example.asus.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by root on 17/11/17.
 */

public class Datos {

    private static DatabaseReference databaseReference = FirebaseDatabase.
            getInstance().getReference();
    private static String bd ="com.example.root.mproject.Pacientes";
    private static ArrayList<Pacientes> pacientes = new ArrayList<>();
    private static String bd2 ="com.example.root.mproject.Medicamentos";
    private static ArrayList<Medicamentos> medicamentos = new ArrayList<>();
    private static String bd3 ="com.example.root.mproject.Doctores";
    private static ArrayList<Doctores> doctores = new ArrayList<>();


    public static void GuardarPacientes(Pacientes p){

        databaseReference.child(bd).child(p.getId()).setValue(p);
    }

    public static ArrayList<Pacientes> obtenerPacientes(){
        return pacientes;
    }

    public static void setPacientes(ArrayList<Pacientes> pac){
        pacientes=pac;
    }

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void Actualizar(Pacientes p ){
        databaseReference.child(bd).child(p.getId()).setValue(p);
    }
    public static void Eliminar(Pacientes p){
        databaseReference.child(bd).child(p.getId()).removeValue();
    }

    //

    public static void GuardarMedicamentos(Medicamentos m){

        databaseReference.child(bd2).child(m.getId()).setValue(m);
    }

    public static ArrayList<Medicamentos> obtenerMedicamentos(){
        return medicamentos;
    }

    public static void setMedicamentos(ArrayList<Medicamentos> med){
        medicamentos=med;
    }

    public static void ActualizarM(Medicamentos m){
        databaseReference.child(bd2).child(m.getId()).setValue(m);
    }
    public static void EliminarM(Medicamentos m){
        databaseReference.child(bd2).child(m.getId()).removeValue();
    }

    //

    public static void GuardarDoctores(Doctores d){

        databaseReference.child(bd3).child(d.getId()).setValue(d);
    }

    public static ArrayList<Doctores> obtenerDoctores(){
        return doctores;
    }

    public static void setDoctores(ArrayList<Doctores> d){
        doctores=d;
    }

    public static void ActualizarD(Doctores d){
        databaseReference.child(bd3).child(d.getId()).setValue(d);
    }
    public static void EliminarD(Doctores d){
        databaseReference.child(bd3).child(d.getId()).removeValue();
    }
}
