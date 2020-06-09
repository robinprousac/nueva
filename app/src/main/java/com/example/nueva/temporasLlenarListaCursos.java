package com.example.nueva;

import java.util.ArrayList;
import java.util.List;

public class temporasLlenarListaCursos {

    public static List<String> llenarlista(){

        List<String> listita = new ArrayList<>();

        listita.add("No.");listita.add("Codigo");listita.add("Curso");listita.add("Nota");listita.add("Fecha");
        listita.add(" ");listita.add(" ");listita.add(" ");listita.add(" ");listita.add(" ");

        for(int i = 0; i<20;i++){
            listita.add("numero"+i);
            listita.add("codigo"+i);
            listita.add("curso"+i);
            listita.add("nota"+i);
            listita.add("fecha"+i);
        }

        return  listita;
    }
}
