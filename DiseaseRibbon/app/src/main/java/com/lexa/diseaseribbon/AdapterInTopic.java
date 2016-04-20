package com.lexa.diseaseribbon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 06.04.2016.
 */
public class AdapterInTopic extends RecyclerView.Adapter<AdapterInTopic.SummaHolder> {

    private List<InTopicList> mList;
    private ItemClickListener mItemClickListener;
    Picasso mPicasso;

    public AdapterInTopic(Context context, List<InTopicList> list) {
        mList = list;
        mPicasso = Picasso.with(context);
    }

    public void addItems(List<InTopicList> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.in_topic_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {
         String summa = null;
        String name = null;
        String photoUrl = null;


            summa = mList.get(position).getSubject();
            name = mList.get(position).getUserName();
            photoUrl = mList.get(position).getPhotoUrl();

        mPicasso.load(photoUrl)
                .placeholder(R.mipmap.ic_launcher)
                .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                .centerInside()
                .into(holder.photo);

        holder.summa.setText(summa);
        holder.name.setText(name);


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
        TextView summa;
        TextView name;
        ImageView photo;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            photo = (ImageView) itemView.findViewById(R.id.person_photo);
            summa = (TextView) itemView.findViewById(R.id.tv_summa);
            name = (TextView) itemView.findViewById(R.id.person_name);
        }
    }

    public interface ItemClickListener {

        void onClick(String summa);

    }

    public void setFilter(List<InTopicList> diseaseLists) {
        mList = new ArrayList<>();
        mList.addAll(diseaseLists);
        notifyDataSetChanged();
    }
}
