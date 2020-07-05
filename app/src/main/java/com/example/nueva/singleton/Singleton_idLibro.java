package com.example.nueva.singleton;

import com.example.nueva.ui.login.user_login;

public class Singleton_idLibro {


    // static variable single_instance of type Singleton
    private static Singleton_idLibro single_instance = null;

    // variable of type String
    public int usuario_logeado;

    // private constructor restricted to this class itself
    private Singleton_idLibro()
    {
        usuario_logeado = 0;
    }

    // static method to create instance of Singleton class
    public static Singleton_idLibro getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton_idLibro();

        return single_instance;
    }
}
