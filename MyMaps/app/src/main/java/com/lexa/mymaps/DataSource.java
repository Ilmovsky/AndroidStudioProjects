package com.lexa.mymaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 02.02.2016.
 */
public class DataSource {
    private SQLiteDatabase database;
    private OurBaseHelper dbHelper;
    private String[] allColumns = { OurBaseHelper.MAP_ID,OurBaseHelper.MAP_COUNTRY,
            OurBaseHelper.MAP_KIND,OurBaseHelper.MAP_PLACE,OurBaseHelper.MAP_INFORM,
             OurBaseHelper.MAP_COORDX,OurBaseHelper.MAP_COORDY};
    private String[] allColumns1 = { OurBaseHelper.MAPS_ID,OurBaseHelper.MAP_COUNTRY,
            OurBaseHelper.MAPS_NAME,OurBaseHelper.MAP_COORDX,OurBaseHelper.MAP_COORDY,OurBaseHelper.MAPS_ZOOM};
    private String[] allColumns2 = { OurBaseHelper.LIST_ID,OurBaseHelper.LIST_KIND};

    public DataSource(Context context) {
        dbHelper = new OurBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public MapBase createMapBase(String country,String kind,String place,String inform,double coordX,double coordY) {
        ContentValues values = new ContentValues();
        values.put(OurBaseHelper.MAP_COUNTRY, country);
        values.put(OurBaseHelper.MAP_KIND, kind);
        values.put(OurBaseHelper.MAP_PLACE, place);
        values.put(OurBaseHelper.MAP_INFORM, inform);
        values.put(OurBaseHelper.MAP_COORDX, coordX);
        values.put(OurBaseHelper.MAP_COORDY, coordY);
        long insertId = database.insert(OurBaseHelper.DATABASE_TABLE, null,
                values);
        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE,
                allColumns, OurBaseHelper.MAP_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        MapBase newD = cursorToMapBase(cursor);
        cursor.close();
        return newD;
    }

    public void updateMapBaseKind(MapBase mapBase){
        ContentValues  mapLat = new ContentValues();
        mapLat.put("MAP_KIND",mapBase.getKind());
        database.update(OurBaseHelper.DATABASE_TABLE,
                mapLat,
                "MAP_ID = ?",
                new String[]{Long.toString(mapBase.getId())});
    }

    public void updateMapBasePlace(MapBase mapBase){
        ContentValues  mapLat = new ContentValues();
        mapLat.put("MAP_PLACE", mapBase.getPlace());
        database.update(OurBaseHelper.DATABASE_TABLE,
                mapLat,
                "MAP_ID = ?",
                new String[]{Long.toString(mapBase.getId())});
    }

    public void updateMapBaseInform(MapBase mapBase){
        ContentValues  mapLat = new ContentValues();
        mapLat.put("MAP_INFORM",mapBase.getInform());
        database.update(OurBaseHelper.DATABASE_TABLE,
                mapLat,
                "MAP_ID = ?",
                new String[]{Long.toString(mapBase.getId())});
    }

    public void deleteMapBase(MapBase mapBase) {
        long id = mapBase.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(OurBaseHelper.DATABASE_TABLE, OurBaseHelper.MAP_ID + " = "
                + id, null);
    }

    public List<MapBase> getAllMapBase() {
        List<MapBase> maps = new ArrayList<>();

        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE,
                allColumns, null, null, null, null, OurBaseHelper.MAP_PLACE);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MapBase map = cursorToMapBase(cursor);
            maps.add(map);
            cursor.moveToNext();
        }

        cursor.close();
        return maps;
    }

    private MapBase cursorToMapBase(Cursor cursor) {
        MapBase map = new MapBase();
        map.setId(cursor.getLong(0));
        map.setCountry(cursor.getString(1));
        map.setKind(cursor.getString(2));
        map.setPlace(cursor.getString(3));
        map.setInform(cursor.getString(4));
        map.setCoordX(cursor.getDouble(5));
        map.setCoordY(cursor.getDouble(6));
        return map;
    }

    public MapsBase createMapsBase(String country,String name,double coordX,double coordY,int zoom) {
        ContentValues values = new ContentValues();
        values.put(OurBaseHelper.MAP_COUNTRY, country);
        values.put(OurBaseHelper.MAPS_NAME, name);
        values.put(OurBaseHelper.MAP_COORDX, coordX);
        values.put(OurBaseHelper.MAP_COORDY, coordY);
        values.put(OurBaseHelper.MAPS_ZOOM, zoom);
        long insertId = database.insert(OurBaseHelper.DATABASE_TABLE1, null,
                values);
        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE1,
                allColumns1, OurBaseHelper.MAPS_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        MapsBase newD = cursorToMapsBase(cursor);
        cursor.close();
        return newD;
    }

    public void updateMapsBase(MapsBase mapsBase){
        ContentValues  mapLat = new ContentValues();
        ContentValues  mapLong = new ContentValues();
        ContentValues  mapZoom = new ContentValues();
        mapLat.put("MAP_COORDX",mapsBase.getCoordX());
        mapLong.put("MAP_COORDY",mapsBase.getCoordY());
        mapZoom.put("MAPS_ZOOM",mapsBase.getZoomLevel());
        database.update(OurBaseHelper.DATABASE_TABLE1,
                mapLat,
                "MAPS_ID = ?",
                new String[]{Long.toString(mapsBase.getId())});
        database.update(OurBaseHelper.DATABASE_TABLE1,
                mapLong,
                "MAPS_ID = ?",
                new String[]{Long.toString(mapsBase.getId())});
        database.update(OurBaseHelper.DATABASE_TABLE1,
                mapZoom,
                "MAPS_ID = ?",
                new String[]{Long.toString(mapsBase.getId())});
    }

    public void deleteMapsBase(MapsBase mapBase) {
        long id = mapBase.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(OurBaseHelper.DATABASE_TABLE1, OurBaseHelper.MAPS_ID + " = "
                + id, null);
    }

    public List<MapsBase> getAllMapsBase() {
        List<MapsBase> maps = new ArrayList<>();

        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE1,
                allColumns1, null, null, null, null, OurBaseHelper.MAPS_NAME);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MapsBase map = cursorToMapsBase(cursor);
            maps.add(map);
            cursor.moveToNext();
        }

        cursor.close();
        return maps;
    }

    private MapsBase cursorToMapsBase(Cursor cursor) {
        MapsBase map = new MapsBase();
        map.setId(cursor.getLong(0));
        map.setCountry(cursor.getString(1));
        map.setName(cursor.getString(2));
        map.setCoordX(cursor.getDouble(3));
        map.setCoordY(cursor.getDouble(4));
        map.setZoomLevel(cursor.getInt(5));
        return map;
    }

    public KindBase createListBase(String kind) {
        ContentValues values = new ContentValues();
        values.put(OurBaseHelper.LIST_KIND, kind);
        long insertId = database.insert(OurBaseHelper.DATABASE_TABLE2, null,
                values);
        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE2,
                allColumns2, OurBaseHelper.LIST_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        KindBase newD = cursorToListBase(cursor);
        cursor.close();
        return newD;
    }

    public void deleteListBase(KindBase kindBase) {
        long id = kindBase.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(OurBaseHelper.DATABASE_TABLE2, OurBaseHelper.LIST_ID + " = "
                + id, null);
    }

    public List<KindBase> getAllListBase() {
        List<KindBase> kindBases = new ArrayList<>();

        Cursor cursor = database.query(OurBaseHelper.DATABASE_TABLE2,
                allColumns2, null, null, null, null, OurBaseHelper.LIST_KIND);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            KindBase kindBase = cursorToListBase(cursor);
            kindBases.add(kindBase);
            cursor.moveToNext();
        }

        cursor.close();
        return kindBases;
    }

    private KindBase cursorToListBase(Cursor cursor) {
        KindBase list = new KindBase();
        list.setId(cursor.getLong(0));
        list.setKind(cursor.getString(1));
        return list;
    }
}
