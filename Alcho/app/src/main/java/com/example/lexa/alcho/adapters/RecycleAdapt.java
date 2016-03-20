package com.example.lexa.alcho.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lexa.alcho.R;
import com.example.lexa.alcho.construct.AlcoBase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 12.01.2016.
 */
public class RecycleAdapt extends RecyclerView.Adapter<RecycleAdapt.SummaHolder> {

    private List<AlcoBase> mList;
    private ItemClickListener mItemClickListener;



    public RecycleAdapt(List<AlcoBase> list) {
        mList = list;
    }

    public void addItems(List<AlcoBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final AlcoBase summa = mList.get(position);
        holder.summa.setText(summa.getName());
        int drawableId = 0;
        try {
            Class res = R.drawable.class;
            Field field = res.getField(summa.getImage());
            drawableId  = field.getInt(null);

        }
        catch (Exception e) {
            Log.e("MyTag", "Failure to get drawable id.", e);
        }

        holder.view1.setImageResource(drawableId);


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
        ImageView view1;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            summa = (TextView) itemView.findViewById(R.id.tv_summa);
            view1 = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public interface ItemClickListener {

        void onClick(AlcoBase summa);

    }

    public void setFilter(List<AlcoBase> countryModels) {
        mList = new ArrayList<>();
        mList.addAll(countryModels);
        notifyDataSetChanged();
    }
}
