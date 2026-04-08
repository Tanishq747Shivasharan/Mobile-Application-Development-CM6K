package com.example.examinationprep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    // Database config pool
    public static final String DATABASE_NAME = "College.db";
    public static final int DATABASE_VERSION = 1;
    public  static final String TABLE_NAME = "Students";

    public MyDbHelper(Context c) {
        super(c, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + "id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT, " + "course TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
