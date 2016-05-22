package com.lexa.tripworld.retrofit;

import com.lexa.tripworld.parseObject.ListOfCities;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lexa on 20.05.2016.
 */
public interface RetrofitInt {

    @GET("api/v2/position/suggest/en/{path}")
    Call<ArrayList<ListOfCities>> findCity(@Path("path") String cityName);

}
