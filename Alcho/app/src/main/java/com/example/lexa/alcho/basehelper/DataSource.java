package com.example.lexa.alcho.basehelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lexa.alcho.construct.AlcoBase;
import com.example.lexa.alcho.basehelper.OurbaseHelper1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 21.12.2015.
 */
public class DataSource  {
private SQLiteDatabase database;
private OurbaseHelper1 dbHelper;
private String[] allColumns = { OurbaseHelper1.ALCHO_ID,OurbaseHelper1.ALCHO_NAME_COLUMN,
        OurbaseHelper1.ALCHO_DEGREE, OurbaseHelper1.ALCHO_IM, OurbaseHelper1.ALCHO_SITE};

        public DataSource(Context context) {
            dbHelper = new OurbaseHelper1(context);
        }

        public void open() throws SQLException {
            database = dbHelper.getWritableDatabase();
        }

        public void close() {
            dbHelper.close();
        }

        public AlcoBase createAlcoBase(String name,String degree,String im,String site) {
            ContentValues values = new ContentValues();
            values.put(OurbaseHelper1.ALCHO_NAME_COLUMN, name);
            values.put(OurbaseHelper1.ALCHO_DEGREE, degree);
            values.put(OurbaseHelper1.ALCHO_IM, im);
            values.put(OurbaseHelper1.ALCHO_SITE, site);
            long insertId = database.insert(OurbaseHelper1.DATABASE_TABLE, null,
                    values);
            Cursor cursor = database.query(OurbaseHelper1.DATABASE_TABLE,
                    allColumns, OurbaseHelper1.ALCHO_ID + " = " + insertId, null,
                    null, null, null);
            cursor.moveToFirst();
            AlcoBase newD = cursorToAlcoBase(cursor);
            cursor.close();
            return newD;
        }

        public void deleteAlcoBase(AlcoBase alcoBase) {
            long id = alcoBase.getId();
            System.out.println("Comment deleted with id: " + id);
            database.delete(OurbaseHelper1.DATABASE_TABLE, OurbaseHelper1.ALCHO_ID + " = "
                    + id, null);
        }

        public List<AlcoBase> getAllAlcoBase() {
            List<AlcoBase> alcos = new ArrayList<AlcoBase>();

            Cursor cursor = database.query(OurbaseHelper1.DATABASE_TABLE,
                    allColumns, null, null, null, null, OurbaseHelper1.ALCHO_NAME_COLUMN );

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                AlcoBase alco = cursorToAlcoBase(cursor);
                alcos.add(alco);
                cursor.moveToNext();
            }

            cursor.close();
            return alcos;
        }

        private AlcoBase cursorToAlcoBase(Cursor cursor) {
            AlcoBase alco = new AlcoBase();
            alco.setId(cursor.getLong(0));
            alco.setName(cursor.getString(1));
            alco.setDegrees(cursor.getString(2));
            alco.setImage(cursor.getString(3));
            alco.setSite(cursor.getString(4));
            return alco;
        }
}
