package com.example.igor.les4;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by USER on 16.11.2015.
 */
public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;
    private LayoutInflater mLayoutInflater; // чтобы получить объектную модель из xml

    static class ViewHolder {
        public TextView mText;
    }

    public MyAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList == null ? null : mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            rowView = mLayoutInflater.inflate(R.layout.list_element, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mText = (TextView) rowView.findViewById(R.id.textView);
            rowView.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();
        String s = mList.get(position);
        holder.mText.setText(s);

        return rowView;
        /**
        View view = mLayoutInflater.inflate(R.layout.list_element, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(mList.get(position));

        Log.d(this.getClass().getSimpleName(), "getView position = " + position);

        return view;
         */
    }
}
