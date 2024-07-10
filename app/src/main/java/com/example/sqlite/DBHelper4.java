package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper4 extends SQLiteOpenHelper {

    private static final String Liaotian = "CREATE TABLE liaotian (\n" +
            "    sendUserId TEXT,\n" +
            "    receiveUserId TEXT,\n" +
            "    content TEXT\n" +
            ");";

    public DBHelper4(@Nullable Context context) {
        super(context, "liaotian.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Liaotian);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
