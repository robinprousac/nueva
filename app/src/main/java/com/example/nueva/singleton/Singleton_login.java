package com.example.nueva.singleton;

import com.example.nueva.ui.login.user_login;

public class Singleton_login {


    // static variable single_instance of type Singleton
    private static Singleton_login single_instance = null;

    // variable of type String
    public user_login usuario_logeado;

    // private constructor restricted to this class itself
    private Singleton_login()
    {
        usuario_logeado = new user_login();
    }

    // static method to create instance of Singleton class
    public static Singleton_login getInstance()
    {
        if (single_instance == null)
            single_instance = new Singleton_login();

        return single_instance;
    }
}
