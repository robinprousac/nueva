package com.example.nueva.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nueva.Interface.RetrofitApi;
import com.example.nueva.NotesActivity;
import com.example.nueva.R;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.singleton.Singlenton_URL;
import com.example.nueva.singleton.Singleton_login;
import com.example.nueva.ui.login.LoginViewModel;
import com.example.nueva.ui.login.LoginViewModelFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private LoginViewModel loginViewModel;
    String user;
    String pass;

    String carnet_l;
    String nombre_l;

    private  List<user_login> login;

    private final String URL = Singlenton_URL.getURL();
    private RetrofitApi retrofitApi;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        Retrofit retro = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitApi = retro.create(RetrofitApi.class);

        final EditText usernameEditText = findViewById(R.id.username);
        final EditText passwordEditText = findViewById(R.id.password);
        final Button loginButton = findViewById(R.id.login);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);




        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    updateUiWithUser(loginResult.getSuccess());
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        //String welcome = getString(R.string.welcome) + model.getDisplayName();
       // String welcome = "welcome";
       // validate();

        EditText user_text = findViewById(R.id.username);
        EditText pass_text = findViewById(R.id.password);

        user = user_text.getText().toString();
        pass = pass_text.getText().toString();


        validate(user,pass);






        // TODO : initiate successful logged in experience
       // Toast.makeText(getApplicationContext(), user, Toast.LENGTH_LONG).show();




    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void validate(String carnet, String password){
        Call<List<user_login>> call = retrofitApi.getLogin(carnet, password, Singlenton_URL.getAppid(), Singlenton_URL.getPass());
        call.enqueue(new Callback<List<user_login>>() {
            @Override
            public void onResponse(Call<List<user_login>> call, Response<List<user_login>> response) {

                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "respuesta codigo"+response.code(), Toast.LENGTH_LONG).show();
                }

                login = response.body();

               // String contenido = "";

                for(user_login n: login){
                    carnet_l =  n.getCarnet();
                    nombre_l =  n.getNombre();
                }


                if(carnet_l.equals("0")){
                    Toast.makeText(LoginActivity.this,"Usuario o contraseña incorreta", Toast.LENGTH_LONG).show();
                }else{

                    Intent intent = new Intent(LoginActivity.this, NotesActivity.class);
                    Singleton_login.getInstance().usuario_logeado.setCarnet(carnet_l);
                    Singleton_login.getInstance().usuario_logeado.setNombre(nombre_l);
                    //intent.putExtra(EXTRA_MESSAGE, carnet_p);
                    startActivity(intent);
                }

                //Toast.makeText(LoginActivity.this,contenido, Toast.LENGTH_LONG).show();



            }

            @Override
            public void onFailure(Call<List<user_login>> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"error culey"+t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
