package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.model.anuncio;
import com.example.nueva.model.autoridad;
import com.example.nueva.singleton.Singlenton_URL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Autoridad extends AppCompatActivity {

    RecyclerView recyclerView;
    Adapter_Auth myAdapter ;

    LinearLayoutManager linearLayoutManager;

    private final String URL = Singlenton_URL.getURL();
    private RetrofitApi retrofitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autoridad);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retro.create(RetrofitApi.class);
        //recyclerView = findViewById(R.id.recyclerView2);


        ArrayList<String> list = new ArrayList<>();
        list.add("something1");
        list.add("something2");


        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

       // Adapter_Auth menuRecAdapter = new Adapter_Auth(this ,list);
       // recyclerView.setAdapter(menuRecAdapter);


      //  getAutoridades();
        //   getCarrers();



    }

/*
    private void getAutoridades(){
        Call<List<autoridad>> call = retrofitApi.getAutoridades();
        call.enqueue(new Callback<List<autoridad>>() {
            @Override
            public void onResponse(Call<List<autoridad>> call, Response<List<autoridad>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(Autoridad.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                List<autoridad> autoridades = response.body();


                recyclerView.setHasFixedSize(true);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Autoridad.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                //  anuncios_global = anuncios;
                myAdapter = new Adapter_Auth(Autoridad.this, autoridades);
                recyclerView.setAdapter(myAdapter);
            //     recyclerView.setLayoutManager(new LinearLayoutManager(Autoridad.this));
                //recyclerView.setLayoutManager(new LinearLayoutManager(Autoridad.this,
                 //LinearLayoutManager.HORIZONTAL, false));


                Toast.makeText(Autoridad.this,"funcionaaa",Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<List<autoridad>> call, Throwable t) {
                Toast.makeText(Autoridad.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    */
}
