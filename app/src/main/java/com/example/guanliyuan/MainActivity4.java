package com.example.guanliyuan;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.R;
import com.example.databinding.ActivityMain4Binding;
import com.example.guanliyuan.fragment.AdminJianZhiFragment;
import com.example.guanliyuan.fragment.AdminPersonFragment;
import com.example.guanliyuan.fragment.HeTongFragment;
import com.example.util.Base;
import com.example.yongong.fragment.CJianZhiFragment;
import com.example.yongong.fragment.CZhaoPingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends Base<ActivityMain4Binding> {

    private List<Fragment> list = new ArrayList<>();

    @Override
    protected ActivityMain4Binding getViewBinding() {
        return ActivityMain4Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        bind.vp2.setOffscreenPageLimit(2);
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
                case R.id.jianzhi:
                    bind.vp2.setCurrentItem(0);
                    break;
                case R.id.person:
                    bind.vp2.setCurrentItem(1);
                    break;
                case R.id.hetong:
                    bind.vp2.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void initData() {
        list.add(new AdminJianZhiFragment());
        list.add(new AdminPersonFragment());
        list.add(new HeTongFragment());
    }
}