package com.example.user.activity.activtiy_person;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.ActivityPasswordBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class Password extends Base<ActivityPasswordBinding> {

    private String id;

    @Override
    protected ActivityPasswordBinding getViewBinding() {
        return ActivityPasswordBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("修改密码", true);

        bind.confim.setOnClickListener(v -> {
            if (bind.newPassword.getText().toString().length()>=6){
                if (bind.newPassword.getText().toString().equals(bind.newPasswordd.getText().toString())) {
                    DBHelper1 dbHelper1 = new DBHelper1(this);
                    SQLiteDatabase db = dbHelper1.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("password", bind.newPassword.getText().toString());
                    int rowsUpdated = db.update("user", values, "id = ? AND password = ?",
                            new String[]{String.valueOf(id), bind.oldPassword.getText().toString()});
                    if (rowsUpdated != -1) {
                        finish();
                        Toast.makeText(this, "密码修改成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "密码修改失败", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "两次新密码不一样", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(context, "新密码长度不足6位", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
    }
}