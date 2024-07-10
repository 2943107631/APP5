package com.example.yongong;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.R;
import com.example.databinding.ActivityMain3Binding;
import com.example.util.Base;
import com.example.yongong.fragment.CJianZhiFragment;
import com.example.yongong.fragment.CLiaoTianFragment;
import com.example.yongong.fragment.CPersonFragment;
import com.example.yongong.fragment.CZhaoPingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends Base<ActivityMain3Binding> {

    private List<Fragment> list = new ArrayList<>();

    @Override
    protected ActivityMain3Binding getViewBinding() {
        return ActivityMain3Binding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        bind.vp2.setOffscreenPageLimit(4);
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
                case R.id.zhaoping:
                    bind.vp2.setCurrentItem(1);
                    break;
                case R.id.jiaoliu:
                    bind.vp2.setCurrentItem(2);
                    break;
                case R.id.person:
                    bind.vp2.setCurrentItem(3);
                    break;
            }
            return true;
        });
    }

    @Override
    protected void initData() {
        list.add(new CJianZhiFragment());
        list.add(new CZhaoPingFragment());
        list.add(new CLiaoTianFragment());
        list.add(new CPersonFragment());
    }
}