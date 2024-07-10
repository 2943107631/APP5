package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper3 extends SQLiteOpenHelper {

    private static final String sqlite = "CREATE TABLE zhaoping (\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    userId TEXT,\n" +// 用工号
            "    image TEXT,\n" +//头像
            "    username TEXT,\n" +//联系人
            "    phone TEXT,\n" +//联系人电话
            "    address TEXT,\n" +//公司地址
            "    ask TEXT,\n" +//职位要求
            "    askName TEXT,\n" +//职位名称
            "    price TEXT,\n" +//工资
            "    clearance TEXT\n" +//发布是否通过
            ");";

    public DBHelper3(@Nullable Context context) {
        super(context, "zhaoping.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
