package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.fragments.PageFragmet3;
import com.example.nueva.fragments.PageFragmet4;
import com.example.nueva.model.anuncio;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.singleton.Singlenton_URL;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main22Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    RecyclerView recyclerView;
    MyAdapter myAdapter;

    LinearLayoutManager linearLayoutManager;


    int time = 1;
    int count = 0;

    private final static int NUM_PAGES = 2;

    private final String URL = Singlenton_URL.getURL();
    private RetrofitApi retrofitApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
/*
        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmet3());
        list.add(new PageFragmet4());

        viewPager = findViewById(R.id.pager);
        adapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

*/

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitApi = retro.create(RetrofitApi.class);
        recyclerView = findViewById(R.id.recyclerView);
        //viewPager = findViewById(R.id.pager);

        getAnuncios();
     //   getCarrers();

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


    }

    private void getAnuncios(){
        Call<List<anuncio>> call = retrofitApi.getAnuncios();
        call.enqueue(new Callback<List<anuncio>>() {
            @Override
            public void onResponse(Call<List<anuncio>> call, Response<List<anuncio>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(Main22Activity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                List<anuncio> anuncios = response.body();
                //  anuncios_global = anuncios;
                myAdapter = new MyAdapter(Main22Activity.this, anuncios);
                recyclerView.setAdapter(myAdapter);
                // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                //recyclerView.setLayoutManager(new LinearLayoutManager(Main22Activity.this,
                       // LinearLayoutManager.HORIZONTAL, false));

                   /* String contenido = "";
                    for(anuncio n: anuncios){
                        contenido += "nombre: " + n.getNombre() + "\n";
                        contenido += "url: " +n.getUrl() + "\n";
                    }

                    json_text.setText(contenido);*/



                linearLayoutManager = new LinearLayoutManager(Main22Activity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(linearLayoutManager);

                //The LinearSnapHelper will snap the center of the target child view to the center of the attached RecyclerView , it's optional if you want , you can use it
              //  final LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
               // linearSnapHelper.attachToRecyclerView(recyclerView);

                //final int count = 0;

            startSlide();





            }

            @Override
            public void onFailure(Call<List<anuncio>> call, Throwable t) {
                Toast.makeText(Main22Activity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }



    private void startSlide(){
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {

                int tiempo_mili = 0;

                if (linearLayoutManager.findLastCompletelyVisibleItemPosition() < (myAdapter.getItemCount() - 1)) {



                    linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), linearLayoutManager.findLastCompletelyVisibleItemPosition() + 1);
                }

                else if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == (myAdapter.getItemCount() - 1)) {

                    linearLayoutManager.smoothScrollToPosition(recyclerView, new RecyclerView.State(), 0);
                }

                if(count == myAdapter.getItemCount()) { count = 0; }

                tiempo_mili = toMins(myAdapter.getItemTime(count ));

                //System.out.println(tiempo_mili);


                try {
                    Thread.sleep(tiempo_mili);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            /*
                if(count==1){
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(count == 2){
                    try {
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
               */ count++;
            }
        }, 0, time);
    }
/*
    private void getCarrers(){
        Call<List<CarrerItems>> call = retrofitApi.getCarrers("200719847");
        call.enqueue(new Callback<List<CarrerItems>>() {
            @Override
            public void onResponse(Call<List<CarrerItems>> call, Response<List<CarrerItems>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(Main22Activity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                List<CarrerItems> anuncios = response.body();
                //  anuncios_global = anuncios;
                //myAdapter = new MyAdapter(Main22Activity.this, anuncios);
                //recyclerView.setAdapter(myAdapter);
                // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                //recyclerView.setLayoutManager(new LinearLayoutManager(Main22Activity.this,
                  //      LinearLayoutManager.HORIZONTAL, false));
                // viewPager.setAdapter((PagerAdapter) myAdapter);
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    String contenido = "";
                    for(CarrerItems n: anuncios){
                        contenido += "nombre: " + n.getNombre() + "\n";
                        contenido += "id: " +n.getCod_carrera() + "\n";
                        contenido += "idmacho: "+n.getId_estudiante_carrera() + "\n";
                    }

                  //  json_text.setText(contenido);
                Toast.makeText(Main22Activity.this,contenido,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<CarrerItems>> call, Throwable t) {
                Toast.makeText(Main22Activity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
*/
    public void openMain1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private int toMins(String s) {
        String[] hourMin = s.split(":");
        int hour = Integer.parseInt(hourMin[0]);
        int mins = Integer.parseInt(hourMin[1]);
        int seconds = Integer.parseInt(hourMin[2]);
        int hoursInMili = hour * 3600 * 1000;
        int minsInMili = mins * 60 * 1000;
        int secondsInMili = seconds * 1000;
        return hoursInMili + minsInMili + secondsInMili;
    }

    public void prueba(){

    }
}
