package com.example.user.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.MainActivity;
import com.example.R;
import com.example.databinding.FragmentPersonBinding;
import com.example.sqlite.DBHelper1;
import com.example.user.activity.activtiy_person.HeTong;
import com.example.user.activity.activtiy_person.Password;
import com.example.user.activity.activtiy_person.Personal;
import com.example.user.activity.activtiy_person.QiuZhi;
import com.example.util.Base2;

public class PersonFragment extends Base2<FragmentPersonBinding> {

    private String id;

    @Override
    protected FragmentPersonBinding getViewBinding() {
        return FragmentPersonBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("个人中心");
        bind.exit.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });
        bind.manage.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QiuZhi.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        bind.layout1.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Personal.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        bind.layout2.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Password.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        bind.layout3.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), HeTong.class);
            intent.putExtra("id", id);
            getActivity().startActivity(intent);
        });
        bind.layout4.setOnClickListener(v -> getActivity().finish());
    }

    @Override
    protected void initData() {
        id = getActivity().getIntent().getStringExtra("id");

        DBHelper1 dbHelper = new DBHelper1(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM user WHERE id = ?";
        String[] strings = new String[]{id};
        Cursor cursor = db.rawQuery(sqlQuery, strings);
        if (cursor.moveToFirst()) {
            bind.username.setText("姓名:" + cursor.getString(cursor.getColumnIndex("username")));
            bind.userId.setText("学号:" + cursor.getString(cursor.getColumnIndex("id")));
            ImageView imageView = bind.getRoot().findViewById(R.id.image);
            Glide.with(getContext()).load(cursor.getString(cursor.getColumnIndex("image")))
                    .placeholder(R.drawable.t1).into(imageView);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}