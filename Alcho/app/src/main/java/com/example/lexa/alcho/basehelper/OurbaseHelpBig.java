package com.example.lexa.alcho.basehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lexa on 27.12.2015.
 */
public class OurbaseHelpBig extends SQLiteOpenHelper

{
    private static final String OURBASE_NAME = "databasealco.db";
    private static final int OURBASE_VERSION = 1;

    OurbaseHelpBig(Context context) {
        super(context, OURBASE_NAME, null, OURBASE_VERSION);
    }
    public static final String DATABASE_TABLE = "list";
    public static final String DATABASE_TABLE_A = "list_a";
    public static final String ALCHO_ID = "alcho_id";
    public static final String ALCHO_ID_A = "alcho_id1";
    public static final String ALCHO_DATE_COLUMN = "alcho_date";
    public static final String ALCHO_NAME_DATE = "alcho_namedate";
    public static final String ALCHO_NAME_COLUMN = "alcho_name";
    public static final String ALCHO_NAME1_COLUMN = "alcho_name1";
    public static final String ALCHO_DEGREE = "alcho_degrees";
    public static final String ALCHO_COLICH = "alcho_site";
    public static final String ALCHO_ID_ALL = "alcho_id_all";

    public static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " ("  + ALCHO_ID
            + " integer primary key autoincrement, " + ALCHO_DATE_COLUMN
            + " text not null, " + ALCHO_NAME_DATE
            + " text not null);";

    public static final String DATABASE_CREATE_SCRIPT_A = "create table "
            + DATABASE_TABLE_A + " ("  + ALCHO_ID_A
            + " integer primary key autoincrement, " + ALCHO_NAME_COLUMN
            + " text not null, " + ALCHO_NAME1_COLUMN
            + " text not null, " + ALCHO_DEGREE
            + " text not null, " + ALCHO_COLICH
            + " text not null, " + ALCHO_ID_ALL
            + " text not null);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_SCRIPT);
        db.execSQL(DATABASE_CREATE_SCRIPT_A);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию " + newVersion);

        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE);
        db.execSQL("DROP TABLE IF IT EXISTS " + DATABASE_TABLE_A);

        onCreate(db);
    }
}
