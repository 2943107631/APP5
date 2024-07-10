package com.example.guanliyuan.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.MainActivity;
import com.example.R;
import com.example.bean.UserBean;
import com.example.databinding.FragmentAdminPersonBinding;
import com.example.guanliyuan.AdminPersonal;
import com.example.sqlite.DBHelper1;
import com.example.util.AdapterUtil;
import com.example.util.Base2;

import java.util.ArrayList;
import java.util.List;

public class AdminPersonFragment extends Base2<FragmentAdminPersonBinding> {

    private String content = "";

    @Override
    protected FragmentAdminPersonBinding getViewBinding() {
        return FragmentAdminPersonBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setTitle("用户信息");
        bind.recy.setLayoutManager(new LinearLayoutManager(getContext()));

        bind.exit.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), MainActivity.class));
            getActivity().finish();
        });

        bind.search.setOnClickListener(v -> {
            content=bind.edit.getText().toString();
            setRecy();
        });
    }

    @Override
    protected void initData() {
        setRecy();
    }

    private void setRecy() {
        List<UserBean> list = new ArrayList<>();
        DBHelper1 dbHelper = new DBHelper1(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sqlQuery = "SELECT * FROM user";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            UserBean bean = new UserBean(
                    cursor.getString(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("image")),
                    cursor.getString(cursor.getColumnIndex("username")),
                    cursor.getString(cursor.getColumnIndex("speciality")),
                    cursor.getString(cursor.getColumnIndex("phone")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getString(cursor.getColumnIndex("hetong")),
                    cursor.getString(cursor.getColumnIndex("yyzz")),
                    cursor.getString(cursor.getColumnIndex("clearance"))
            );

            if (bean.getId().contains(content))
                list.add(bean);
        }

        AdapterUtil<UserBean> adapter = new AdapterUtil<>(R.layout.user_item, ((data, position, holder) -> {
            TextView text1 = holder.getView(R.id.text1);
            TextView text2 = holder.getView(R.id.text2);
            LinearLayout layout = holder.getView(R.id.layout);
            text1.setText("姓名:" + data.getUsername());
            if (data.getId().substring(0, 1).equals("c"))
                text2.setText("用工号:" + data.getId());
            else
                text2.setText("学号:" + data.getId());
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), AdminPersonal.class);
                intent.putExtra("bean", data);
                startActivity(intent);
            });
        }));
        adapter.listUpdate(list);
        bind.recy.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        setRecy();
    }
}