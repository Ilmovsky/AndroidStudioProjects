package com.lexa.tripworld.location_listener;

import android.content.Context;
import android.os.Looper;

import com.lexa.tripworld.location_listener.LocListener;

/**
 * Created by Lexa on 19.05.2016.
 */
public class MyLocationThred extends Thread{

    Context context;

    public MyLocationThred(Context context) {
      this.context = context;
    }

    @Override
    public void run() {
        Looper.prepare();
          LocListener.SetUpLocationListener(context);
        Looper.loop();
    }

}
