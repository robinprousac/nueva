package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.notes.ItemNote;

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

    private final String URL = "http://35.193.119.10/";
    private RetrofitApi retrofitApi;

    private  List<CarrerItems> anuncios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);




         spinner = (Spinner) findViewById(R.id.planets_spinner);
        getCarrers();
       // fetchJSON();

      /*   List<CarrerItems> carrers = anuncios;
         CarrerItems c1 = new CarrerItems("PROFESORADO DE ENSEÑANZA MEDIA EN PEDAGOGI", 568, 4);
         CarrerItems c2 = new CarrerItems("LICENCIATURA Y PEDAGOGIA Y DERECHOS HUMANOS", 567,5);

         carrers.add(c1);
         carrers.add(c2);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CarrerItems> adapter = new ArrayAdapter<CarrerItems>(this,
                android.R.layout.simple_spinner_item, carrers);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
*/

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






        //https://abhiandroid.com/ui/gridview
        //https://www.tutlane.com/tutorial/android/android-gridview-with-examples
        //https://www.raywenderlich.com/995-android-gridview-tutorial



    }


    private void getCarrers(){
        Call<List<CarrerItems>> call = retrofitApi.getCarrers("200613637");//200613637  200719847
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
                Toast.makeText(NotesActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //https://demonuts.com/android-spinner-example-to-load-json-using-retrofit/
        //https://dzone.com/articles/populate-spinner-from-json-data
        //https://androidforums.com/threads/how-to-populate-a-spinner-from-database-using-retrofit2.1229480/
    }



    public void displaycarrer(CarrerItems c){
        String name = c.getNombre();
        int id = c.getId_estudiante_carrera();

       // String data = "Carrera: "+name+ "\n ID: "+id;
       // Toast.makeText(this,data,Toast.LENGTH_LONG).show();

        displayNotes(id);
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
                //  anuncios_global = anuncios;
                //myAdapter = new MyAdapter(Main22Activity.this, anuncios);
                //recyclerView.setAdapter(myAdapter);
                // recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                //recyclerView.setLayoutManager(new LinearLayoutManager(Main22Activity.this,
                //      LinearLayoutManager.HORIZONTAL, false));
                // viewPager.setAdapter((PagerAdapter) myAdapter);
                //  recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


                List<String> values=new ArrayList<String>();
                values.add("No.");values.add("Codigo");values.add("Curso");values.add("Nota");values.add("Fecha");
                values.add(" ");values.add(" ");values.add(" ");values.add(" ");values.add(" ");

                int count = 0;

                String contenido = "";
                for(ItemNote n: anuncios){
                    contenido += "nombre: " + n.getCurso() + "\n";
                    contenido += "nota: " +n.getNota_final() + "\n";
                   // contenido += "idmacho: "+n.getId_estudiante_carrera() + "\n";

                    count++;

                    values.add(Integer.toString(count));
                    values.add(n.getCod_curso());
                    values.add(n.getCurso());
                    values.add(n.getNota_final());
                    values.add(n.getFecha_aprobacion());
                }






              /*  values.add("2");values.add("43");values.add("Curso2");values.add("90");values.add("01/03/2012");
                values.add("3");values.add("AD9");values.add("Curso3");values.add("76");values.add("01/03/2012");
                values.add("4");values.add("AB");values.add("Curso4");values.add("67");values.add("01/03/2012");
                values.add("5");values.add("A33");values.add("Curso5");values.add("87");values.add("01/03/2012");
                values.add("6");values.add("35S");values.add("Curso6");values.add("89");values.add("01/03/2012");
                values.add("7");values.add("AD9");values.add("Curso7");values.add("77");values.add("01/03/2012");
                values.add("8");values.add("AB");values.add("Curso8");values.add("87");values.add("01/03/2012");
                values.add("9");values.add("A33");values.add("Curso9");values.add("80");values.add("01/03/2012");
                values.add("10");values.add("35S");values.add("Curso10");values.add("89");values.add("01/03/2012");
                */

                GridView myGrid=(GridView)findViewById(R.id.gridmachos);

                myGrid.setAdapter(new ArrayAdapter<String>(NotesActivity.this,R.layout.cell,values));

                //  json_text.setText(contenido);
               // Toast.makeText(NotesActivity.this,contenido,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<List<ItemNote>> call, Throwable t) {
                Toast.makeText(NotesActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

/*
    private void fetchJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi api = retrofit.create(RetrofitApi.class);

        Call<List<CarrerItems>> call = api.getCarrers("200719847");

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        spinJSON(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
*/
}
