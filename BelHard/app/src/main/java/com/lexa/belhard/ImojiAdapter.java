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
public class ImojiAdapter extends RecyclerView.Adapter<ImojiAdapter.SummaHolder> {

    private List<Integer> mList;

    private ItemClickListener mItemClickListener;

    public void setItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public ImojiAdapter(List<Integer> list) {
        mList = list;
    }

    public void addItems(List<Integer> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SummaHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emoji_adapter, parent, false);

        SummaHolder summaHolder = new SummaHolder(view);

        return summaHolder;
    }

    @Override
    public void onBindViewHolder(SummaHolder holder, int position) {

        final int text = mList.get(position);
        holder.text.setText(getEmijoByUnicode(text));

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onClick(text);
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
        TextView text;

        public SummaHolder(View itemView) {
            super(itemView);
            view = itemView;
            text = (TextView) itemView.findViewById(R.id.text_imoji);

        }
    }


    public void setFilter(List<Integer> chatBases) {
        mList = new ArrayList<>();
        mList.addAll(chatBases);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {

        void onClick(int link);

    }

        public String getEmijoByUnicode(int unicode){
        return new String(Character.toChars(unicode));
   }
}
