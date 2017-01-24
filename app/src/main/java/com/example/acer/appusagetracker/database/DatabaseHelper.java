package com.example.acer.appusagetracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Acer on 1/11/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String KEY_NAME = "name";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_TIME = "time";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "AppDbAdapter";
    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table appStatus (_id integer primary key autoincrement, "
                    + "name text not null, package text not null, time long not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 2;
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }
}
