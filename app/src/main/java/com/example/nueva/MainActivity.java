package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.nueva.fragments.PageFragmet1;
import com.example.nueva.fragments.PageFragmet2;
import com.example.nueva.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private ViewPager viewPager;
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }


    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        String message = "FAHUSAC 2020";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void openAuth(View view){
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }

    public void openLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openCheck(View view){
        Intent intent = new Intent(this, CheckActivity.class);
        startActivity(intent);
    }

    public void openSug(View view){
        Intent intent = new Intent(this, sugeActivity.class);
        startActivity(intent);
    }

}
