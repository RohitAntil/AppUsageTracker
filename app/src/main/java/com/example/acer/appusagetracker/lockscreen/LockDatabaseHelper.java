package com.example.acer.appusagetracker.lockscreen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class LockDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "appusage.db";
    private static final int DATABASE_VERSION = 1;

    public LockDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {

        ScreenLockTable.onCreate(database);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion,
                          int newVersion) {
        ScreenLockTable.onUpgrade(database, oldVersion, newVersion);
    }
}
