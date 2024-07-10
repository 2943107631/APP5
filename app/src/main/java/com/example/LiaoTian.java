package com.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bean.LiaoTianBean;
import com.example.databinding.ActivityLiaoTianBinding;
import com.example.sqlite.DBHelper1;
import com.example.sqlite.DBHelper4;
import com.example.util.AdapterUtil;
import com.example.util.Base;

import java.util.ArrayList;
import java.util.List;

public class LiaoTian extends Base<ActivityLiaoTianBinding> {

    private String sendUserId;
    private String receiveUserId;
    private List<LiaoTianBean> list = new ArrayList<>();

    @Override
    protected ActivityLiaoTianBinding getViewBinding() {
        return ActivityLiaoTianBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        setRecy();

        bind.send.setOnClickListener(v -> {
            DBHelper4 dbHelper4 = new DBHelper4(this);
            SQLiteDatabase db = dbHelper4.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("receiveUserId", receiveUserId);
            values.put("sendUserId", sendUserId);
            values.put("content", bind.commentEdit.getText().toString());
            long result = db.insert("liaotian", null, values);
            if (result != -1) {
                Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
                bind.commentEdit.setText("");
                setRecy();
            } else {
                Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setRecy() {
        list.clear();
        DBHelper4 dbHelper4 = new DBHelper4(this);
        SQLiteDatabase db = dbHelper4.getReadableDatabase();
        String sqlQuery = "SELECT * FROM liaotian";
        Cursor cursor = db.rawQuery(sqlQuery, null);
        while (cursor.moveToNext()) {
            LiaoTianBean bean = new LiaoTianBean(
                    cursor.getString(cursor.getColumnIndex("sendUserId")),
                    cursor.getString(cursor.getColumnIndex("receiveUserId")),
                    cursor.getString(cursor.getColumnIndex("content"))
            );
            if ((bean.getReceiveId().equals(receiveUserId) && bean.getSendId().equals(sendUserId)) ||
                    bean.getReceiveId().equals(sendUserId) && bean.getSendId().equals(receiveUserId))
                list.add(bean);
        }

        AdapterUtil<LiaoTianBean> adapter = new AdapterUtil<>(R.layout.char_item, ((data, position, holder) -> {
            LinearLayout layout1 = holder.getView(R.id.layout1);
            TextView content1 = holder.getView(R.id.content1);
            LinearLayout layout2 = holder.getView(R.id.layout2);
            TextView content2 = holder.getView(R.id.content2);

            if (data.getSendId().equals(sendUserId)) {
                // 号主发的消息
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                content2.setText(data.getContent());
            } else {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                content1.setText(data.getContent());
            }
        }));
        adapter.listUpdate(list);
        bind.recy.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        sendUserId = getIntent().getStringExtra("sendUserId");
        receiveUserId = getIntent().getStringExtra("receiveUserId");
        setTitle("聊天", true);
        bind.recy.setLayoutManager(new LinearLayoutManager(this));
    }
}