package com.lexa.tripworld.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lexa on 20.05.2016.
 */
public class RetrofitInitialization {

    public static RetrofitInt retroinc(){

        String URL = "http://api.goeuro.com/";

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(URL)
                .build();

        RetrofitInt intf = retrofit.create(RetrofitInt.class);


        return intf;
    }

}
