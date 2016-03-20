package com.lexa.mymaps;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 02.02.2016.
 */
public class AdapterMaps extends RecyclerView.Adapter<AdapterMaps.SummaHolder> {

    private List<MapsBase> mList;
    private ItemClickListener mItemClickListener;



    public AdapterMaps(List<MapsBase> list) {
        mList = list;
    }

    public void addItems(List<MapsBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.maps_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final MapsBase summa = mList.get(position);
        holder.summa.setText(summa.getName());


        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null) {
                    mItemClickListener.onClick(summa);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class SummaHolder extends RecyclerView.ViewHolder {

        View view;
        TextView summa;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            summa = (TextView) itemView.findViewById(R.id.tv_summa);
        }
    }

    public interface ItemClickListener {

        void onClick(MapsBase summa);

    }

    public void setFilter(List<MapsBase> countryModels) {
        mList = new ArrayList<>();
        mList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
