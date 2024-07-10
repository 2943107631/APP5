package com.example.yongong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.bean.ZhaoPingBean;
import com.example.databinding.ActivityAddZhaoPingDetailBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper2;
import com.example.sqlite.DBHelper3;
import com.example.util.Base;

public class AddZhaoPingDetail extends Base<ActivityAddZhaoPingDetailBinding> {

    private ZhaoPingBean bean;
    private int id;
    private int clearance;

    @Override
    protected ActivityAddZhaoPingDetailBinding getViewBinding() {
        return ActivityAddZhaoPingDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        ImageView image = bind.getRoot().findViewById(R.id.image);
        Glide.with(this).load(bean.getImage())
                .placeholder(R.drawable.t1).into(image);
        bind.username.setText("联系人:" + bean.getUsername());
        bind.phone.setText("联系电话:" + bean.getPhone());

        bind.askName.setText(bean.getAskName());
        bind.ask.setText(bean.getAsk());
        bind.address.setText(bean.getAddress());
        bind.price.setText(bean.getPrice() + "￥");

        bind.delete.setOnClickListener(v -> {
            DBHelper3 dbHelper3 = new DBHelper3(getApplicationContext());
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            String selection = "id=?";
            String[] selectionArgs = {String.valueOf(id)};
            int deletedRows = db.delete("zhaoping", selection, selectionArgs);
            if (deletedRows > 0) {
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
            }
        });

        bind.clearance.setOnClickListener(v -> {
            DBHelper3 dbHelper3 = new DBHelper3(this);
            SQLiteDatabase db = dbHelper3.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("clearance", "1");
            int rowsUpdated = db.update("zhaoping", values, "userId=?", new String[]{bean.getUserId()});
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
        bean = (ZhaoPingBean) getIntent().getSerializableExtra("bean");
        clearance = getIntent().getIntExtra("clearance", 1);
        if (clearance != 1 || bean.getClearance().equals("1"))
            bind.clearance.setVisibility(View.GONE);
        id = bean.getId();
        setTitle("招聘管理详细", true);
    }
}