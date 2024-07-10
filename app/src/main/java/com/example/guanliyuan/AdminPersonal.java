package com.example.guanliyuan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.bean.UserBean;
import com.example.databinding.ActivityAdminPersonalBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class AdminPersonal extends Base<ActivityAdminPersonalBinding> {

    private ImageView image;
    private String imagePath1;
    private String imagePath2;
    private UserBean bean;
    private Boolean is = false;

    @Override
    protected ActivityAdminPersonalBinding getViewBinding() {
        return ActivityAdminPersonalBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("用户详细信息", true);

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
                if (bean.getId().substring(0, 1).equals("c"))
                    values.put("yyzz", imagePath2);
                else
                    values.put("hetong", imagePath2);
                values.put("username", bind.username.getText().toString());
                values.put("speciality", bind.speciality.getText().toString());
                values.put("phone", bind.phone.getText().toString());
                int rowsUpdated = db.update("user", values, "id=?", new String[]{bean.getId()});
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

        bind.delete.setOnClickListener(v -> {
            if (!is) {
                DBHelper1 dbHelper1 = new DBHelper1(this);
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("clearance", "1");
                int rowsUpdated = db.update("user", values, "id=?", new String[]{bean.getId()});
                if (rowsUpdated > 0) {
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
                }
            } else {
                DBHelper1 dbHelper1 = new DBHelper1(getApplicationContext());
                SQLiteDatabase db = dbHelper1.getWritableDatabase();
                String selection = "id=?";
                String[] selectionArgs = {bean.getId()};
                int deletedRows = db.delete("user", selection, selectionArgs);
                if (deletedRows > 0) {
                    Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void initData() {
        bean = (UserBean) getIntent().getSerializableExtra("bean");
        image = bind.getRoot().findViewById(R.id.image);

        if (bean.getClearance().equals("0") && bean.getId().substring(0, 1).equals("c")) {
            bind.delete.setText("账号通过");
            is = false;
        } else {
            is = true;
        }

        bind.username.setText(bean.getUsername());
        bind.speciality.setText(bean.getSpeciality());
        bind.phone.setText(String.valueOf(bean.getPhone()));
        Glide.with(this).load(bean.getImage())
                .placeholder(R.drawable.t1).into(image);
        if (bean.getId().substring(0, 1).equals("c")) {
            Glide.with(this).load(bean.getYyzz())
                    .placeholder(R.drawable.add_hetong_image).into(bind.yyzz);
        } else {
            bind.text2.setText("专业");
            bind.speciality.setHint("请输入专业");
            bind.layout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
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