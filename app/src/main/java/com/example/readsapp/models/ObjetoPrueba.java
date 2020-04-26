package com.example.readsapp.models;

public class ObjetoPrueba {
    private String nombre;
    private String fecha;
    private int imgID;

    public ObjetoPrueba() {}

    public ObjetoPrueba(String nombre, String fecha, int imgID) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.imgID = imgID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }
}
