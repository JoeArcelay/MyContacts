package com.joearcelay.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rent-A-Center on 1/31/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";


    public static final String TABLE_CONTACT_LIST = "contactlist";
    public static final String COLUMN_LIST_ID = "id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_EMAIL = "email";
    public static final String COLUMN_LIST_PHONE = "phone";

    public DBHandler (Context context, SQLiteDatabase.CursorFactory factory){
        super (context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_CONTACT_LIST + "(" +
                COLUMN_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_LIST_NAME + " TEXT," +
                COLUMN_LIST_EMAIL + " TEXT," +
                COLUMN_LIST_PHONE + " TEXT" +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_LIST);
        onCreate(db);
    }

    public void addShoppingList(String name, String email, String phone){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_EMAIL, email);
        values.put(COLUMN_LIST_PHONE, phone);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CONTACT_LIST, null, values);

        db.close();
    }


}
