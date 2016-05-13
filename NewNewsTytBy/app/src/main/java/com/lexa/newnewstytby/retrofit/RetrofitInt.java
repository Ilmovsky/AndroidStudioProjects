package com.lexa.newnewstytby.retrofit;


import com.lexa.newnewstytby.object.RssInfo;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lexa on 22.04.2016.
 */
public interface RetrofitInt {


    @GET("/rss/index.rss")
    Call<RssInfo> readListNews();
}
