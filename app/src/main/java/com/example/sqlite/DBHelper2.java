package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper2 extends SQLiteOpenHelper {

    private static final String sqlite = "CREATE TABLE qiuzhi (\n" +
            "    userId TEXT UNIQUE,\n" +// 学号
            "    image TEXT,\n" +//头像
            "    username TEXT,\n" +//姓名
            "    speciality TEXT,\n" +//专业
            "    qiuzhizhuangtai TEXT,\n" +//求职状态
            "    gerenjingli TEXT,\n" +//个人经历
            "    ziwopingjia TEXT,\n" +//自我评价
            "    price TEXT\n" +//期望薪资
            ");";

    public DBHelper2(@Nullable Context context) {
        super(context, "qiuzhi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlite);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
