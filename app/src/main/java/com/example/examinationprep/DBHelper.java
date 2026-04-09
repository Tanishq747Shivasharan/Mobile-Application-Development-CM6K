package com.example.examinationprep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // ■■ Constants ■■
    private static final String DB_NAME = "RegistrationDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE = "Registration";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";

    public DBHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    // Called once when DB does not exists.

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE =
                "CREATE TABLE " + TABLE + " (" +
                        COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_NAME + " TEXT NOT NULL, " +
                        COL_EMAIL + " TEXT UNIQUE NOT NULL, " +
                        COL_PHONE + " TEXT NOT NULL" +
                        ")";
        db.execSQL(CREATE_TABLE);
    }

    // Called when db version increases.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    // Insert
    public long insertUser(String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PHONE, phone);
        long result = db.insert(TABLE, null, cv);
        db.close();
        return result; // -1 if failed
    }

    // Read all
    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE, null);
    }

    // update
    public int updateUser(int id, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, name);
        cv.put(COL_EMAIL, email);
        cv.put(COL_PHONE, phone);
        int rows = db.update(TABLE, cv, COL_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();

        return rows;
    }

    // Delete
    public int deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE, COL_ID + "=?",
                new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    // Delete all
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE);
        db.close();
    }

}
