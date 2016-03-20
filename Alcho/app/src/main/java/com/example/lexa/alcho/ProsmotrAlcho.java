package com.example.lexa.alcho;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.lexa.alcho.basehelper.DataSour;
import com.example.lexa.alcho.construct.BigBase;
import com.example.lexa.alcho.adapters.RecycleAdapterAll;

import java.util.List;

/**
 * Created by Lexa on 27.12.2015.
 */
public class ProsmotrAlcho extends AppCompatActivity {

    List<BigBase> values = null;
    public DataSour datasource;

    BigBase alco = null;

    private RecyclerView mRecyclerView;
    private RecycleAdapterAll mRecycleAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prosmotr_alcho);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

        datasource = new DataSour(this);
        datasource.open();
        values = datasource.getAllAlcoBase();

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);

        mRecycleAdaper = new RecycleAdapterAll(values);
        mRecyclerView.setAdapter(mRecycleAdaper);

        mRecycleAdaper.addItems(values);

        mRecycleAdaper.setItemClickListener(new RecycleAdapterAll.ItemClickListener() {

            @Override
            public void onClick(BigBase summa) {
                Intent intent2 = new Intent(ProsmotrAlcho.this, ProsmotrAlcoDay.class);
                intent2.putExtra(ProsmotrAlcoDay.KEY_TEXT2, summa.getId());
                startActivity(intent2);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }
}
