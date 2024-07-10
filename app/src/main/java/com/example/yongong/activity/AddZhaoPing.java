package com.example.yongong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.ActivityAddZhaoPingBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper2;
import com.example.sqlite.DBHelper3;
import com.example.util.Base;

public class AddZhaoPing extends Base<ActivityAddZhaoPingBinding> {

    private String id;
    private String image;
    private String username;
    private String phone;

    @Override
    protected ActivityAddZhaoPingBinding getViewBinding() {
        return ActivityAddZhaoPingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("添加招聘信息", true);

        bind.add.setOnClickListener(v -> {
            DBHelper3 dbHelper = new DBHelper3(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userId", id);
            values.put("image", image);
            values.put("username", username);
            values.put("phone", phone);
            values.put("address", bind.address.getText().toString());
            values.put("ask", bind.ask.getText().toString());
            values.put("askName", bind.askName.getText().toString());
            values.put("price", bind.price.getText().toString());
            values.put("clearance", "0");
            long insertResult = db.insert("zhaoping", null, values);
            if (insertResult != -1) {
                Toast.makeText(this, "发布成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "发布失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");

        DBHelper1 dbHelper = new DBHelper1(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex("username"));
            phone = cursor.getString(cursor.getColumnIndex("phone"));
            image = cursor.getString(cursor.getColumnIndex("image"));
        }
    }
}