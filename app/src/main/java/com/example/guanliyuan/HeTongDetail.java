package com.example.guanliyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.bean.UserBean;
import com.example.databinding.ActivityHeTongDetailBinding;
import com.example.sqlite.DBHelper1;
import com.example.util.Base;

public class HeTongDetail extends Base<ActivityHeTongDetailBinding> {

    private UserBean bean;

    @Override
    protected ActivityHeTongDetailBinding getViewBinding() {
        return ActivityHeTongDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        Glide.with(this).load(bean.getHetong()).placeholder(R.drawable.add_hetong_image)
                .into(bind.image);

        bind.tongguo.setOnClickListener(v -> {
            DBHelper1 dbHelper1 = new DBHelper1(this);
            SQLiteDatabase db = dbHelper1.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("hetongClearance", "1");
            int rowsUpdated = db.update("user", values, "id=?", new String[]{bean.getId()});
            if (rowsUpdated > 0) {
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void initData() {
        bean = (UserBean) getIntent().getSerializableExtra("bean");
    }
}