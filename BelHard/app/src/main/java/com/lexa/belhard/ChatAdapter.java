package com.lexa.belhard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.SummaHolder> {

    private List<ChatBase> mList;

    public ChatAdapter(Context context, List<ChatBase> list) {
        mList = list;
    }

    public void addItems(List<ChatBase> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {
        String text = null;
        String date = null;
        String photoUrl = null;


        text = mList.get(position).getText();
        date = mList.get(position).getDate();
        photoUrl = mList.get(position).getPhotoUrl();



        holder.text.setText(text);
        holder.date.setText(date);
        holder.photo.setImageResource(R.drawable.monster);

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class SummaHolder extends RecyclerView.ViewHolder {

        View view;
        TextView text;
        TextView date;
        CircleImageView photo;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            photo = (CircleImageView) itemView.findViewById(R.id.person_photo);
            text = (TextView) itemView.findViewById(R.id.text_chat);
            date = (TextView) itemView.findViewById(R.id.date_chat);
        }
    }

    public void setFilter(List<ChatBase> chatBases) {
        mList = new ArrayList<>();
        mList.addAll(chatBases);
        notifyDataSetChanged();
    }
}
