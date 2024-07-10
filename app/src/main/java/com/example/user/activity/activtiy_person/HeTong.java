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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.databinding.ActivityHeTongBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class HeTong extends Base<ActivityHeTongBinding> {

    private String id;
    private String imagePath;

    @Override
    protected ActivityHeTongBinding getViewBinding() {
        return ActivityHeTongBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        bind.image.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 100);
        });

        bind.add.setOnClickListener(v -> {
            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("hetong", imagePath);
            int rowsUpdated = db.update("user", values, "id=?", new String[]{id});
            if (rowsUpdated > 0) {
                Toast.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        setTitle("合同",true);

        DBHelper1 dbHelper = new DBHelper1(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            Glide.with(this).load(cursor.getString(cursor.getColumnIndex("hetong")))
                    .placeholder(R.drawable.add_hetong_image).into(bind.image);
            if (cursor.getString(cursor.getColumnIndex("hetongClearance")).equals("1"))
                bind.text.setText("合同已通过");
            else
                bind.text.setText("合同未通过");

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
                        .placeholder(R.drawable.t1).into(bind.image);
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