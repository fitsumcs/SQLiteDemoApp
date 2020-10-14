package com.example.databseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "EMPLOYEES";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String ADDRESS = "address";

    // Database Information
    static final String DB_NAME = "myEmployees.DB";

    // database version
    static final int DB_VERSION = 1;

    private SQLiteDatabase database;

    // Creating table query
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT NOT NULL, " +  ADDRESS + " CHAR(50));";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    //databse open
    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }
    //databse close
    public void close() {
        database.close();
    }

    //add record
    public void addRecord(String name, String address)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);

        database.insert(TABLE_NAME, null, contentValues);
    }

    //get all recored
    public Cursor getAllRecord()
    {
        String[] projection = { _ID, NAME, ADDRESS };

        String orderBy = _ID + " DESC" ;

        Cursor cursor = database.query(TABLE_NAME, projection, null, null, null, null, orderBy);

        return cursor;
    }
   //update Record
    public int updateRecord(long _id, String name, String address) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(ADDRESS, address);

        int count = database.update(TABLE_NAME, contentValues, this._ID + " = " + _id, null);
        return count;
    }

    public void deleteRecord(long _id)
    {
        database.delete(TABLE_NAME, _ID + "=" + _id, null);
    }
}
