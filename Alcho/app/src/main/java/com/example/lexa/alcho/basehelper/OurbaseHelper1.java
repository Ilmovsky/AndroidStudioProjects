package com.example.lexa.alcho.basehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lexa on 21.12.2015.
 */
public class OurbaseHelper1 extends SQLiteOpenHelper {

    private static final String OURBASE_NAME = "mydatabase.db";
    private static final int OURBASE_VERSION = 1;

    OurbaseHelper1(Context context) {
        super(context, OURBASE_NAME, null, OURBASE_VERSION);
    }
    public static final String DATABASE_TABLE = "alcho";
    public static final String ALCHO_ID = "alcho_id";
    public static final String ALCHO_NAME_COLUMN = "alcho_name";
    public static final String ALCHO_DEGREE = "alcho_degrees";
    public static final String ALCHO_SITE = "alcho_site";
    public static final String ALCHO_IM = "alcho_im";
    public static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + ALCHO_ID
            + " integer primary key autoincrement, " + ALCHO_NAME_COLUMN
            + " text not null, " + ALCHO_DEGREE + " text not null, " + ALCHO_IM
            + " text not null, " + ALCHO_SITE + " text not null);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);

        onCreate(db);
    }
}
