package com.example.todo.halper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    public static  int VERSION = 1;
    public static  String DB_NAME = "DB_TASK";
    public static String TABLE_TASK = "task";

    public DbHelper(Context context) {

        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS  " + TABLE_TASK
               + " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " name TEXT NOT NULL); ";

        try {
            db.execSQL(sql);
            Log.i("INFO DB", " creating DB Success ");
        } catch (Exception e) {
            Log.i("INFO DB", "Error creating DB " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String sql = "DROP TABLE IF EXISTS " + TABLE_TASK + " ;";

        try {
            db.execSQL(sql);
            onCreate(db);
            Log.i("INFO DB", " update DB Success ");
        } catch (Exception e) {
            Log.i("INFO DB", "Error creating DB " + e.getMessage());
        }
    }
}
