package com.example.user.activity.activtiy_person;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.databinding.ActivityPersonalBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;
import com.example.util.Base2;

public class Personal extends Base<ActivityPersonalBinding> {

    private String id;
    private String imagePath;
    private ImageView image;

    @Override
    protected ActivityPersonalBinding getViewBinding() {
        return ActivityPersonalBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("用户信息", true);

        image.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        bind.confim.setOnClickListener(v -> {
            if (bind.phone.getText().toString().length() == 11) {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("image", imagePath);
                values.put("username", bind.username.getText().toString());
                values.put("speciality", bind.speciality.getText().toString());
                values.put("phone", bind.phone.getText().toString());
                int rowsUpdated = db.update("user", values, "id=?", new String[]{id});
                if (rowsUpdated > 0) {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "手机号长度不足11位", Toast.LENGTH_SHORT).show();
            }

        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        image = bind.getRoot().findViewById(R.id.image);

        DBHelper1 dbHelper = new DBHelper1(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            bind.username.setText(cursor.getString(cursor.getColumnIndex("username")));
            bind.speciality.setText(cursor.getString(cursor.getColumnIndex("speciality")));
            bind.phone.setText(cursor.getString(cursor.getColumnIndex("phone")));
            imagePath = cursor.getString(cursor.getColumnIndex("image"));
            Glide.with(this).load(imagePath)
                    .placeholder(R.drawable.t1).into(image);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Uri selectedImageUri = data.getData();
                imagePath = getPath(selectedImageUri);
                Glide.with(this).load(imagePath)
                        .placeholder(R.drawable.t1).into(image);
            }
        }
    }

    private String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        } else {
            return null;
        }
    }
}