package com.example.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;

import com.example.R;

abstract public class Base<T extends ViewBinding> extends AppCompatActivity {
    protected T bind;
    public Context context;
    private String TAG = "TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        bind = getViewBinding();
        setContentView(bind.getRoot());
        initData();
        initView();
    }

    protected abstract T getViewBinding();

    protected abstract void initView();

    protected abstract void initData();

    protected void setTitle(String title, Boolean flag) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setOnClickListener(view -> finish());
        toolbar.getChildAt(0).setVisibility(flag ? View.VISIBLE : View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText(title);
    }

    public <T> void loge(T str) {
        Log.e(TAG, "loge: " + String.valueOf(str));
    }
}
