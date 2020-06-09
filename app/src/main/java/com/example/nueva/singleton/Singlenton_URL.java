package com.example.nueva.singleton;

public class Singlenton_URL {

    private static String URL;

    private Singlenton_URL(){
        URL = "";

    }

    public synchronized static String getURL(){
        URL = "http://35.192.190.218/";
        return URL;
    }
}
