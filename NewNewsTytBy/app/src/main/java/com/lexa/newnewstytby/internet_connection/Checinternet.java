package com.lexa.newnewstytby.internet_connection;

import android.content.Context;

import com.lexa.newnewstytby.internet_connection.ServiceManager;

/**
 * Created by Lexa on 09.05.2016.
 */
public class Checinternet {

   static public boolean checkInternet(Context context) {
        ServiceManager serviceManager = new ServiceManager(context);
        if (serviceManager.isNetworkAvailable()) {
            return true;
        } else {
            return false;
        }
    }
}
