package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper1 extends SQLiteOpenHelper {

    private static final String sqlite = "CREATE TABLE user (\n" +
            "    id TEXT UNIQUE,\n" +// 学号/用工号
            "    image TEXT,\n" +//头像
            "    username TEXT,\n" +//姓名
            "    speciality TEXT,\n" +//专业/店铺名
            "    phone TEXT,\n" +//手机号
            "    password TEXT,\n" +//密码
            "    hetong TEXT,\n" +//密码
            "    hetongClearance TEXT,\n" +//密码
            "    yyzz TEXT,\n" +//营业执照
            "    clearance TEXT\n" +//用工账号是否通过
            ");";

    public DBHelper1(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
