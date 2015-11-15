package com.example.lexa.myapplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;


public class Parse {
    Net vvod;
    IBeer iBeer = new IBeer();

    public Parse (Net vvod) {
        this.vvod = vvod;
    }

    public void runner() {
        try {
            BufferedReader bufferedreader = new BufferedReader(new FileReader("pub.json"));
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            iBeer = gson.fromJson(bufferedreader, IBeer.class);

        } catch (Exception e) {
            System.out.println("day");
        }
    }
}
