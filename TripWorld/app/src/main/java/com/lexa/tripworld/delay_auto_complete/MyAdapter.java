package com.lexa.tripworld.delay_auto_complete;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.lexa.tripworld.R;
import com.lexa.tripworld.internet_broadcast.BroadcastConnection;
import com.lexa.tripworld.location_listener.LocListener;
import com.lexa.tripworld.parseObject.ListOfCities;
import com.lexa.tripworld.retrofit.RetrofitInitialization;
import com.lexa.tripworld.retrofit.RetrofitInt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Lexa on 20.05.2016.
 */
public class MyAdapter extends BaseAdapter implements Filterable {

    private final Context mContext;
    private List<ListOfCities> mResults;

    public MyAdapter(Context mContext) {
        this.mContext = mContext;
        this.mResults = new ArrayList<ListOfCities>();
        ;
    }

    @Override
    public int getCount() {
        return mResults.size();
    }

    @Override
    public ListOfCities getItem(int position) {
        return mResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.simple_dropdown_item_2line, parent, false);
        }
        ListOfCities item = getItem(position);
        ((TextView) convertView.findViewById(R.id.text1)).setText(item.getName());
        ((TextView) convertView.findViewById(R.id.text2)).setText(item.getCountry());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    RetrofitInt intf = RetrofitInitialization.retroinc();
                    Call<ArrayList<ListOfCities>> call = intf.findCity(constraint.toString());
                    Response<ArrayList<ListOfCities>> arrayListResponse = null;

                    try {
                        arrayListResponse = call.execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                  ArrayList<ListOfCities> listOfCities = arrayListResponse.body();
                    if(listOfCities !=null && listOfCities.size()!=0) {
                        double lat = LocListener.lat;
                        double lon = LocListener.lon;
                        Log.e("filter2", "dgf");
                        for (ListOfCities cities : listOfCities) {
                            double distance = Math.sqrt(Math.pow(lat - cities.getGeoPosition().getLatitude(), 2) +
                                    Math.pow(lon - cities.getGeoPosition().getLongitude(), 2));
                            cities.setDistance(distance);
                        }
                        Collections.sort(listOfCities, new Comparator<ListOfCities>() {
                            @Override
                            public int compare(ListOfCities o1, ListOfCities o2) {
                                return (int) (o2.getDistance() - o1.getDistance());
                            }
                        });
                        filterResults.values = listOfCities;
                        filterResults.count = listOfCities.size();
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                if (results != null && results.count > 0) {
                    mResults = (List<ListOfCities>) results.values;
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };

    return filter;
 }

}
