package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.data.Result;
import com.example.nueva.library.libro;
import com.example.nueva.library.tipoMaterial;
import com.example.nueva.singleton.Singlenton_URL;
import com.example.nueva.singleton.Singleton_idLibro;
import com.example.nueva.ui.login.BooksAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultsActivity extends AppCompatActivity {

    private final String URL = Singlenton_URL.getURL();
    // es la url del servidor
    private RetrofitApi retrofitApi;
    // sirve para las peticiones

    private List<libro> libros;
    GridView gridView;

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


        gridView = (GridView)findViewById(R.id.gridview);
        //Toast.makeText(ResultsActivity.this,message,Toast.LENGTH_LONG).show();

        setLibros(message);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                libro libro_select = (libro) libros.get(position);
                //String mesaage = "holaa";
                //Toast.makeText(ResultsActivity.this,mesaage+libro_select.getId_titulo(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Book_genericActivity.class);

                Singleton_idLibro.getInstance().id_book = position;

                String message = libro_select.getId_titulo();
                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);
            }
        });


    }

    private void setLibros(String query){
        Call<List<libro>> call = retrofitApi.getLibros(query, Singlenton_URL.getAppid(), Singlenton_URL.getPass());
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


                TextView Resultados = (TextView) findViewById(R.id.textView5);
                Resultados.setText("Resultados de busqueda: "+libros.size());

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


 /*

    public void openBook(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Book_genericActivity.class);

        String message = "6";
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    */

}

