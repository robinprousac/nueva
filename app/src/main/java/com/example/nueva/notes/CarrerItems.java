package com.example.nueva.notes;

import androidx.annotation.NonNull;

public class CarrerItems {

    private String nombre;
    private int cod_carrera;
    private int id_estudiante_carrera;

    public CarrerItems(String nombre, int cod_carrera, int id_estudiante_carrera) {
        this.nombre = nombre;
        this.cod_carrera = cod_carrera;
        this.id_estudiante_carrera = id_estudiante_carrera;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCod_carrera(int cod_carrera) {
        this.cod_carrera = cod_carrera;
    }

    public void setId_estudiante_carrera(int id_estudiante_carrera) {
        this.id_estudiante_carrera = id_estudiante_carrera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCod_carrera() {
        return cod_carrera;
    }

    public int getId_estudiante_carrera() {
        return id_estudiante_carrera;
    }

    @NonNull
    @Override
    public String toString() {
         return nombre;
    }
}
