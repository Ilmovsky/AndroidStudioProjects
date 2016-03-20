package com.lexa.mymaps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lexa on 02.02.2016.
 */
public class OurBaseHelper extends SQLiteOpenHelper {

    private static final String OURBASE_NAME = "mydatabase.db";
    private static final int OURBASE_VERSION = 1;

    OurBaseHelper(Context context) {
        super(context, OURBASE_NAME, null, OURBASE_VERSION);
    }
    public static final String DATABASE_TABLE = "map";
    public static final String DATABASE_TABLE1 = "maps";
    public static final String DATABASE_TABLE2 = "list";
    public static final String MAP_ID = "map_id";
    public static final String MAPS_NAME = "maps_name";
    public static final String MAPS_ID = "maps_id";
    public static final String MAP_COUNTRY = "map_country";
    public static final String MAP_KIND = "map_kind";
    public static final String MAP_PLACE = "map_place";
    public static final String MAP_INFORM = "map_inform";
    public static final String MAP_COORDX = "map_coordx";
    public static final String MAP_COORDY = "map_coordy";
    public static final String MAPS_ZOOM = "maps_zoom";
    public static final String LIST_ID = "list_id";
    public static final String LIST_KIND = "list_kind";


    public static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + MAP_ID
            + " integer primary key autoincrement, " + MAP_COUNTRY
            + " text not null, " + MAP_KIND + " text not null, " + MAP_PLACE
            + " text not null, " + MAP_INFORM + " text not null, " + MAP_COORDX
            + " text not null, " + MAP_COORDY + " text not null);";

    public static final String DATABASE_CREATE_SCRIPT1 = "create table "
            + DATABASE_TABLE1 + " (" + MAPS_ID
            + " integer primary key autoincrement, " + MAP_COUNTRY
            + " text not null, " + MAPS_NAME + " text not null, "
            + MAP_COORDX + " text not null, "
            + MAP_COORDY + " text not null, "
            + MAPS_ZOOM + " text not null);";

    public static final String DATABASE_CREATE_SCRIPT2 = "create table "
            + DATABASE_TABLE2 + " (" + LIST_ID
            + " integer primary key autoincrement, "
            + LIST_KIND + " text not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_SCRIPT);
        db.execSQL(DATABASE_CREATE_SCRIPT1);
        db.execSQL(DATABASE_CREATE_SCRIPT2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE1);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE2);

        onCreate(db);
    }
}
