package com.joearcelay.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rent-A-Center on 1/31/2016.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "contact.db";


    public static final String TABLE_CONTACT_LIST = "contactlist";
    public static final String COLUMN_LIST_ID = "id";
    public static final String COLUMN_LIST_NAME = "name";
    public static final String COLUMN_LIST_EMAIL = "email";
    public static final String COLUMN_LIST_PHONE = "phone";

    public static final String TABLE_CONTACT_LIST_ITEM = "contactlistitem";
    public static final String COLUMN_ITEM_ID = "_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_EMAIL = "item_email";
    public static final String COLUMN_ITEM_PHONE = "item_phone";
    public static final String COLUMN_ITEM_HAS = "item_has";
    public static final String COLUMN_ITEM_LIST_ID = "item_list_id";

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


        String query2 = "CREATE TABLE " + TABLE_CONTACT_LIST_ITEM + "(" +
                COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_ITEM_NAME + " TEXT," +
                COLUMN_ITEM_EMAIL + " TEXT," +
                COLUMN_ITEM_PHONE + " INTEGER, " +
                COLUMN_ITEM_HAS + " TEXT," +
                COLUMN_ITEM_LIST_ID + " INTEGER" +
                ");";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT_LIST_ITEM);
        onCreate(db);
    }

    public void addContactList(String name, String email, String phone){
        ContentValues values = new ContentValues();
        values.put(COLUMN_LIST_NAME, name);
        values.put(COLUMN_LIST_EMAIL, email);
        values.put(COLUMN_LIST_PHONE, phone);

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CONTACT_LIST, null, values);

        db.close();
    }

    public Cursor getContactList(){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_CONTACT_LIST, null);

    }

    public void addContactListItem(String name, String email, Integer phone, Integer listId){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, name);
        values.put(COLUMN_ITEM_EMAIL, email);
        values.put(COLUMN_ITEM_PHONE, phone);
        values.put(COLUMN_ITEM_LIST_ID, listId);
        values.put(COLUMN_ITEM_HAS, "false");


        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CONTACT_LIST_ITEM, null, values);

        db.close();
    }

    public String getContactListName(int id){
        SQLiteDatabase db = getWritableDatabase();

        String dbString = "";

        String query = "SELECT * FROM " + TABLE_CONTACT_LIST + " WHERE " + COLUMN_LIST_ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        int numShoppingLists =  cursor.getCount();

        if (numShoppingLists >= 1) {
            cursor.moveToFirst();
            if ((cursor.getString(cursor.getColumnIndex("name")) != null)) {
                dbString = cursor.getString(cursor.getColumnIndex("name"));
            }
        }


        db.close();
        return dbString;

    }

    public Cursor getContactListItem(Integer listId){

        SQLiteDatabase db = getWritableDatabase();

        return db.rawQuery("SELECT * FROM " + TABLE_CONTACT_LIST_ITEM + " WHERE " + COLUMN_ITEM_LIST_ID + " =" + listId, null);

    }


    public String getContactListTotal(int listId){

        String dbString = " ";

        String query = "SELECT * FROM " + TABLE_CONTACT_LIST_ITEM + " WHERE " + COLUMN_ITEM_LIST_ID + " =" + listId;

        SQLiteDatabase db = getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        Double totalCost = 0.0;

        int numShoppingListItems = c.getCount();

        if(numShoppingListItems >= 1){
            c.moveToFirst();

            while (!c.isAfterLast()){
                totalCost += (c.getDouble(c.getColumnIndex("item_email")) + (c.getInt(c.getColumnIndex("item_quantity"))));
                c.moveToNext();
            }

            dbString = String.valueOf(totalCost);

            db.close();

        }

        return dbString;

    }


}
