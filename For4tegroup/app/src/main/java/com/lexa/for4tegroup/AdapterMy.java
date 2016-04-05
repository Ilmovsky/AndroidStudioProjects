package com.lexa.for4tegroup;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 05.04.2016.
 */
public class AdapterMy extends RecyclerView.Adapter<AdapterMy.SummaHolder> {

    private List<String> mList;
    private ItemClickListener mItemClickListener;
    static int mCheckedPosition =-1;

    public AdapterMy(List<String> list) {
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
                .inflate(R.layout.image_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(final SummaHolder holder, final int position) {

        ImageManager IM = new ImageManager();


        String summa = null;

        summa = mList.get(position);


        holder.summa.setImageResource(R.mipmap.ic_launcher);
        IM.fetchImage(summa, holder.summa);

        holder.checkBox.setChecked(position == mCheckedPosition);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position == mCheckedPosition) {
                    holder.checkBox.setChecked(false);
                    mCheckedPosition = -1;
                } else {
                    mCheckedPosition = position;
                    notifyItemRangeChanged(0, mList.size());
                }

            }

    });



        final String finalSumma = summa;
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
        ImageView summa;
        CheckBox checkBox;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            summa = (ImageView) itemView.findViewById(R.id.imageView1);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
        }
    }

    public interface ItemClickListener {

        void onClick(String summa);

    }

    public void setFilter(List<String> imageViews) {
        mList = new ArrayList<>();
        mList.addAll(imageViews);
        notifyDataSetChanged();
    }
}

