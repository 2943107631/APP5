package com.example.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;

import com.example.R;
import com.example.databinding.ActivityMain2Binding;
import com.example.user.fragment.JianZhiFragment;
import com.example.user.fragment.LiaoTianFragment;
import com.example.user.fragment.PersonFragment;
import com.example.util.Base;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends Base<ActivityMain2Binding> {

    private List<Fragment> list = new ArrayList<>();

    @Override
    protected ActivityMain2Binding getViewBinding() {
        return ActivityMain2Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        bind.vp2.setOffscreenPageLimit(3);
        bind.vp2.setUserInputEnabled(false);
        bind.vp2.setAdapter(new FragmentStateAdapter(this) {
            @Override
            public Fragment createFragment(int position) {
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });

        bind.jiao.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    bind.vp2.setCurrentItem(0);
                    break;
                case R.id.jiaoliu:
                    bind.vp2.setCurrentItem(1);
                    break;
                case R.id.person:
                    bind.vp2.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void initData() {
        list.add(new JianZhiFragment());
        list.add(new LiaoTianFragment());
        list.add(new PersonFragment());
    }
}