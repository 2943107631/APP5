package com.example.user.activity.activtiy_person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.databinding.ActivityQiuZhiBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper2;
import com.example.util.Base;

public class QiuZhi extends Base<ActivityQiuZhiBinding> {

    private String id;
    private Boolean isHava = false;
    private String image;

    @Override
    protected ActivityQiuZhiBinding getViewBinding() {
        return ActivityQiuZhiBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("求职信息", true);

        bind.add.setOnClickListener(v -> {
            if (isHava) {
                //删除操作
                DBHelper2 dbHelper = new DBHelper2(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String selection = "userId=?";
                String[] selectionArgs = {id};
                int deletedRows = db.delete("qiuzhi", selection, selectionArgs);
                if (deletedRows > 0) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                getData();
                bind.layout.setVisibility(View.VISIBLE);
            }
        });

        bind.save.setOnClickListener(v -> {
            DBHelper2 dbHelper = new DBHelper2(this);
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("userId", bind.userId.getText().toString());
            values.put("image", image);
            values.put("username", bind.username.getText().toString());
            values.put("speciality", bind.speciality.getText().toString());
            values.put("qiuzhizhuangtai", bind.qiuzhizhuangtai.getText().toString());
            values.put("gerenjingli", bind.gerenjingli.getText().toString());
            values.put("ziwopingjia", bind.ziwopingjia.getText().toString());
            values.put("price", bind.price.getText().toString());
            if (isHava) {
                long updateResult = db.update("qiuzhi", values, "userId=?", new String[]{id});
                if (updateResult > 0) {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                long insertResult = db.insert("qiuzhi", null, values);
                if (insertResult != -1) {
                    Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");

        DBHelper2 dbHelper = new DBHelper2(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM qiuzhi WHERE userId = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            bind.userId.setText(cursor.getString(cursor.getColumnIndex("userId")));
            bind.username.setText(cursor.getString(cursor.getColumnIndex("username")));
            bind.speciality.setText(cursor.getString(cursor.getColumnIndex("speciality")));
            bind.qiuzhizhuangtai.setText(cursor.getString(cursor.getColumnIndex("qiuzhizhuangtai")));
            bind.gerenjingli.setText(cursor.getString(cursor.getColumnIndex("gerenjingli")));
            bind.ziwopingjia.setText(cursor.getString(cursor.getColumnIndex("ziwopingjia")));
            bind.price.setText(cursor.getString(cursor.getColumnIndex("price")));

            bind.layout.setVisibility(View.VISIBLE);
            bind.add.setText("删除求职信息");
            isHava = true;
        } else {
            bind.layout.setVisibility(View.GONE);
        }
    }

    private void getData() {
        DBHelper1 dbHelper = new DBHelper1(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            bind.userId.setText(cursor.getString(cursor.getColumnIndex("id")));
            bind.username.setText(cursor.getString(cursor.getColumnIndex("username")));
            bind.speciality.setText(cursor.getString(cursor.getColumnIndex("speciality")));
            image = cursor.getString(cursor.getColumnIndex("image"));
        }
    }
}