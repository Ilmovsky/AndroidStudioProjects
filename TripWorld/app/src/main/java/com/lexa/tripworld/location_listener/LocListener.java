package com.lexa.tripworld.location_listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.lexa.tripworld.R;

/**
 * Created by Lexa on 19.05.2016.
 */
public class LocListener implements LocationListener {

    static public double lat = 0;
    static public double lon = 0;
    static LocationManager locationManager;
    static LocationListener locationListener;
    Context contex;

    public LocListener(Context context) {
        this.contex = context;
    }

    public static void closeManager(){
        if(locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public static void SetUpLocationListener(Context context)
    {
        locationManager = (LocationManager)
                context.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocListener(context);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 10, locationListener);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                0, 10, locationListener);

        Location local = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if(local!= null){
            lat = local.getLatitude();
            lon = local.getLongitude();
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        if(location != null){
             lat = location.getLatitude();
             lon = location.getLongitude();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

        Toast.makeText(contex, contex.getResources().getString(R.string.Toast3), Toast.LENGTH_SHORT).show();

        Location local = locationManager.getLastKnownLocation(provider);
        if(local!= null){
            lat = local.getLatitude();
            lon = local.getLongitude();
        }
    }

    @Override
    public void onProviderDisabled(String provider) {

        Toast.makeText(contex, contex.getResources().getString(R.string.Toast4), Toast.LENGTH_SHORT).show();

    }

}
