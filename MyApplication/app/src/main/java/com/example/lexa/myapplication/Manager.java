package com.example.lexa.myapplication;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Manager extends Thread{
    private static Manager instance;
    private ArrayList<Beer> good = new ArrayList<>();



    public ArrayList<Beer> getGood() {
        return good;
    }

    private Manager() {
    }

    public static class SingletonHolder {
        public static final Manager HOLDER_INSTANCE = new Manager();
    }

    public static Manager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }
    InputStream inputStream = null;
    FileOutputStream fileOutputStream = null;
    IBeer iBeer = new IBeer();
    BufferedInputStream bufferedreader = null;
    String chunk = null;

    public synchronized void run()
    {
        try {
          URL url = new URL("http://kiparo.ru/t/pub.json");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int status = connection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                System.out.println("day");
                inputStream = connection.getInputStream();
                bufferedreader = new BufferedInputStream(inputStream);
                int byteRead = -1;
                byte[] bufer = new byte[4096];
                while ((byteRead = inputStream.read(bufer)) != -1) {
                    chunk = new String(bufer, 0, byteRead);

                }
            }
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            iBeer = gson.fromJson(chunk, IBeer.class);
            good.addAll(iBeer.getGoods());



        } catch (Exception e) {
            Log.d("DDD", "e = " + e);
            System.out.println("day");
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }

        }

    }
}
