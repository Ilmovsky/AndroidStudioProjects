package com.lexa.newnewstytby.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Lexa on 05.05.2016.
 */
public class RetrofitInic {

 public static RetrofitInt retroinc(){

      String URL = "http://news.tut.by/";


      Retrofit retrofit = new Retrofit.Builder()
              .addConverterFactory(SimpleXmlConverterFactory.create())
              .baseUrl(URL)
              .build();

      RetrofitInt intf = retrofit.create(RetrofitInt.class);


      return intf;
  }

}
