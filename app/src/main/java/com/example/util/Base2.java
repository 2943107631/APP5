package com.example.util;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.R;

abstract public class Base2<T extends ViewBinding> extends Fragment {
    protected T bind;
    public Context context;
    private String TAG = "TAG";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getContext();
        bind = getViewBinding();
        initData();
        initView();
        return bind.getRoot();
    }

    protected abstract T getViewBinding();

    protected abstract void initView();

    protected abstract void initData();

    protected void setTitle(String title) {
        Toolbar toolbar = bind.getRoot().findViewById(R.id.toolbar);
        toolbar.getChildAt(0).setVisibility(View.GONE);
        ((TextView) toolbar.getChildAt(1)).setText(title);
    }

    public <T> void loge(T str) {
        Log.e(TAG, "loge: " + String.valueOf(str));
    }
}