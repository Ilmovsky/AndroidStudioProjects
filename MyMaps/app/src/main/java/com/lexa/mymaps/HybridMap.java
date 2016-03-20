package com.lexa.mymaps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.tileprovider.IRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Lexa on 01.02.2016.
 */
public class HybridMap extends FrameLayout {

    static public ResourceProxy resourceProxy;
    public DataSource datasource;
    List<MapsBase> values = null;
    public static MapsBase mapsBase = null;
    static MapViewLoc mv = null;

    public HybridMap(final Context context, AttributeSet attrs)
    {
        super(context, attrs);

        resourceProxy = new DefaultResourceProxyImpl(this.getContext());

        datasource = new DataSource(context);
        datasource.open();
        values = datasource.getAllMapsBase();
        for(int i = 0; i< values.size();i++){
            if(values.get(i).getId() == MainActivity.id){
                mapsBase = values.get(i);
                break;
            }
        }

        useMapsforgeTiles();
        mv.getController().setZoom(mapsBase.getZoomLevel());
        center(mapsBase.getCoordX(), mapsBase.getCoordY());
        datasource.close();

    }

    public HybridMap(final Context context)
    {
        super(context);

    }

    public void useMapsforgeTiles(){
        if(mv != null){
            removeView(mv);
            mv = null;
        }
        File mapFile = new File(Environment.getExternalStoragePublicDirectory(mapsBase.getCountry()).getPath());
        MFTileProvider provider = new MFTileProvider((IRegisterReceiver)this.getContext(), mapFile);
        mv = new MapViewLoc(getContext(), provider.getTileSource().getTileSizePixels(), resourceProxy, provider);
        mv.setMultiTouchControls(true);
        addView(mv);
    }


    public void center(double latitude, double longitude){
        GeoPoint p = new GeoPoint(latitude, longitude);
        mv.getController().setCenter(p);
    }

    public ItemizedIconOverlay markerMomentAdd(double lat, double lon,Drawable markerDrawable, ItemizedIconOverlay markersOverlay){
        if (markersOverlay != null){
            mv.getOverlays().remove(markersOverlay);
        }
        GeoPoint point = new GeoPoint(lat, lon);
        OverlayItem marker = new OverlayItem("Novoe","Novoe",point);
        marker.setMarker(markerDrawable);
        markersOverlay = new ItemizedIconOverlay(new LinkedList(), markerDrawable, null, resourceProxy);
        markersOverlay.addItem(marker);
        mv.getOverlays().add(markersOverlay);
        return markersOverlay;
    }

    public void markerCurrentDelete(OverlayItem over){
        MyOwnItemizedOverlay myOwnItemizedOverlay = (MyOwnItemizedOverlay)mv.getOverlays().get(1);
        myOwnItemizedOverlay.removeOverlay(over);
    }

    public void markerMomentDelete(ItemizedIconOverlay markersOverlay){
        if (markersOverlay != null){
            mv.getOverlays().remove(markersOverlay);
        }
    }

    public void markerAllAdd(double lat, double lon,Drawable markerDrawable, String place, String name,MyOwnItemizedOverlay markersOverlay){
        GeoPoint point = new GeoPoint(lat, lon);
        OverlayItem marker = new OverlayItem(place,name,point);
        marker.setMarker(markerDrawable);
        markersOverlay.addItem(marker);
        mv.getOverlays().add(markersOverlay);
    }

}
