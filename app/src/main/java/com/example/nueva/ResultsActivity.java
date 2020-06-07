package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.data.Result;
import com.example.nueva.library.libro;
import com.example.nueva.library.tipoMaterial;
import com.example.nueva.ui.login.BooksAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    private final String URL = "http://104.197.206.202/";
    // es la url del servidor
    private RetrofitApi retrofitApi;
    // sirve para las peticiones

    private List<libro> libros;

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        setLibros(message);

        //Toast.makeText(ResultsActivity.this,message,Toast.LENGTH_LONG).show();


    }

    private void setLibros(String query){
        Call<List<libro>> call = retrofitApi.getLibros(query);//200613637  200719847
        call.enqueue(new Callback<List<libro>>() {
            @Override
            public void onResponse(Call<List<libro>> call, Response<List<libro>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(ResultsActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }



                libros = response.body();

/*
                String contenido = "";
                for(libro n: libros){
                    contenido += "nombre: " + n.getTitulo() + "\n";
                    contenido += "id: " +n.getIdlibro() + "\n";
                    contenido += "idmacho: "+n.getRuta() + "\n";
                }

                //  json_text.setText(contenido);
                Toast.makeText(ResultsActivity.this,contenido,Toast.LENGTH_LONG).show();
*/
                GridView gridView = (GridView)findViewById(R.id.gridview);
                BooksAdapter booksAdapter = new BooksAdapter(ResultsActivity.this, libros);
                gridView.setAdapter(booksAdapter);


            }

            @Override
            public void onFailure(Call<List<libro>> call, Throwable t) {
                Toast.makeText(ResultsActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //https://demonuts.com/android-spinner-example-to-load-json-using-retrofit/
        //https://dzone.com/articles/populate-spinner-from-json-data
        //https://androidforums.com/threads/how-to-populate-a-spinner-from-database-using-retrofit2.1229480/
    }


    private Book[] books = {
            new Book(R.string.abc_an_amazing_alphabet_book, R.string.dr_seuss, R.drawable.abc,
                    "http://35.193.119.10/img/no-portada.jpg"),
            new Book(R.string.are_you_my_mother, R.string.dr_seuss, R.drawable.areyoumymother,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/areyoumymother.jpg"),
            new Book(R.string.where_is_babys_belly_button, R.string.karen_katz, R.drawable.whereisbabysbellybutton,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/whereisbabysbellybutton.jpg"),
            new Book(R.string.on_the_night_you_were_born, R.string.nancy_tillman, R.drawable.onthenightyouwereborn,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onthenightyouwereborn.jpg"),
            new Book(R.string.hand_hand_fingers_thumb, R.string.dr_seuss, R.drawable.handhandfingersthumb,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/handhandfingersthumb.jpg"),
            new Book(R.string.the_very_hungry_caterpillar, R.string.eric_carle, R.drawable.theveryhungrycaterpillar,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/theveryhungrycaterpillar.jpg"),
            new Book(R.string.the_going_to_bed_book, R.string.sandra_boynton, R.drawable.thegoingtobedbook,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thegoingtobedbook.jpg"),
            new Book(R.string.oh_baby_go_baby, R.string.dr_seuss, R.drawable.ohbabygobaby,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/ohbabygobaby.jpg"),
            new Book(R.string.the_tooth_book, R.string.dr_seuss, R.drawable.thetoothbook,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/thetoothbook.jpg"),
            new Book(R.string.one_fish_two_fish_red_fish_blue_fish, R.string.dr_seuss, R.drawable.onefish,
                    "http://www.raywenderlich.com/wp-content/uploads/2016/03/onefish.jpg")
    };

    public void openBook(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Book_genericActivity.class);

        String message = "6";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}

