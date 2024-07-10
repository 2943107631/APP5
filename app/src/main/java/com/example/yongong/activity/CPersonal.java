package com.example.yongong.activity;

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
import com.example.databinding.ActivityCpersonalBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class CPersonal extends Base<ActivityCpersonalBinding> {

    private String id;
    private String imagePath1;
    private String imagePath2;
    private ImageView image;

    @Override
    protected ActivityCpersonalBinding getViewBinding() {
        return ActivityCpersonalBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        image.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        bind.yyzz.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 200);
        });

        bind.confim.setOnClickListener(v -> {
            if (bind.phone.getText().toString().length() == 11) {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("image", imagePath1);
                values.put("yyzz", imagePath2);
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
        setTitle("用户信息", true);
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
            imagePath1 = cursor.getString(cursor.getColumnIndex("image"));
            imagePath2 = cursor.getString(cursor.getColumnIndex("yyzz"));
            Glide.with(this).load(imagePath1)
                    .placeholder(R.drawable.t1).into(image);
            Glide.with(this).load(imagePath2)
                    .placeholder(R.drawable.add_hetong_image).into(bind.yyzz);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 100) {
                Uri selectedImageUri = data.getData();
                imagePath1 = getPath(selectedImageUri);
                Glide.with(this).load(imagePath1)
                        .placeholder(R.drawable.t1).into(image);
            }
            if (requestCode == 200) {
                Uri selectedImageUri = data.getData();
                imagePath2 = getPath(selectedImageUri);
                Glide.with(this).load(imagePath2)
                        .placeholder(R.drawable.add_hetong_image).into(bind.yyzz);
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