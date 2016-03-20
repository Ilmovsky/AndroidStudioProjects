package com.example.lexa.alcho.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.ProsmotrAlcoDay;
import com.example.lexa.alcho.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 12.01.2016.
 */
public class RecycleAdapterDay extends RecyclerView.Adapter<RecycleAdapterDay.SummaHolder> {

    private List<VvodAlchoBase> mList;
    private ItemClickListener mItemClickListener;

    public RecycleAdapterDay(List<VvodAlchoBase> list) {
        mList = list;
    }

    public void addItems(List<VvodAlchoBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_day, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final VvodAlchoBase summa = mList.get(position);

        holder.name.setText(ProsmotrAlcoDay.n + " " + summa.getName());
        holder.name2.setText(ProsmotrAlcoDay.n1 + " " + summa.getName1());
        holder.degree.setText(ProsmotrAlcoDay.d + " " + Double.toString(summa.getDegrees()) + " " + ProsmotrAlcoDay.p);
        holder.colic.setText(ProsmotrAlcoDay.c + " " + Integer.toString(summa.getColich()) + " " + ProsmotrAlcoDay.m);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
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
        TextView name;
        TextView name2;
        TextView degree;
        TextView colic;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            name = (TextView) itemView.findViewById(R.id.tv_summa);
            name2 = (TextView) itemView.findViewById(R.id.tv_summa1);
            degree = (TextView) itemView.findViewById(R.id.tv_summa2);
            colic = (TextView) itemView.findViewById(R.id.tv_summa3);

        }
    }

    public interface ItemClickListener {

        void onClick(VvodAlchoBase summa);

    }

    public void setFilter(List<VvodAlchoBase> countryModels) {
        mList = new ArrayList<>();
        mList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
