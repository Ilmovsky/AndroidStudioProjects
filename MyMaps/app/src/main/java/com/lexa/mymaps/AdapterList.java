package com.lexa.mymaps;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 19.02.2016.
 */
public class AdapterList extends RecyclerView.Adapter<AdapterList.SummaHolder> {

    private List<String> mList;
    private ItemClickListener mItemClickListener;



    public AdapterList(List<String> list) {
        mList = list;
    }

    public void addItems(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final String summa = mList.get(position);
        holder.summa.setText(summa);


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

        void onClick(String summa);

    }

    public void setFilter(List<String> countryModels) {
        mList = new ArrayList<>();
        mList.addAll(countryModels);
        notifyDataSetChanged();
    }

}


