package com.lexa.newnewstytby.ormLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Lexa on 09.05.2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();

    private static final String DATABASE_NAME ="myappname.db";

    private static final int DATABASE_VERSION = 1;

    private BaseClassDAO baseClassDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, BaseClass.class);
        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,BaseClass.class,true);
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "error upgrading db " + DATABASE_NAME+"from ver "+oldVersion);
            throw new RuntimeException(e);
        }
    }

    public BaseClassDAO getBaseClassDAO() throws SQLException{
        if(baseClassDao == null){
            baseClassDao = new BaseClassDAO(getConnectionSource(), BaseClass.class);
        }
        return baseClassDao;
    }

    @Override
    public void close(){
        super.close();
        baseClassDao = null;
    }


}
