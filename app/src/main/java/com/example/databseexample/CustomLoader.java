package com.example.databseexample;

import android.content.Context;
import android.database.Cursor;

import androidx.loader.content.CursorLoader;

public class CustomLoader extends CursorLoader {

    DatabaseHelper myDatabaseHelper;

    public CustomLoader(Context context, DatabaseHelper db) {
        super(context);
        myDatabaseHelper = db;
    }

    @Override
    public Cursor loadInBackground() {
        return myDatabaseHelper.getAllRecord();
    }
}
