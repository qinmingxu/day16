package com.example.lianxi03.adaper;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by QinQinBaoBei on 2017/12/16.
 */

public class MyViewpagerAdapter extends FragmentPagerAdapter{
    List<Fragment> data;
    public MyViewpagerAdapter(FragmentManager fm, List<Fragment> data) {
        super(fm);
        this.data=data;

    }


    @Override
    public Fragment getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
