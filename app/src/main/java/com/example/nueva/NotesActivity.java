package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.notes.ItemNote;
import com.example.nueva.singleton.Singlenton_URL;
import com.example.nueva.singleton.Singleton_login;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class NotesActivity extends AppCompatActivity {

    private Spinner spinner;

    private final String URL = Singlenton_URL.getURL();
    private RetrofitApi retrofitApi;

    private  List<CarrerItems> anuncios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);


        Intent intent = getIntent();
       // String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);


        Toast.makeText(getApplicationContext(), Singleton_login.getInstance().usuario_logeado.getNombre(), Toast.LENGTH_LONG).show();

       // prueballenartabla();
        TextView alumno = (TextView) findViewById(R.id.textView3);
        alumno.setText(Singleton_login.getInstance().usuario_logeado.getNombre());

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);




        spinner = (Spinner) findViewById(R.id.planets_spinner);
        getCarrers();


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                CarrerItems carrer = (CarrerItems) parent.getSelectedItem();
                displaycarrer(carrer);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void getCarrers(){
        Call<List<CarrerItems>> call = retrofitApi.getCarrers(Singleton_login.getInstance().usuario_logeado.getCarnet());//200613637  200719847
        call.enqueue(new Callback<List<CarrerItems>>() {
            @Override
            public void onResponse(Call<List<CarrerItems>> call, Response<List<CarrerItems>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(NotesActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                anuncios = response.body();

                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CarrerItems> adapter = new ArrayAdapter<CarrerItems>(NotesActivity.this,
                        android.R.layout.simple_spinner_item, anuncios);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                spinner.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<CarrerItems>> call, Throwable t) {
                Toast.makeText(NotesActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }



    public void displaycarrer(CarrerItems c){
        String name = c.getNombre();
        int id = c.getId_estudiante_carrera();


        displayNotes(id);
    }
    public void prueballenartabla(){
        List<String> values= temporasLlenarListaCursos.llenarlista();
        GridView myGrid=(GridView)findViewById(R.id.gridmachos);
        GridViewAdapter adapt = new GridViewAdapter(values,NotesActivity.this);
        myGrid.setAdapter(adapt);


    }


    public void displayNotes(int id){
        Call<List<ItemNote>> call = retrofitApi.getNotes(id);//200613637  200719847
        call.enqueue(new Callback<List<ItemNote>>() {
            @Override
            public void onResponse(Call<List<ItemNote>> call, Response<List<ItemNote>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(NotesActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                List<ItemNote> anuncios = response.body();



                List<String> values=new ArrayList<>();
                values.add("No.");values.add("Codigo");values.add("Curso");values.add("     ");values.add("Nota");values.add("Fecha");


                int count = 0;

                String contenido = "";
                for(ItemNote n: anuncios){
                    contenido += "nombre: " + n.getCurso() + "\n";
                    contenido += "nota: " +n.getNota_final() + "\n";

                    count++;

                    values.add(Integer.toString(count));
                    values.add(n.getCod_curso());
                    values.add(n.getCurso());

                    values.add("     ");
                    values.add(n.getNota_final());
                    String str = n.getFecha_aprobacion();
                    String[] fechaSplit = str.split("00:");
                    values.add(fechaSplit[0]);
                }

                GridView myGrid=(GridView)findViewById(R.id.gridmachos);
                GridViewAdapter adapt = new GridViewAdapter(values,NotesActivity.this);
                myGrid.setAdapter(adapt);


                // myGrid.setAdapter(new ArrayAdapter<String>(NotesActivity.this,R.layout.cell,values));

            }

            @Override
            public void onFailure(Call<List<ItemNote>> call, Throwable t) {
                Toast.makeText(NotesActivity.this,t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
