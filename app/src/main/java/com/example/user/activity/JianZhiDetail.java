package com.example.user.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.LiaoTian;
import com.example.R;
import com.example.bean.ZhaoPingBean;
import com.example.databinding.ActivityJianZhiDetailBinding;
import com.example.sqlite.DBHelper3;
import com.example.util.Base;

public class JianZhiDetail extends Base<ActivityJianZhiDetailBinding> {

    private ZhaoPingBean bean;
    private String id;

    @Override
    protected ActivityJianZhiDetailBinding getViewBinding() {
        return ActivityJianZhiDetailBinding.inflate(getLayoutInflater());
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

        bind.send.setOnClickListener(v -> {
            Intent intent = new Intent(this, LiaoTian.class);
            intent.putExtra("sendUserId", id);
            intent.putExtra("receiveUserId", bean.getUserId());
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        bean = (ZhaoPingBean) getIntent().getSerializableExtra("bean");
        id = getIntent().getStringExtra("id");
        setTitle("兼职详细");
    }
}