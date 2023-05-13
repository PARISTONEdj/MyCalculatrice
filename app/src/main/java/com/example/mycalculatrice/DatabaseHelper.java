package com.example.mycalculatrice;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "calculator.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "calculations";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SIGN = "sign";
    private static final String COLUMN_INPUT = "input";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SIGN + " TEXT, " +
                COLUMN_INPUT + " TEXT" +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public void insertData(String sign, String input) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SIGN, sign);
        values.put(COLUMN_INPUT, input);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public List<String> getAllData() {
        List<String> dataList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String sign = cursor.getString(cursor.getColumnIndex(COLUMN_SIGN));
                @SuppressLint("Range")
                String input = cursor.getString(cursor.getColumnIndex(COLUMN_INPUT));

                String data = ""+sign+" \n                                         "+input ;
                dataList.add(data);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return dataList;
    }


}
