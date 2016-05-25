package com.lexa.belhard;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 25.05.2016.
 */
public class MarketFragment extends Fragment {

    MarkerAdapter markerAdapter;

    List<MarketBase> imojis;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.market_fragment, container, false);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        imojis = new ArrayList<>();

        int[] imoji = getResources().getIntArray(R.array.listArray5);
        for(int i= 0; i < imoji.length; i++){
            imojis.add(new MarketBase(imoji[i],"Studio","Vasia",125));
        }

        final RecyclerView mRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView3);

        final LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        markerAdapter = new MarkerAdapter(imojis);
        mRecyclerView.setAdapter(markerAdapter);
        markerAdapter.addItems(imojis);

    }


    @Override
    public void onPause()
    {
        super.onPause();
    }



    @Override
    public void onResume()
    {
        super.onResume();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}


