package com.example.asus.myapplication;

/**
 * Created by root on 16/11/17.
 */

public class Medicamentos {

    private String id;
    private String foto;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String cantidad;
    private String precio;

    public Medicamentos(String id, String foto, String nombre, String descripcion, String categoria, String cantidad, String precio) {
        this.id = id;
        this.foto = foto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void GuardarM(){
        Datos.GuardarMedicamentos(this);
    }
    public void ModificarM(){Datos.ActualizarM(this);}

    public void EliminarM(){Datos.EliminarM(this);}
}


