package com.lexa.mymaps;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lexa on 02.02.2016.
 */
public class MapFragment extends Fragment {

    AlertDialog.Builder builder;
    Dialog dlg1 = null;
    Dialog dlg2 = null;
    Dialog dlg3 = null;
    Toolbar toolbar  = null;
    Toolbar toolbar2  = null;
    public DataSource datasource;
    List<MapBase> values1 = null;
    List<KindBase> kindBases = null;
    HybridMap hybridMap =null;
    Drawable markerDrawable =null;
    Drawable markerDrawable1 =null;
    ItemizedIconOverlay markersOverlay = null;
    static String markerName = null;
    final static String MAP_CONSTANT = "map_constant";
    AppCompatActivity appCompatActivity = null;
    MapBase value1 = null;
    MyOwnItemizedOverlay markersOverlay1 =null;
    String kindMapLast = null;
    public static List<OverlayItem> mOverlays = null;
    OverlayItem overlayItem1 = null;
    InputMethodManager imm = null;
    GPSTracker gps;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }





    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View v = inflater.inflate(R.layout.map_fragment, null);
        mOverlays = new ArrayList<>();
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        markersOverlay1 = new MyOwnItemizedOverlay(this.getActivity());



        datasource = new DataSource(this.getActivity());
        datasource.open();
        values1 = datasource.getAllMapBase();
        hybridMap = new HybridMap(getActivity());
        markerDrawable = getResources().getDrawable(R.drawable.m2);
        if(values1.size() > 0){
            for(int i =0; i < values1.size(); i++){
                hybridMap.markerAllAdd(values1.get(i).getCoordX(),values1.get(i).getCoordY(),markerDrawable,values1.get(i).getKind(),values1.get(i).getPlace(),markersOverlay1);
            }
        }



        appCompatActivity = (AppCompatActivity) getActivity();
        toolbar = (Toolbar) v.findViewById(R.id.toolbar_bottom);
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().hide();
        toolbar = (Toolbar) v.findViewById(R.id.toolbar_bottom2);
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().hide();



        Button locationBtn = (Button)v.findViewById(R.id.buttonLoc);
        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View ve) {

               yourLocation(v);

            }
        });



        kindBases = datasource.getAllListBase();
        if(kindBases.size() == 0) {
            final String[] kind = getResources().getStringArray(R.array.listArray);
            for (int j = 0; j < kind.length; j++) {
                datasource.createListBase(kind[j].toUpperCase());
            }
            kindBases = datasource.getAllListBase();
        }
        kindMapLast = kindBases.get(0).getKind();
        markerName = MAP_CONSTANT;



        if(MainActivity.clickOnListPlace){
            clickOnMarker(v);
        }



        HybridMap.mv.addTapListener(new MapViewLoc.OnTapListener() {
            @Override
            public void onMapTapped(GeoPoint geoPoint) {
            }
            @Override
            public void onMapTapped(final Location location) {
                if (!markerName.equalsIgnoreCase(MAP_CONSTANT)) {
                     clickOnMarker(v);
                } else {
                    clickOnMap(v, location);
                }
            }
        });

        return v;
    }










    public void yourLocation(View v){

        gps = new GPSTracker(getActivity());

        if(gps.canGetLocation()) {
            appCompatActivity.getSupportActionBar().hide();

            final double latitude = gps.getLatitude();
            final double longitude = gps.getLongitude();

            toolbar2 = (Toolbar) v.findViewById(R.id.toolbar_bottom);
            appCompatActivity.setSupportActionBar(toolbar2);
            appCompatActivity.getSupportActionBar().show();

            TextView txtTool = (TextView) v.findViewById(R.id.textTool);
            txtTool.setText(getResources().getString(R.string.Name10) + " " + latitude + ", " + longitude);

            markerDrawable1 = getResources().getDrawable(R.drawable.manthis);
            markersOverlay = hybridMap.markerMomentAdd(latitude, longitude, markerDrawable1, markersOverlay);
            hybridMap.mv.getController().animateTo(new GeoPoint(latitude,longitude));

            Button btnTool = (Button) v.findViewById(R.id.buttonTool);
            Button btnTool2 = (Button) v.findViewById(R.id.buttonTool2);

            btnTool.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPlaceInfDialog(latitude, longitude);
                    appCompatActivity.getSupportActionBar().hide();
                }
            });

            btnTool2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appCompatActivity.getSupportActionBar().hide();
                }
            });

        } else {
            gps.showSettingsAlert();
        }

    }







    @Override
    public void onPause() {
        super.onPause();
        double markerLat = (double)(HybridMap.mv.getMapCenter().getLatitudeE6()/1e6);
        double markerLong = (double)(HybridMap.mv.getMapCenter().getLongitudeE6()/1e6);
        HybridMap.mapsBase.setCoordX(markerLat);
        HybridMap.mapsBase.setCoordY(markerLong);
        HybridMap.mapsBase.setZoomLevel(hybridMap.mv.getZoomLevel());
        datasource.updateMapsBase(HybridMap.mapsBase);
        datasource.close();
    }







    @Override
    public  void onResume() {
       super.onResume();
        datasource.open();
    }





    public  void clickOnMarker(View v){

        hybridMap.markerMomentDelete(markersOverlay);

        appCompatActivity.getSupportActionBar().hide();

        toolbar = (Toolbar) v.findViewById(R.id.toolbar_bottom2);
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().show();

        TextView txtTool2 = (TextView) v.findViewById(R.id.textTool2);
        TextView txtTool3 = (TextView) v.findViewById(R.id.textTool3);
        TextView txtTool4 = (TextView) v.findViewById(R.id.textTool4);

        boolean clickOnListPlace1 = false;

        if (MainActivity.clickOnListPlace){
            GeoPoint geoPoints = new GeoPoint(HybridMap.mapsBase.getCoordX(),HybridMap.mapsBase.getCoordY());
            for(MapBase value : values1){
                if (geoPoints.equals(new GeoPoint(value.getCoordX(),value.getCoordY()))) {
                    txtTool4.setText(value.getInform());
                    value1 = value;
                    break;
                }
            }
            for(OverlayItem overlayItem : mOverlays){
                if(overlayItem.getPoint().equals(geoPoints)){
                 overlayItem1 = overlayItem;
                    break;
                }
            }
            MainActivity.clickOnListPlace = false;
            clickOnListPlace1 = true;
        }
else {
            for (MapBase value : values1) {
                if (MyOwnItemizedOverlay.pointLatLon.equals(new GeoPoint(value.getCoordX(), value.getCoordY()))) {
                    txtTool4.setText(value.getInform());
                    value1 = value;
                    hybridMap.mv.getController().animateTo(MyOwnItemizedOverlay.pointLatLon);
                    markerName = MAP_CONSTANT;
                    break;
                }
            }
        }

        txtTool2.setText(value1.getPlace());
        txtTool3.setText(value1.getKind());

        Button btnMarker = (Button) v.findViewById(R.id.buttonTool3);
        Button btnMarker2 = (Button) v.findViewById(R.id.buttonTool4);
        btnMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMapMarker(value1);
                appCompatActivity.getSupportActionBar().hide();
            }
        });
        final boolean finalClickOnListPlace = clickOnListPlace1;
        btnMarker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(finalClickOnListPlace){
                  deleteMapMarker(overlayItem1, value1);
              }
               else{
                  deleteMapMarker(MyOwnItemizedOverlay.item1, value1);
              }
                appCompatActivity.getSupportActionBar().hide();
            }
        });
    }








    public void deleteMapMarker(OverlayItem item,MapBase mapBase){
        hybridMap.markerCurrentDelete(item);
        datasource.deleteMapBase(mapBase);
        values1 = datasource.getAllMapBase();
        hybridMap.mv.getController().animateTo(new GeoPoint(mapBase.getCoordX()+ 0.0001,mapBase.getCoordY()));
    }









    public void changeMapMarker(final MapBase mapBase){
        dlg2 = new Dialog(this.getActivity());
        dlg2.setContentView(R.layout.dialog_change_place);
        dlg2.setTitle(mapBase.getPlace());
        dlg2.setCancelable(true);

        final Button btn3 = (Button)dlg2.findViewById(R.id.button7);
        Button btn4 = (Button)dlg2.findViewById(R.id.button8);

        final TextView kindText = (TextView)dlg2.findViewById(R.id.txtView2);
        final EditText editText2 = (EditText)dlg2.findViewById(R.id.editText5);
        final EditText editText3 = (EditText)dlg2.findViewById(R.id.editText6);
        kindText.setText(mapBase.getKind());
        editText2.setText(mapBase.getPlace());
        editText3.setText(mapBase.getInform());

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText2.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.Toast6, Toast.LENGTH_LONG).show();
                    imm.hideSoftInputFromWindow(btn3.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    if (editText3.getText().length() == 0) {
                        editText3.setText(getResources().getString(R.string.Name4));
                    }
                    if (!kindText.getText().toString().equalsIgnoreCase(mapBase.getKind())) {
                        mapBase.setKind(kindText.getText().toString());
                        datasource.updateMapBaseKind(mapBase);
                    }
                    if (!editText2.getText().toString().equalsIgnoreCase(mapBase.getPlace())) {
                        mapBase.setPlace(editText2.getText().toString());
                        datasource.updateMapBasePlace(mapBase);
                    }
                    if (!editText3.getText().toString().equalsIgnoreCase(mapBase.getInform())) {
                        mapBase.setInform(editText3.getText().toString());
                        datasource.updateMapBaseInform(mapBase);
                    }
                    values1 = datasource.getAllMapBase();
                    dlg2.dismiss();
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg2.dismiss();
            }
        });

        kindText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateListKinds(kindText);
            }
        });

        dlg2.show();
        ((TextView) dlg2.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
    }











    public void clickOnMap(View v, final Location location){

        appCompatActivity.getSupportActionBar().hide();

        toolbar = (Toolbar) v.findViewById(R.id.toolbar_bottom);
        appCompatActivity.setSupportActionBar(toolbar);
        appCompatActivity.getSupportActionBar().show();

        TextView txtTool = (TextView) v.findViewById(R.id.textTool);
        txtTool.setText(getResources().getString(R.string.Name11) + location.getLatitude() + ", " + location.getLongitude());

        markerDrawable1 = getResources().getDrawable(R.drawable.ar);
        markersOverlay = hybridMap.markerMomentAdd(location.getLatitude(), location.getLongitude(), markerDrawable1, markersOverlay);

        GeoPoint p = new GeoPoint(location.getLatitude(), location.getLongitude());
        hybridMap.mv.getController().animateTo(p);

        Button btnTool = (Button) v.findViewById(R.id.buttonTool);
        Button btnTool2 = (Button) v.findViewById(R.id.buttonTool2);

        btnTool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPlaceInfDialog(location.getLatitude(), location.getLongitude());
                appCompatActivity.getSupportActionBar().hide();
            }
        });

        btnTool2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appCompatActivity.getSupportActionBar().hide();
            }
        });
    }








    public void addPlaceInfDialog(final double lat, final double lon) {
        dlg1 = new Dialog(this.getActivity());
        dlg1.setContentView(R.layout.dialog_add_place_2);
        dlg1.setTitle(getResources().getString(R.string.Name3));
        dlg1.setCancelable(true);

        final Button btn3 = (Button)dlg1.findViewById(R.id.button5);
        Button btn4 = (Button)dlg1.findViewById(R.id.button6);

        final TextView kindText1 = (TextView)dlg1.findViewById(R.id.txtView);
        final EditText editText2 = (EditText)dlg1.findViewById(R.id.editText2);
        final EditText editText3 = (EditText)dlg1.findViewById(R.id.editText3);

        kindText1.setText(kindMapLast);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(editText2.getText().length() == 0){
                        Toast.makeText(getActivity(), R.string.Toast6, Toast.LENGTH_LONG).show();
                        imm.hideSoftInputFromWindow(btn3.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                    else{
                        if(editText3.getText().length() == 0){
                            editText3.setText(getResources().getString(R.string.Name4));
                        }
                        hybridMap.markerMomentDelete(markersOverlay);
                        datasource.createMapBase(HybridMap.mapsBase.getName(), kindText1.getText().toString(), editText2.getText().toString(), editText3.getText().toString(), lat, lon);
                        values1 = datasource.getAllMapBase();
                        hybridMap.markerAllAdd(lat, lon, markerDrawable, kindText1.getText().toString(), editText2.getText().toString(),markersOverlay1);
                        hybridMap.mv.getController().animateTo(new GeoPoint(lat + 0.00001, lon + 0.00001));
                        dlg1.dismiss();
                    }
                }

        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlg1.dismiss();
            }
        });

        kindText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateListKinds(kindText1);
            }
        });

        dlg1.show();
        ((TextView) dlg1.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
    }








    public Dialog onCreateListKinds( final TextView kindText1){
        final String [] kinds = new String [kindBases.size()];
        for (int i =0; i < kindBases.size(); i++){
            kinds [i] = kindBases.get(i).getKind();
        }

        builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle(getResources().getString(R.string.Name5))
                .setPositiveButton(getResources().getString(R.string.Button),
                        new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addMapKind(kindText1);
                            }
                        })
                .setNegativeButton(getResources().getString(R.string.Button2),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
        builder.setItems(kinds, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                kindText1.setText(kinds[item]);
                kindMapLast = kinds [item];
            }
        });
        builder.setCancelable(true);
        return  builder.show();
    }








    public void addMapKind(final TextView kindText1){
        dlg3 = new Dialog(this.getActivity());
        dlg3.setContentView(R.layout.dialog_add_kind);
        dlg3.setTitle(getResources().getString(R.string.Name3));
        dlg3.setCancelable(true);

        final Button btn3 = (Button)dlg3.findViewById(R.id.button11);
        final Button btn4 = (Button)dlg3.findViewById(R.id.button12);

        final EditText editText2 = (EditText)dlg3.findViewById(R.id.editText10);


        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText2.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.Toast8, Toast.LENGTH_LONG).show();
                    imm.hideSoftInputFromWindow(btn3.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                } else {
                    int isMarker = 0;
                    for (KindBase kindBase : kindBases) {
                        if (editText2.getText().toString().toUpperCase().equalsIgnoreCase(kindBase.getKind())) {
                            Toast.makeText(getActivity(), R.string.Toast9, Toast.LENGTH_LONG).show();
                            imm.hideSoftInputFromWindow(btn3.getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                          isMarker = 1;
                            break;
                        }
                    }
                    if(isMarker==0) {
                        datasource.createListBase(editText2.getText().toString().toUpperCase());
                        kindBases = datasource.getAllListBase();
                        imm.hideSoftInputFromWindow(btn3.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        onCreateListKinds(kindText1);
                        dlg3.dismiss();
                    }
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateListKinds(kindText1);
                imm.hideSoftInputFromWindow(btn4.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                dlg3.dismiss();
            }
        });

        dlg3.show();
        ((TextView) dlg3.findViewById(android.R.id.title)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 21);
    }

}
