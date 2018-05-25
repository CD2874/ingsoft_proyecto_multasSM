package com.example.dell.restapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by DELL on 8/04/2017.
 */

public interface InventarioClient {

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.10/api-rest-inventario/inventario-api.php/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @Headers({"Content-Type:application/json"})
    @POST("inventario")
    Call<Result> save(@Body Inventario inventario);

}
