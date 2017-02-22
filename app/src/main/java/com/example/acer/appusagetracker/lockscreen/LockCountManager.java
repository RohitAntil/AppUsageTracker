package com.example.acer.appusagetracker.lockscreen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public long createTableEntry(String date,int count) {
        ContentValues values = new ContentValues();
        values.put(ScreenLockTable.COLUMN_DATE, date);
        values.put(ScreenLockTable.COLUMN_COUNT, count);
        long insertId = mDatabase.insert(ScreenLockTable.TABLE_LOCK, null, values);
        return insertId;
    }

    public long updateLockCount(String date,int count) {
        String whereClause = ScreenLockTable.COLUMN_DATE+"=?";
        String [] whereArgs = {date.toString()};
        ContentValues values = new ContentValues();
        values.put(ScreenLockTable.COLUMN_DATE, date);
        values.put(ScreenLockTable.COLUMN_COUNT, count);
        long insertId = mDatabase.update(ScreenLockTable.TABLE_LOCK, values, whereClause, whereArgs);
        return insertId;
    }

    public int deleteDateObject(long date) {
        int deleteStatus = mDatabase.delete(ScreenLockTable.TABLE_LOCK, ScreenLockTable.COLUMN_DATE
                + " = " + date, null);
        return deleteStatus;
    }
    public int getCount(String date)
    {
        Cursor cursor = mDatabase.query(ScreenLockTable.TABLE_LOCK,
                mAllColumns,  ScreenLockTable.COLUMN_DATE
                        + " = " + date, null, null, null,null);
        return cursor.getInt(1);
    }
    public List<Lock> getAllLock_Objects() {
       List<Lock> countList=new ArrayList<Lock>();

        Cursor cursor = mDatabase.query(ScreenLockTable.TABLE_LOCK,
                mAllColumns, null, null, null, null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Lock lock = cursorToLock_Object(cursor);
            countList.add(lock);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return countList;
    }
    public int isEntryExists(String date) {

        Cursor cursor = mDatabase.query(ScreenLockTable.TABLE_LOCK,
                mAllColumns, null, null, null, null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           if(date.equalsIgnoreCase(cursor.getString(0)))
           {
               return cursor.getInt(1);
           }
        }
        // make sure to close the cursor
        cursor.close();
        return -1;
    }
    private Lock cursorToLock_Object(Cursor cursor) {
        Lock lock = new Lock();
        lock.setDate(cursor.getString(0));
        lock.setCount(cursor.getInt(1));
        return lock;
    }

}
