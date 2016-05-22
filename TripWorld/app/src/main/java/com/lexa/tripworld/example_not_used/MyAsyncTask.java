package com.lexa.tripworld.example_not_used;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.lexa.tripworld.parseObject.ListOfCities;
import com.lexa.tripworld.retrofit.RetrofitInitialization;
import com.lexa.tripworld.retrofit.RetrofitInt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lexa on 20.05.2016.
 */
public class MyAsyncTask extends AsyncTask<String, Void, String[]> {

    public AsyncMYAnswer asyncAnswer = null;

    public interface AsyncMYAnswer {
        public void returnCities (String[] findCities);
    }

    public void setAsyncMYAnswer(Context context) {
        this.asyncAnswer = (AsyncMYAnswer) context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected String[] doInBackground(String... url) {

        try {
            TimeUnit.MILLISECONDS.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        RetrofitInt intf = RetrofitInitialization.retroinc();
        Call<ArrayList<ListOfCities>> call = intf.findCity(url[0]);
        Response<ArrayList<ListOfCities>> arrayListResponse = null;

        try {
            arrayListResponse = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isCancelled())
            return null;
        ArrayList<ListOfCities> listOfCities = arrayListResponse.body();
        if (listOfCities == null || listOfCities.size() == 0)
            return null;
        String[] nowCities = new String[listOfCities.size()];
        for(int i = 0;i < listOfCities.size();i++){
          nowCities[i] = listOfCities.get(i).getName() + "(" + listOfCities.get(i).getCountry() +")";
        }
        return nowCities;
    }

    @Override
    protected void onPostExecute(String[] result) {
        super.onPostExecute(result);
        asyncAnswer.returnCities(result);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        Log.d("xcxvc", "Cancel");

    }
}
