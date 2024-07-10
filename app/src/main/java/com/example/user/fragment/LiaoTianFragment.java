package com.example.user.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.LiaoTian;
import com.example.R;
import com.example.bean.LiaoTianBean;
import com.example.databinding.FragmentLiaoTianBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper4;
import com.example.util.AdapterUtil;
import com.example.util.Base2;

import java.util.ArrayList;
import java.util.List;

public class LiaoTianFragment extends Base2<FragmentLiaoTianBinding> {

    private String id;
    private List<LiaoTianBean> list = new ArrayList<>();

    @Override
    protected FragmentLiaoTianBinding getViewBinding() {
        return FragmentLiaoTianBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        list.clear();

        DBHelper4 dbHelper = new DBHelper4(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sqlQuery = "SELECT * FROM liaotian WHERE sendUserId=? OR receiveUserId=?";
        Cursor cursor = db.rawQuery(sqlQuery, new String[]{id, id});
        while (cursor.moveToNext()) {
            LiaoTianBean bean = new LiaoTianBean(
                    cursor.getString(cursor.getColumnIndex("sendUserId")),
                    cursor.getString(cursor.getColumnIndex("receiveUserId")),
                    cursor.getString(cursor.getColumnIndex("content"))
            );

            if (list.size() == 0) {
                list.add(bean);
            } else {
                boolean isDuplicate = false;
                for (LiaoTianBean liaotianBean : list) {
                    if (liaotianBean.getSendId().equals(bean.getSendId())) {
                        isDuplicate = true;
                        break;
                    }
                    if (liaotianBean.getSendId().equals(bean.getReceiveId()) &&
                            liaotianBean.getReceiveId().equals(bean.getSendId())) {
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    list.add(bean);
                }
            }
        }

        AdapterUtil<LiaoTianBean> adapter = new AdapterUtil<>(R.layout.liaotian_item, ((data, position, holder) -> {
            LinearLayout layout = (LinearLayout) holder.getView(R.id.layout);
            ImageView image = (ImageView) holder.getView(R.id.image);
            TextView username = (TextView) holder.getView(R.id.username);

            DBHelper1 dbHelper1 = new DBHelper1(getContext());
            SQLiteDatabase db1 = dbHelper1.getReadableDatabase();
            String sqlQuery1 = "SELECT * FROM user WHERE id = ?";
            String[] strings = new String[]{String.valueOf(data.getSendId().equals(id) ?
                    data.getReceiveId() : data.getSendId())};
            Cursor cursor1 = db1.rawQuery(sqlQuery1, strings);
            if (cursor1.moveToFirst()) {
                Glide.with(this).load(cursor1.getString(cursor1.getColumnIndex("image")))
                        .placeholder(R.drawable.t1).into(image);
                username.setText(cursor1.getString(cursor1.getColumnIndex("username")));
            }

            layout.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), LiaoTian.class);
                intent.putExtra("sendUserId", id);
                if (id.equals(data.getSendId()))
                    intent.putExtra("receiveUserId", data.getReceiveId());
                else
                    intent.putExtra("receiveUserId", data.getSendId());
                startActivity(intent);
            });
        }));
        adapter.listUpdate(list);
        bind.recy.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        id = getActivity().getIntent().getStringExtra("id");
        setTitle("聊天");
        bind.recy.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}