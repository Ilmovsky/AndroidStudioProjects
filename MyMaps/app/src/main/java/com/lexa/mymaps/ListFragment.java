package com.lexa.mymaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Lexa on 02.02.2016.
 */
public class ListFragment extends Fragment {

    AlertDialog.Builder builder;
    public static DataSource datasource;
    MapBase mapBase = null;
    List<String> maps = null;
    List<MapBase> values = null;
    private AdapterList mRecycleAdaper;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, null);

        maps = new ArrayList<>();

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        datasource = new DataSource(getActivity());
        datasource.open();

        LinearLayoutManager linearLayoutManager
                = new LinearLayoutManager(getActivity());

        values = datasource.getAllMapBase();

        iii: for (int i = 0; i < values.size(); i++){
            if(maps.size() > 0){
                for(int j = 0; j < maps.size(); j++){
                    if(values.get(i).getKind().equalsIgnoreCase(maps.get(j))){
                        continue iii;
                    }
                }
                maps.add(values.get(i).getKind());
            }
            else {
                maps.add(values.get(i).getKind());
            }
        }

        mRecyclerView.setLayoutManager(linearLayoutManager); // либо linearLayoutManager
        mRecyclerView.setHasFixedSize(true);
        mRecycleAdaper = new AdapterList(maps);
        mRecyclerView.setAdapter(mRecycleAdaper);

        mRecycleAdaper.addItems(maps);

        mRecycleAdaper.setItemClickListener(new AdapterList.ItemClickListener() {
            @Override
            public void onClick(String summa) {
               onCreateListPlaces(summa);
            }
        });

        return v;
    }





    public Dialog onCreateListPlaces(String summa){
        final List<MapBase> values1 = new ArrayList<>();
        for (MapBase value : values){
            if(value.getKind().equalsIgnoreCase(summa)){
                values1.add(value);
            }
        }
        final String [] places = new String [values1.size()];
        for (int i =0; i < values1.size(); i++){
            places [i] = values1.get(i).getPlace();
        }
       // Arrays.sort(places);

        builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(summa);
        builder.setItems(places, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                HybridMap.mapsBase.setCoordX(values1.get(item).getCoordX());
                HybridMap.mapsBase.setCoordY(values1.get(item).getCoordY());
                HybridMap.mapsBase.setZoomLevel(17);
                datasource.updateMapsBase(HybridMap.mapsBase);
                MainActivity.clickOnListPlace = true;
                MainActivity. tabHost.setCurrentTabByTag("map");
            }
        });
        builder.setCancelable(true);
        return  builder.show();
    }


}
