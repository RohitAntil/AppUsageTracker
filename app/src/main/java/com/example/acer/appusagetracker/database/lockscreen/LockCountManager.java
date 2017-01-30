package com.example.acer.appusagetracker.database.lockscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by rohit02.kumar on 1/30/2017.
 */
public class LockCountManager  {
    private static final String DEFAULT_SORT_ORDER = "ASC";
    private SQLiteDatabase mDatabase;
    private LockDatabaseHelper mDbHelper;
    private String[] mAllColumns = { ScreenLockTable.COLUMN_DATE,
            ScreenLockTable.COLUMN_COUNT};
    private String[] mIdColumn = {ScreenLockTable.COLUMN_DATE };

    public LockCountManager(Context context) {
        mDbHelper = new LockDatabaseHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close() {
        mDbHelper.close();
    }

    public long createTableEntry(long date,int count) {
        ContentValues values = new ContentValues();
        values.put(ScreenLockTable.COLUMN_DATE, date);
        values.put(ScreenLockTable.COLUMN_COUNT, count);
        long insertId = mDatabase.insert(ScreenLockTable.TABLE_LOCK, null, values);
        return insertId;
    }

    public long updateLockCount(long date,int count) {
        ContentValues values = new ContentValues();
        values.put(ScreenLockTable.COLUMN_DATE, date);
        values.put(ScreenLockTable.COLUMN_COUNT, count);
        long insertId = mDatabase.update(ScreenLockTable.TABLE_LOCK, values, ScreenLockTable.COLUMN_DATE
                + " = " + date, null);
        return insertId;
    }

    public int deleteDateObject(long date) {
        int deleteStatus = mDatabase.delete(ScreenLockTable.TABLE_LOCK, ScreenLockTable.COLUMN_DATE
                + " = " + date, null);
        return deleteStatus;
    }

    public ArrayList<Lock> getAllNote_Objects() {
        ArrayList<Lock> notes = new ArrayList<Lock>();

        Cursor cursor = mDatabase.query(ScreenLockTable.TABLE_LOCK,
                mAllColumns, null, null, null, null,
                ScreenLockTable.COLUMN_DATE + DEFAULT_SORT_ORDER);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lock lock = cursorToLock_Object(cursor);
            notes.add(lock);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return notes;
    }
    private Lock cursorToLock_Object(Cursor cursor) {
        Lock lock = new Lock();
        Date date=new Date(cursor.getLong(0));
        DateFormat format=SimpleDateFormat.getDateInstance();
        lock.setDate(format.format(date));
        lock.setCount(cursor.getInt(1));
        return lock;
    }
}
