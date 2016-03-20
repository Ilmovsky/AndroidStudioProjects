package com.example.lexa.alcho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lexa.alcho.basehelper.DataSour;
import com.example.lexa.alcho.construct.BigBase;
import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.adapters.RecycleAdapterDay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 28.12.2015.
 */
public class ProsmotrAlcoDay extends AppCompatActivity {

    List<VvodAlchoBase> values = null;
    List<VvodAlchoBase> values1 = null;
    List<BigBase> values2 = null;
    public static final String KEY_TEXT2 = "key_text";
    long re = 0;
    public DataSour datasource;
    private RecyclerView mRecyclerView;
    private RecycleAdapterDay mRecycleAdaper;
    static public String n = null;
    static public String n1 = null;
    static public String d = null;
    static public String c = null;
    static public String p = null;
    static public String m = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prosmotr_alcho_day);

        n = getResources().getString(R.string.Re);
        n1 = getResources().getString(R.string.Re2);
        d = getResources().getString(R.string.Re3);
        c = getResources().getString(R.string.Re4);
        p = getResources().getString(R.string.Te10);
        m = getResources().getString(R.string.Te8);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

        datasource = new DataSour(this);

        datasource.open();
        values = datasource.getAllAlcoBaseA();
        values2 = datasource.getAllAlcoBase();
        values1 = new ArrayList<VvodAlchoBase>();

        Intent intent = getIntent();
        re = intent.getLongExtra(KEY_TEXT2,1);


        for(int j = 0;j < values.size(); j++) {
            if (values.get(j).getIdAll() > re) {
                break;
            }
            if (values.get(j).getIdAll() == re) {
                values1.add(values.get(j));
            }
        }

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);

        mRecycleAdaper = new RecycleAdapterDay(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        mRecycleAdaper.addItems(values1);

        }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }
    }
