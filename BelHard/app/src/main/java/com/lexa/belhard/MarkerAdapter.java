package com.lexa.belhard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lexa on 25.05.2016.
 */
public class MarkerAdapter extends RecyclerView.Adapter<MarkerAdapter.SummaHolder> {

    private List<MarketBase> mList;

    public MarkerAdapter(List<MarketBase> list) {
        mList = list;
    }

    public void addItems(List<MarketBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.market_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {
        String text = null;
        String writer = null;
        Integer photoUrl = null;
        Integer prise = null;

        text = mList.get(position).getCollection();
        writer = mList.get(position).getWriter();
        photoUrl= mList.get(position).getUrl();
        prise = mList.get(position).getPrise();

        Log.e("sdsfsf",text);

        holder.col.setText(text);
        holder.wri.setText(writer);
        holder.imo.setText(getEmijoByUnicode(photoUrl));
        holder.pri.setText(String.valueOf(prise));

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class SummaHolder extends RecyclerView.ViewHolder {

        View view;
        TextView imo;
        TextView col;
        TextView wri;
        TextView pri;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            imo = (TextView) itemView.findViewById(R.id.image_mark);
            col = (TextView) itemView.findViewById(R.id.collect_mark2);
            wri = (TextView) itemView.findViewById(R.id.writer_mark);
            pri = (TextView) itemView.findViewById(R.id.prise_mark);
        }
    }

    public void setFilter(List<MarketBase> chatBases) {
        mList = new ArrayList<>();
        mList.addAll(chatBases);
        notifyDataSetChanged();
    }

    public String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}
