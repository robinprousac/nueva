package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.library.BookCopy;
import com.example.nueva.library.BookCopyAdapter;
import com.example.nueva.library.Book_description;
import com.example.nueva.library.libro;
import com.example.nueva.singleton.Singlenton_URL;
import com.example.nueva.ui.login.BooksAdapter;
import com.example.nueva.ui.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Book_genericActivity extends AppCompatActivity {

    private final String URL = Singlenton_URL.getURL();
    // es la url del servidor
    private RetrofitApi retrofitApi;
    // sirve para las peticiones

    private List<Book_description> campos;
    private List<BookCopy> copias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_generic);


        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);


        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        setlibro(message);
        setcopy(message);


        Toast.makeText(Book_genericActivity.this,message,Toast.LENGTH_LONG).show();
    }

    private void setlibro(String query){
        Call<List<Book_description>> call = retrofitApi.getLibro(query);//200613637  200719847
        call.enqueue(new Callback<List<Book_description>>() {
            @Override
            public void onResponse(Call<List<Book_description>> call, Response<List<Book_description>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(Book_genericActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }



                campos = response.body();


                String contenido = "";

                String tituloAnterior = "";
                String valor = "";
                List<String> values=new ArrayList<>();

                for(int a = 0; a<campos.size();a++){

                    if(!campos.get(a).getDescripcion_campo().equalsIgnoreCase("Dewey")){
                        if(campos.get(a).getDescripcion_campo().equalsIgnoreCase(tituloAnterior) ){
                            valor+= " "+campos.get(a).getValor();
                        }else{
                            if(a!=0){
                                values.add(tituloAnterior);
                                values.add(valor);
                            }
                            tituloAnterior = campos.get(a).getDescripcion_campo();
                            valor = campos.get(a).getValor();
                        }
                    }

                }



                GridView myGrid=(GridView)findViewById(R.id.grid_descripcionlibro);
                GridViewAdapter adapt = new GridViewAdapter(values,Book_genericActivity.this);
                myGrid.setAdapter(adapt);




            }

            @Override
            public void onFailure(Call<List<Book_description>> call, Throwable t) {
                Toast.makeText(Book_genericActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //https://demonuts.com/android-spinner-example-to-load-json-using-retrofit/
        //https://dzone.com/articles/populate-spinner-from-json-data
        //https://androidforums.com/threads/how-to-populate-a-spinner-from-database-using-retrofit2.1229480/
    }


    private void setcopy(String query){
        Call<List<BookCopy>> call = retrofitApi.getCopias(query);//200613637  200719847
        call.enqueue(new Callback<List<BookCopy>>() {
            @Override
            public void onResponse(Call<List<BookCopy>> call, Response<List<BookCopy>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(Book_genericActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }



                copias = response.body();

/*
                String contenido = "";
                for(BookCopy n: copias){
                    contenido += "titulo: " + n.getTitulo() + "\n";
                    contenido += "isbn: " +n.getIdlibro() + "\n";
                    contenido += "descripcion: "+n.getDescripcion() + "\n";
                    contenido += "autor: "+n.getAutor() + "\n";
                }

                //  json_text.setText(contenido);
                Toast.makeText(Book_genericActivity.this,contenido,Toast.LENGTH_LONG).show();
*/


                  GridView gridView = (GridView)findViewById(R.id.gridview);
                BookCopyAdapter booksAdapter = new BookCopyAdapter(Book_genericActivity.this, copias);
                 gridView.setAdapter(booksAdapter);



            }

            @Override
            public void onFailure(Call<List<BookCopy>> call, Throwable t) {
                Toast.makeText(Book_genericActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //https://demonuts.com/android-spinner-example-to-load-json-using-retrofit/
        //https://dzone.com/articles/populate-spinner-from-json-data
        //https://androidforums.com/threads/how-to-populate-a-spinner-from-database-using-retrofit2.1229480/
    }


    public void openReserv(View view){
        Intent intent = new Intent(this, reservActivity.class);
        startActivity(intent);
    }


}
