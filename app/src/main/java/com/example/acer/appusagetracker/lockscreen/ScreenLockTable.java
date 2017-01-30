package com.example.acer.appusagetracker.lockscreen;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class ScreenLockTable {
    public static final String TABLE_LOCK = "lock";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_COUNT = "count";

    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_LOCK
            + "("
            + COLUMN_DATE + " long primary key autoincrement , "
            + COLUMN_COUNT + " integer not null, "
            + ");";

    /* will be used to create database and notes table */
    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    /* will be used when we need to update database is required in future */
    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {

        database.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCK);
        onCreate(database);
    }
}
