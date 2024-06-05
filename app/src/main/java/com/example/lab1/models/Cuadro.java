package com.example.lab1.models;

public class Cuadro {
    private String nombre;
    private String autor;
    private int imagenResId; // ID del recurso de la imagen

    public Cuadro(String nombre, String autor, int imagenResId) {
        this.nombre = nombre;
        this.autor = autor;
        this.imagenResId = imagenResId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public int getImagenResId() {
        return imagenResId;
    }
}
