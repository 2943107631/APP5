package com.example.yongong.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.LiaoTian;
import com.example.R;
import com.example.bean.QiuZhiBean;
import com.example.databinding.ActivityQiuZhiDetailBinding;
import com.example.sqlite.DBHelper4;
import com.example.util.Base;

public class QiuZhiDetail extends Base<ActivityQiuZhiDetailBinding> {

    private String id;
    private QiuZhiBean bean;

    @Override
    protected ActivityQiuZhiDetailBinding getViewBinding() {
        return ActivityQiuZhiDetailBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        ImageView image = bind.getRoot().findViewById(R.id.image);
        Glide.with(this).load(bean.getImage())
                .placeholder(R.drawable.t1).into(image);
        bind.username.setText("姓名:" + bean.getUsername());
        bind.userId.setText("学号:" + bean.getUserId());
        bind.speciality.setText("专业:" + bean.getSpeciality());

        bind.qiuzhizhuangtai.setText("求职状态:" + bean.getQiuzhizhuangtai());
        bind.gerenjingli.setText("个人经历:" + bean.getGerenjingli());
        bind.ziwopingjia.setText("自我评价:" + bean.getZiwopingjia());
        bind.price.setText("期望薪资:" + bean.getPrice() + "￥");

        bind.send.setOnClickListener(v -> {
            Intent intent = new Intent(this, LiaoTian.class);
            intent.putExtra("sendUserId", id);
            intent.putExtra("receiveUserId", bean.getUserId());
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        id = getIntent().getStringExtra("id");
        bean = (QiuZhiBean) getIntent().getSerializableExtra("bean");
        setTitle("求职详细", true);
    }
}