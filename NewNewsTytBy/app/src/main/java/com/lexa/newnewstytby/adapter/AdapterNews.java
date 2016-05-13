package com.lexa.newnewstytby.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lexa.newnewstytby.R;
import com.lexa.newnewstytby.ormLite.BaseClass;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 05.05.2016.
 */
public class AdapterNews  extends RecyclerView.Adapter<AdapterNews.SummaHolder> {

    private List<BaseClass> mList;
    private ItemClickListener mItemClickListener;
    Picasso mPicasso;

    public AdapterNews(Context context,List<BaseClass> list) {
        mList = list;
        mPicasso = Picasso.with(context);
    }

    public void addItems(List<BaseClass> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_news, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {
        String date = null;
        String name = null;
        String photoUrl = null;


        date = mList.get(position).getDate();
        name = mList.get(position).getName();
        photoUrl = mList.get(position).getPhoto();

        mPicasso.load(photoUrl)
                .placeholder(R.mipmap.tutby)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .centerInside()
                .into(holder.photo);

        holder.date.setText(date);
        holder.name.setText(name);


        final String link = mList.get(position).getLink();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null) {
                    mItemClickListener.onClick(link);
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
        TextView date;
        TextView name;
        ImageView photo;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            photo = (ImageView) itemView.findViewById(R.id.person_photo);
            date = (TextView) itemView.findViewById(R.id.tv_summa);
            name = (TextView) itemView.findViewById(R.id.person_name);
        }
    }

    public interface ItemClickListener {

        void onClick(String link);

    }

    public void setFilter(List<BaseClass> needObjectNews) {
        mList = new ArrayList<>();
        mList.addAll(needObjectNews);
        notifyDataSetChanged();
    }
}
