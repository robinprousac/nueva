package com.example.nueva.model;

public class anuncio   {
    private String nombre;
    private String url;
    private String tiempo;
    private String tipo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTiempo() {return tiempo;}

    public void setTiempo(String tiempo) {this.tiempo = tiempo;}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
