package com.example.nueva.Interface;


import com.example.nueva.Book;
import com.example.nueva.library.BookCopy;
import com.example.nueva.library.Book_description;
import com.example.nueva.library.libro;
import com.example.nueva.library.tipoMaterial;
import com.example.nueva.model.anuncio;
import com.example.nueva.notes.CarrerItems;
import com.example.nueva.notes.ItemNote;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("anuncio/service")
    Call<List<anuncio>> getAnuncios();

    @GET("biblioteca/tipo")
    Call<List<tipoMaterial>> getTipos();


    @GET("/service/carrers")
    Call<List<CarrerItems>> getCarrers(@Query("carnet") String carnet);

    @GET("/biblioteca/busqueda")
    Call<List<libro>> getLibros(@Query("query_string") String query);

    @GET("/biblioteca/book")
    Call<List<Book_description>> getLibro(@Query("id") String query);

    @GET("/biblioteca/info")
    Call<List<BookCopy>> getCopias(@Query("id") String query);

    @GET("/service/test")
    Call<List<ItemNote>> getNotes(@Query("id_estudiante_carrera") int est_carr);


  //  @GET("/service/carrers")
  //  public void getCarrers(Callback<List<CarrerItems>> response);
}
