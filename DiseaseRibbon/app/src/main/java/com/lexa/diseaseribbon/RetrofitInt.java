package com.lexa.diseaseribbon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lexa on 29.03.2016.
 */
public interface RetrofitInt {

    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Diseaselist?pageSize=100")
    Call<DiseaseBase> disease(@Query("offset") int offset);


    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Diseaselist?pageSize=100")
    Call<DiseaseBase> disease1();


    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/DiseaseList?pageSize=100")
    Call<DiseaseBase> diseasefind(@Query("where") String disease);


    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Disease?pageSize=100")
    Call<DiseaseBase> topics2();


    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Disease?pageSize=100")
    Call<DiseaseBase> topics(@Query("where") String disease);



    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/Disease?pageSize=100")
    Call<DiseaseBase> topics1(@Query("where") String disease, @Query("offset") int offset);



    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @POST("v1/data/Disease")
    Call<DiseaseList> topicPut(@Body  DiseaseList disease);



    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @POST("v1/data/{path}")
    Call<InTopicList> topicPut2(@Path("path") String topicName, @Body  InTopicList inTopicList);



    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/{path}?pageSize=100")
    Call<InTopicBase> topic(@Path("path") String topicName,@Query("where") String topic);



    @Headers({
            "application-id: 02D208FE-DC5F-A3B0-FF35-F317CB730500",
            "secret-key: 225BCBE4-DC36-5375-FF66-FBACE7C6A200"
    })
    @GET("v1/data/{path}?pageSize=100")
    Call<InTopicBase> topic1(@Path("path") String topicName, @Query("where") String disease, @Query("offset") int offset);
}