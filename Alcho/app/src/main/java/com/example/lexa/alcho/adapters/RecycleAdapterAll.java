package com.example.lexa.alcho.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lexa.alcho.construct.BigBase;
import com.example.lexa.alcho.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 12.01.2016.
 */
public class RecycleAdapterAll extends RecyclerView.Adapter<RecycleAdapterAll.SummaHolder> {

    private List<BigBase> mList;
    private ItemClickListener mItemClickListener;



    public RecycleAdapterAll(List<BigBase> list) {
        mList = list;
    }

    public void addItems(List<BigBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_all, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final BigBase summa = mList.get(position);
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

        void onClick(BigBase summa);

    }

    public void setFilter(List<BigBase> countryModels) {
        mList = new ArrayList<>();
        mList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
