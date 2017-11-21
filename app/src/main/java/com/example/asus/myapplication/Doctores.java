package com.example.asus.myapplication;

/**
 * Created by root on 16/11/17.
 */

public class Doctores {

    private String id;
    private String foto;
    private String cedula;
    private String nombre;
    private String sexo;
    private String edad;
    private String especialidad;
    private String email;

    public Doctores(String id, String foto, String cedula, String nombre, String sexo, String edad, String especialidad, String email) {
        this.id = id;
        this.foto = foto;
        this.cedula = cedula;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.especialidad = especialidad;
        this.email = email;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void GuardarD(){
        Datos.GuardarDoctores(this);
    }
    public void ModificarD(){Datos.ActualizarD(this);}

    public void EliminarD(){Datos.EliminarD(this);}
}


