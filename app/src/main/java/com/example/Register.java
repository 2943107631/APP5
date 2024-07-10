package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.ActivityRegisterBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class Register extends Base<ActivityRegisterBinding> {
    @Override
    protected ActivityRegisterBinding getViewBinding() {
        return ActivityRegisterBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("注册", true);
        bind.register.setOnClickListener(v -> {
            Register();
        });
    }

    @Override
    protected void initData() {
    }

    public void Register() {
        if (bind.userId.getText().toString().isEmpty() ||
                bind.username.getText().toString().isEmpty() ||
                bind.password.getText().toString().isEmpty() ||
                !bind.password.getText().toString().equals(bind.passwordd.getText().toString())) {
            Toast.makeText(context, "请输入完整内容", Toast.LENGTH_SHORT).show();
        } else {
            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id", bind.userId.getText().toString());
            values.put("username", bind.username.getText().toString());
            values.put("password", bind.password.getText().toString());
            values.put("clearance", "0".toString());
            values.put("hetongClearance", "0".toString());
            long insertResult = db.insert("user", null, values);
            if (insertResult != -1) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}