package com.example.lexa.alcho.basehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lexa.alcho.construct.BigBase;
import com.example.lexa.alcho.construct.VvodAlchoBase;
import com.example.lexa.alcho.basehelper.OurbaseHelpBig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 27.12.2015.
 */
public class DataSour {
    private SQLiteDatabase database;
    private static OurbaseHelpBig dbHelper;
    private String[] allColumns = {OurbaseHelpBig.ALCHO_ID, OurbaseHelpBig.ALCHO_DATE_COLUMN, OurbaseHelpBig.ALCHO_NAME_DATE};
    private String[] allColumnsA = {OurbaseHelpBig.ALCHO_ID_A, OurbaseHelpBig.ALCHO_NAME_COLUMN, OurbaseHelpBig.ALCHO_NAME1_COLUMN,
            OurbaseHelpBig.ALCHO_DEGREE, OurbaseHelpBig.ALCHO_COLICH, OurbaseHelpBig.ALCHO_ID_ALL};

    public DataSour(Context context) {
        dbHelper = new OurbaseHelpBig(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BigBase createAlcoBase( String date, String name) {
        ContentValues values = new ContentValues();
        values.put(OurbaseHelpBig.ALCHO_DATE_COLUMN, date);
        values.put(OurbaseHelpBig.ALCHO_NAME_DATE, name);
        long insertId = database.insert(OurbaseHelpBig.DATABASE_TABLE, null,
                values);
        Cursor cursor = database.query(OurbaseHelpBig.DATABASE_TABLE,
                allColumns, OurbaseHelpBig.ALCHO_ID + " = " + insertId, null,
                null, null, null);
        BigBase newD = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                newD = cursorToAlcoBase(cursor);
            }
            cursor.close();
        }
        return newD;
    }

    public VvodAlchoBase createAlcoBaseA(String name, String name1, double degree, int colich, long idAll) {
        ContentValues values = new ContentValues();
        values.put(OurbaseHelpBig.ALCHO_NAME_COLUMN, name);
        values.put(OurbaseHelpBig.ALCHO_NAME1_COLUMN, name1);
        values.put(OurbaseHelpBig.ALCHO_DEGREE, degree);
        values.put(OurbaseHelpBig.ALCHO_COLICH, colich);
        values.put(OurbaseHelpBig.ALCHO_ID_ALL, idAll);
        long insertId = database.insert(OurbaseHelpBig.DATABASE_TABLE_A, null,
                values);
        Cursor cursor = database.query(OurbaseHelpBig.DATABASE_TABLE_A,
                allColumnsA, OurbaseHelpBig.ALCHO_ID_A + " = " + insertId, null,
                null, null, null);
        VvodAlchoBase newD = null;
        if (cursor != null) {
            while (cursor.moveToNext()) {
                newD = cursorToAlcoBaseA(cursor);
            }
            cursor.close();
        }
        return newD;
    }

    public void deleteAlcoBase(BigBase alcoBase) {
        long id = alcoBase.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(OurbaseHelpBig.DATABASE_TABLE, OurbaseHelpBig.ALCHO_ID + " = "
                + id, null);
    }

    public void deleteAlcoBaseA(VvodAlchoBase alcoBase) {
        long id = alcoBase.getId();
        System.out.println("Comment deleted with id: " + id);
        database.delete(OurbaseHelpBig.DATABASE_TABLE_A, OurbaseHelpBig.ALCHO_ID_A + " = "
                + id, null);
    }

    public List<BigBase> getAllAlcoBase() {
        List<BigBase> alcos = new ArrayList<BigBase>();

        Cursor cursor = database.query(OurbaseHelpBig.DATABASE_TABLE,
                allColumns, null, null, null, null, OurbaseHelpBig.ALCHO_ID + " DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BigBase alco = cursorToAlcoBase(cursor);
            alcos.add(alco);
            cursor.moveToNext();
        }
        cursor.close();
        return alcos;
    }

    public List<VvodAlchoBase> getAllAlcoBaseA() {
        List<VvodAlchoBase> alcos = new ArrayList<VvodAlchoBase>();

        Cursor cursor = database.query(OurbaseHelpBig.DATABASE_TABLE_A,
                allColumnsA, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            VvodAlchoBase alco = cursorToAlcoBaseA(cursor);
            alcos.add(alco);
            cursor.moveToNext();
        }

        cursor.close();
        return alcos;
    }

//    public List<VvodAlchoBase> getOneAlcoBaseA() {
//        List<VvodAlchoBase> alcos = new ArrayList<VvodAlchoBase>();
//
//        Cursor cursor = database.query(OurbaseHelpBig.DATABASE_TABLE_A,
//                allColumnsA, "ALCHO_ID_ALL= ?", new String[]{String.valueOf(ProsmotrAlcoDay.i)}, null, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            VvodAlchoBase alco = cursorToAlcoBaseA(cursor);
//            alcos.add(alco);
//            cursor.moveToNext();
//        }
//
//        cursor.close();
//        return alcos;
//    }

    private BigBase cursorToAlcoBase(Cursor cursor) {
        BigBase alco = new BigBase();
        alco.setId(cursor.getLong(0));
        alco.setDate(cursor.getString(1));
        alco.setName(cursor.getString(2));
        return alco;
    }

    private VvodAlchoBase cursorToAlcoBaseA(Cursor cursor) {
        VvodAlchoBase alco = new VvodAlchoBase();
        alco.setId(cursor.getLong(0));
        alco.setName(cursor.getString(1));
        alco.setName1(cursor.getString(2));
        alco.setDegrees(cursor.getDouble(3));
        alco.setColich(cursor.getInt(4));
        alco.setIdAll(cursor.getLong(5));
        return alco;
    }
}
