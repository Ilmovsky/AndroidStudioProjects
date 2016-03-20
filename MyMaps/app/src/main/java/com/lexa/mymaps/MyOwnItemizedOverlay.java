package com.lexa.mymaps;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 16.02.2016.
 */
public class MyOwnItemizedOverlay extends ItemizedIconOverlay<OverlayItem> {
    protected Context mContext;
    public static GeoPoint pointLatLon = null;
    public static String placeMarker = null;
    public static OverlayItem item1 = null;


    public MyOwnItemizedOverlay(final Context context) {

        super(context, MapFragment.mOverlays, new OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                return false;
            }

            @Override
            public boolean onItemLongPress(final int index, final OverlayItem item) {
                return false;
            }
        });
        // TODO Auto-generated constructor stub
        mContext = context;

    }

    public void removeOverlay(OverlayItem overlay) {
        MapFragment.mOverlays.remove(overlay);
        populate();
    }

    @Override
    protected boolean onSingleTapUpHelper(final int index, final OverlayItem item, final MapView mapView) {

        item1 = item;
        MapFragment.markerName = item.getSnippet();
        pointLatLon = item.getPoint();
        placeMarker = item.getTitle();
        return true;
    }
}
