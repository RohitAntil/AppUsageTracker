package com.example.acer.appusagetracker.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Acer on 1/12/2017.
 */
public class AppDbAdapter {

    public static final String KEY_NAME = "name";
    public static final String KEY_PACKAGE = "package";
    public static final String KEY_TIME = "time";
    public static final String KEY_ROWID = "_id";
    private static final String TAG = "AppDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table notes (_id integer primary key autoincrement, "
                    + "title text not null, body text not null, date text not null);";

    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 2;

    private final Context mCtx;



    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     *
     * @param ctx the Context within which to work
     */
    public AppDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     *
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public AppDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    /**
     * Create a new note using the title and body provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     *
     * @param name the title of the note
     * @param appPackage the body of the note
     * @return rowId or -1 if failed
     */
    public long createNote(String name, String appPackage, long time) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_PACKAGE, appPackage);
        initialValues.put(KEY_TIME, time);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the note with the given rowId
     *
     * @param appName name of app to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteNote(String appName) {

        return mDb.delete(DATABASE_TABLE, KEY_NAME + "=" + appName, null) > 0;
    }

    /**
     * Return a Cursor over the list of all notes in the database
     *
     * @return Cursor over all notes
     */
    public Cursor fetchAllNotes() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NAME,
                KEY_PACKAGE,KEY_TIME}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the note that matches the given rowId
     *
     * @param name name of app to retrieve
     * @return Cursor positioned to matching note, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchNote(String name) throws SQLException {

        Cursor mCursor =

                mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                                KEY_NAME, KEY_PACKAGE,KEY_TIME}, KEY_NAME + "=" + name, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    /**
     * Update the note using the details provided. The note to be updated is
     * specified using the rowId, and it is altered to use the title and body
     * values passed in
     *
     * @param rowId id of note to update
     * @param name value to set note title to
     * @param appPackage value to set note body to
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateNote(long rowId, String name, String appPackage,long time) {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_PACKAGE, appPackage);

        //This lines is added for personal reason
        args.put(KEY_TIME, time);

        //One more parameter is added for data
        return mDb.update(DATABASE_TABLE, args, KEY_NAME + "=" + name, null) > 0;
    }
}
