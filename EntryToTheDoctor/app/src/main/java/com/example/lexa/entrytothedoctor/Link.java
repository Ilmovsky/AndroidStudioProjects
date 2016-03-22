package com.example.lexa.entrytothedoctor;

import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;


/**
 * Created by Lexa on 21.03.2016.
 */
public interface Link {
    /*Retrofit get annotation with our URL
           And our method that will return us the list ob Book
        */
    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Doctors")
    Call<DoctorBase> doctor();
}
