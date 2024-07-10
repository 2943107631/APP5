package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.ActivityMainBinding;
import com.example.guanliyuan.MainActivity4;
import com.example.sqlite.DBHelper1;
import com.example.user.MainActivity2;
import com.example.util.Base;
import com.example.yongong.MainActivity3;

public class MainActivity extends Base<ActivityMainBinding> {

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        //获取权限
        setPermission();

        setTitle("登录", false);
        bind.register.setOnClickListener(v -> startActivity(new Intent(this, Register.class)));
        bind.login.setOnClickListener(v -> Login());
    }

    @Override
    protected void initData() {
    }

    // 设置文件权限
    private void setPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    2);
        }
    }

    public void Login() {
        if (bind.userId.getText().toString().equals("admin") && bind.password.getText().toString().equals("123456")) {
            startActivity(new Intent(this, MainActivity4.class));
            finish();
        } else {
            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getReadableDatabase();
            String sqlQuery = "SELECT * FROM user WHERE id = ? AND password = ?";
            Cursor cursor = db.rawQuery(sqlQuery,
                    new String[]{bind.userId.getText().toString(), bind.password.getText().toString()});
            if (cursor.moveToFirst()) {
                if (bind.userId.getText().toString().substring(0, 1).equals("c")) {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity3.class);
                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity2.class);
                    intent.putExtra("id", cursor.getString(cursor.getColumnIndex("id")));
                    startActivity(intent);
                    finish();
                }
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}