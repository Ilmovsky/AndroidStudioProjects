package com.lexa.tripworld.internet_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lexa.tripworld.R;

/**
 * Created by Lexa on 19.05.2016.
 */
public class BroadcastConnection extends BroadcastReceiver {


    private OnNetReceivedListener listener = null;

    public interface OnNetReceivedListener {
        public void onNetReceived(boolean isNet);
    }

    public BroadcastConnection() {
    }

    public void setOnNetReceivedListener(Context context) {
        this.listener = (OnNetReceivedListener) context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        NetworkInfo info = (NetworkInfo) extras
                .getParcelable("networkInfo");

        NetworkInfo.State state = info.getState();

        if (state == NetworkInfo.State.CONNECTED) {
            if (listener != null) {
                listener.onNetReceived(true);
            }
            Toast.makeText(context, context.getResources().getString(R.string.Toast5), Toast.LENGTH_SHORT).show();

        } else {
            if (listener != null) {
                listener.onNetReceived(false);
            }
            Toast.makeText(context, context.getResources().getString(R.string.Toast6), Toast.LENGTH_SHORT).show();
        }

    }
}

