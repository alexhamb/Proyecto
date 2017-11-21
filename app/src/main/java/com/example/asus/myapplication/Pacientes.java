package com.example.asus.myapplication;

/**
 * Created by root on 16/11/17.
 */

public class Pacientes {

    private String id;
    private String foto;
    private String cedula;
    private String nombre;
    private String edad;
    private String valoracion_medica;

    public Pacientes(String id) {
        this.id = id;
    }

    public Pacientes(String id, String foto, String cedula, String nombre, String edad, String valoracion_medica) {
        this.id = id;
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.valoracion_medica = valoracion_medica;
    }


    public Pacientes(String foto, String cedula, String nombre, String edad, String valoracion_medica) {

        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.edad = edad;
        this.valoracion_medica = valoracion_medica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getValoracion_medica() {
        return valoracion_medica;
    }

    public void setValoracion_medica(String valoracion_medica) {
        this.valoracion_medica = valoracion_medica;
    }

    public void GuardarP(){
        Datos.GuardarPacientes(this);
    }
    public void ModificarP(){Datos.Actualizar(this);}

    public void EliminarP(){Datos.Eliminar(this);}
}
