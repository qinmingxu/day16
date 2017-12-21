package com.example.lianxi02.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.lianxi02.R;
import com.example.lianxi02.fragment.Fragment01;

import java.util.ArrayList;
import java.util.List;

public class FiveActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        list.add("全部");
        list.add("待支付");
        list.add("已支付");
        list.add("已取消");
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setOffscreenPageLimit(list.size());
        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public CharSequence getPageTitle(int position) {
                return list.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                Fragment01 fragment01 = new Fragment01();
                Bundle bundle = new Bundle();
                if(list.get(position).equals("全部")){
                    bundle.putInt("status",3);
                }else if(list.get(position).equals("待支付")){
                    bundle.putInt("status",0);
                }else if(list.get(position).equals("已支付")){
                    bundle.putInt("status",1);
                }else if(list.get(position).equals("已取消")){
                    bundle.putInt("status",2);
                }
                fragment01.setArguments(bundle);
                return fragment01;
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        mTabLayout.setupWithViewPager(mPager);
    }
}
