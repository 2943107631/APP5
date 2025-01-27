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
import com.example.bean.ZhaoPingBean;
import com.example.databinding.FragmentCZhaoPingBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper2;
import com.example.sqlite.DBHelper3;
import com.example.util.AdapterUtil;
import com.example.util.Base2;
import com.example.yongong.activity.AddZhaoPing;
import com.example.yongong.activity.AddZhaoPingDetail;
import com.example.yongong.activity.QiuZhiDetail;

import java.util.ArrayList;
import java.util.List;

public class CZhaoPingFragment extends Base2<FragmentCZhaoPingBinding> {

    private String id;
    private List<ZhaoPingBean> list = new ArrayList<>();

    @Override
    protected FragmentCZhaoPingBinding getViewBinding() {
        return FragmentCZhaoPingBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("招聘管理");
        bind.recy.setLayoutManager(new LinearLayoutManager(getContext()));

        bind.add.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddZhaoPing.class);
            intent.putExtra("id", id);
            startActivity(intent);
        });
    }

    @Override
    protected void initData() {
        id = getActivity().getIntent().getStringExtra("id");

        list.clear();
        DBHelper3 dbHelper = new DBHelper3(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM zhaoping WHERE userId = ?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{id});
        while (cursor.moveToNext()) {
            ZhaoPingBean bean = new ZhaoPingBean(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("userId")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("address")),
                    cursor.getString(cursor.getColumnIndex("ask")),
                    cursor.getString(cursor.getColumnIndex("askName")),
                    cursor.getString(cursor.getColumnIndex("price")),
                    cursor.getString(cursor.getColumnIndex("clearance"))
            );

            list.add(bean);
//            DBHelper1 dbHelper1 = new DBHelper1(getContext());
//            SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
//            String sqlQuery1 = "SELECT * FROM user WHERE id = ? ";
//            Cursor cursor1 = db1.rawQuery(sqlQuery1, new String[]{bean.getUserId()});
//            if (cursor1.moveToFirst()) {
//                if (cursor1.getString(cursor1.getColumnIndex("clearance")).equals("1")){
//
//                }
//            }
        }

        AdapterUtil<ZhaoPingBean> adapter = new AdapterUtil<>(R.layout.zhaojianzhi_item, (data, position, holder) -> {
            TextView username = holder.getView(R.id.username);
            TextView speciality = holder.getView(R.id.speciality);
            ImageView image = holder.getView(R.id.image);
            Glide.with(this).load(data.getImage())
                    .placeholder(R.drawable.t1).into(image);
            username.setText("联系人:" + data.getUsername());
            speciality.setText("职位名称: " + data.getAskName());
            LinearLayout layout = holder.getView(R.id.layout);
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), AddZhaoPingDetail.class);
                intent.putExtra("bean", data);
                intent.putExtra("clearance", 0);
                startActivity(intent);
            });
        });
        adapter.listUpdate(list);
        bind.recy.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}