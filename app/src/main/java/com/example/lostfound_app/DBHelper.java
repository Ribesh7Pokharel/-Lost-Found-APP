package com.example.lostfound_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LostFound.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_CONTACT = "contact";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_LATITUDE + " REAL,"
                + COLUMN_LONGITUDE + " REAL,"
                + COLUMN_CONTACT + " TEXT"
                + ")";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_ITEMS + " ADD COLUMN " + COLUMN_CONTACT + " TEXT");
        }
    }

    public void addItem(String name, String description, double latitude, double longitude, String contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        values.put(COLUMN_CONTACT, contact);

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }

    public Cursor getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        // Alias the 'id' column as '_id' for the CursorAdapter
        return db.rawQuery("SELECT id AS _id, name, description, latitude, longitude, contact FROM " + TABLE_ITEMS, null);
    }
}
