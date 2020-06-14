package com.example.nueva.library;
//clase necesaria para recibir el json, basicamente obtiene el json y lo convierte en objeto
//por eso los atributos de la clase son los mismos del json
//tipo de libro


import androidx.annotation.NonNull;

public class tipoMaterial {
    int idtipo_material;
    String nombre;

    public tipoMaterial(int idtipo_material, String nombre) {
        this.idtipo_material = idtipo_material;
        this.nombre = nombre;
    }

    public int getIdtipo_material() {
        return idtipo_material;
    }

    public String getDescripcion() {
        return nombre;
    }

    @NonNull
    @Override
    public String toString() {
        return nombre;
    }
}
