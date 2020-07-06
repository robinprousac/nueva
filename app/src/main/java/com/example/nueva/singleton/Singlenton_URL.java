package com.example.nueva.singleton;

public class Singlenton_URL {

    private static String URL;
    private static String IDAPP;
    private static String password;

    private Singlenton_URL(){
        URL = "";

    }

    public synchronized static String getURL(){
        URL = "http://35.192.190.218/";
        return URL;
    }

    public synchronized static String getAppid(){
        IDAPP = "1";
        return IDAPP;
    }

    public synchronized static String getPass(){
        password = "12345";
        return password;
    }
}
