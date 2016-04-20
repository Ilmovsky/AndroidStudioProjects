package com.lexa.diseaseribbon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 29.03.2016.
 */
public class AdapterDisease extends RecyclerView.Adapter<AdapterDisease.SummaHolder> {

    private List<DiseaseList> mList;
    private ItemClickListener mItemClickListener;



    public AdapterDisease(List<DiseaseList> list) {
        mList = list;
    }

    public void addItems(List<DiseaseList> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {
        String summa = null;
        if(MainActivity.fragnum == 1){
        summa = mList.get(position).getDisease();}
        else{
            summa = mList.get(position).getTopic();
        }

        holder.summa.setText(summa);


        final DiseaseList finalSumma = mList.get(position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null) {
                    mItemClickListener.onClick(finalSumma);
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

        void onClick(DiseaseList summa);

    }

    public void setFilter(List<DiseaseList> diseaseLists) {
        mList = new ArrayList<>();
        mList.addAll(diseaseLists);
        notifyDataSetChanged();
    }
}
