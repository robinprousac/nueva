package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.model.autoridad;
import com.example.nueva.singleton.Singlenton_URL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutoridadActivity extends AppCompatActivity {


    String s1[], s2[];


    private final String URL = Singlenton_URL.getURL();
    private RetrofitApi retrofitApi;

    private RecyclerView recyclerView;
    private Adapter_Auth mAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autoridad2);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retro.create(RetrofitApi.class);


        recyclerView = (RecyclerView) findViewById(R.id.new_recycler);

        s1 = getResources().getStringArray(R.array.programs);
        s2 = getResources().getStringArray(R.array.programs_desc);




        // use a linear layout manager
        //layoutManager = new LinearLayoutManager(this);


        getAutoridades();

        // specify an adapter (see also next example)

    }


    private void getAutoridades(){
        Call<List<autoridad>> call = retrofitApi.getAutoridades();
        call.enqueue(new Callback<List<autoridad>>() {
            @Override
            public void onResponse(Call<List<autoridad>> call, Response<List<autoridad>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(AutoridadActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                List<autoridad> autoridades = response.body();




                mAdapter = new Adapter_Auth(AutoridadActivity.this, autoridades);
                recyclerView.setAdapter(mAdapter);
                linearLayoutManager = new LinearLayoutManager(AutoridadActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);


                PagerSnapHelper snapHelper = new PagerSnapHelper();
                snapHelper.attachToRecyclerView(recyclerView);



            }

            @Override
            public void onFailure(Call<List<autoridad>> call, Throwable t) {
                Toast.makeText(AutoridadActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
