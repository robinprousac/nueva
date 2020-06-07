package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class reservActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserv);
    }

    public void openShow(View view)
    {
        Toast toast1 = Toast.makeText(getApplicationContext(), "Libro reservado con exito.", Toast.LENGTH_LONG);
        //toast1.setGravity(Gravity.CENTER, , );

        toast1.show();

    }
}
