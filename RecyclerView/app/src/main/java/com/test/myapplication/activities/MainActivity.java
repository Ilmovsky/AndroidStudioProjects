package com.test.myapplication.activities;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.test.myapplication.R;
import com.test.myapplication.adapters.SummaAdaper;
import com.test.myapplication.models.Summa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private List<Summa> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SummaAdaper mSummaAdaper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

       LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(this);

  //      GridLayoutManager gridLayoutManager
   //             = new GridLayoutManager(this, 2);

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);

        mSummaAdaper = new SummaAdaper(mList);
        mRecyclerView.setAdapter(mSummaAdaper);

        mSummaAdaper.setItemClickListener(new SummaAdaper.ItemClickListener() {
            @Override
            public void onClick(Summa summa) {
                Log.d("ResVIEW", "item name" + summa.getName());
            }
        });

        showData();
    }

    private void showData() {

        mList.add(new Summa(10, new Date(), "Первый"));
        mList.add(new Summa(5.7, new Date(), "Второй"));
        mList.add(new Summa(1.1, new Date(), "Третий"));
        mList.add(new Summa(2.4, new Date(), "Item 4"));
        mList.add(new Summa(54.1, new Date(), "Item 5"));
        mList.add(new Summa(7.1, new Date(), "Item 6"));
        mList.add(new Summa(5.1, new Date(), "Item 7"));

        mSummaAdaper.addItems(mList);
    }


}
