package com.example.yongong.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.R;
import com.example.bean.QiuZhiBean;
import com.example.databinding.FragmentCJianZhiBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper2;
import com.example.user.activity.activtiy_person.QiuZhi;
import com.example.util.AdapterUtil;
import com.example.util.Base2;
import com.example.yongong.activity.QiuZhiDetail;

import java.util.ArrayList;
import java.util.List;

public class CJianZhiFragment extends Base2<FragmentCJianZhiBinding> {

    private String id;
    private List<QiuZhiBean> list = new ArrayList<>();

    @Override
    protected FragmentCJianZhiBinding getViewBinding() {
        return FragmentCJianZhiBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("找兼职");
        bind.recy.setLayoutManager(new LinearLayoutManager(getContext()));

        AdapterUtil<QiuZhiBean> adapter = new AdapterUtil<>(R.layout.zhaojianzhi_item, (data, position, holder) -> {
            TextView username = holder.getView(R.id.username);
            TextView speciality = holder.getView(R.id.speciality);
            ImageView image = holder.getView(R.id.image);
            Glide.with(this).load(data.getImage())
                    .placeholder(R.drawable.t1).into(image);
            username.setText("姓名:" + data.getUsername());
            speciality.setText("专业: " + data.getSpeciality());
            LinearLayout layout = holder.getView(R.id.layout);
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), QiuZhiDetail.class);
                intent.putExtra("id", id);
                intent.putExtra("bean", data);
                startActivity(intent);
            });
        });
        adapter.listUpdate(list);
        bind.recy.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        id = getActivity().getIntent().getStringExtra("id");

        DBHelper1 dbHelper1 = new DBHelper1(getContext());
        SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
        String sqlQuery1 = "SELECT * FROM user WHERE id = ? ";
        Cursor cursor1 = db1.rawQuery(sqlQuery1, new String[]{id});
        if (cursor1.moveToFirst()) {
            if (cursor1.getString(cursor1.getColumnIndex("clearance")).equals("1")) {
                DBHelper2 dbHelper2 = new DBHelper2(getContext());
                SQLiteDatabase db = dbHelper2.getReadableDatabase();
                String sqlQuery = "SELECT * FROM qiuzhi";
                Cursor cursor = db.rawQuery(sqlQuery, null);
                while (cursor.moveToNext()) {
                    QiuZhiBean bean = new QiuZhiBean(
                            cursor.getString(cursor.getColumnIndex("userId")),
                            cursor.getString(cursor.getColumnIndex("image")),
                            cursor.getString(cursor.getColumnIndex("username")),
                            cursor.getString(cursor.getColumnIndex("speciality")),
                            cursor.getString(cursor.getColumnIndex("qiuzhizhuangtai")),
                            cursor.getString(cursor.getColumnIndex("gerenjingli")),
                            cursor.getString(cursor.getColumnIndex("ziwopingjia")),
                            cursor.getString(cursor.getColumnIndex("price"))
                    );
                    list.add(bean);
                }
            }
        }
    }
}