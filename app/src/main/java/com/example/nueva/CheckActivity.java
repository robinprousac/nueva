package com.example.nueva;
//clase que esta en la pantalla de CONSULTA CATALOGO
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.library.tipoMaterial;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.singleton.Singlenton_URL;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckActivity extends AppCompatActivity {


    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private Spinner spinner;
    //se declara el combobo para tipo de material

    private final String URL = Singlenton_URL.getURL();
    // es la url del servidor
    private RetrofitApi retrofitApi;
    // sirve para las peticiones

    private List<tipoMaterial> tipos = new ArrayList<tipoMaterial>();

    // lista que se llena despues de la peticion (combobox tipo material)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);




        spinner = (Spinner) findViewById(R.id.spinner);


        setTipos();

        Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.ordenar, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);
    }


    public void openResults(View view){

        EditText edit = (EditText)findViewById(R.id.editText);

        String titulo =  edit.getText().toString();

        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(EXTRA_MESSAGE,titulo);
        startActivity(intent);
    }

    private void setTipos(){
        Call<List<tipoMaterial>> call = retrofitApi.getTipos();//200613637  200719847
        call.enqueue(new Callback<List<tipoMaterial>>() {
            @Override
            public void onResponse(Call<List<tipoMaterial>> call, Response<List<tipoMaterial>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(CheckActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                tipoMaterial tp = new tipoMaterial(0,"TIPO DE MATERIAL");
               // tipos.add(tp);

                tipos = response.body();
                tipos.add(0,tp);

                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<tipoMaterial> adapter = new ArrayAdapter<tipoMaterial>(CheckActivity.this,
                        android.R.layout.simple_spinner_item, tipos);
// Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                spinner.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<tipoMaterial>> call, Throwable t) {
                Toast.makeText(CheckActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        //https://demonuts.com/android-spinner-example-to-load-json-using-retrofit/
        //https://dzone.com/articles/populate-spinner-from-json-data
        //https://androidforums.com/threads/how-to-populate-a-spinner-from-database-using-retrofit2.1229480/
    }
}
