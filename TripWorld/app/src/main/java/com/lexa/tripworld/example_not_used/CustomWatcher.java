package com.lexa.tripworld.example_not_used;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

/**
 * Created by Lexa on 20.05.2016.
 */
public class CustomWatcher implements TextWatcher {

    MyAsyncTask myAsyncTask;
    Context context;

    public CustomWatcher(Context context) {
       this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
       String textNow = s.toString();
        Log.e("fghhgf",textNow);
        if (myAsyncTask != null){
        myAsyncTask.cancel(true);}
        myAsyncTask = new MyAsyncTask();
        myAsyncTask.setAsyncMYAnswer(context);
        myAsyncTask.execute(textNow);

    }
}
