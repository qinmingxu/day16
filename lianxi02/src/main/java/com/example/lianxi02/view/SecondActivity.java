package com.example.lianxi02.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxi02.R;
import com.example.lianxi02.bean.Detailbean;
import com.example.lianxi02.net.GlideImageLoader;
import com.example.lianxi02.presenter.AddCartPresenter;
import com.example.lianxi02.presenter.DetailPresenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener,ISecondListener {

    private Banner mBanner;
    private TextView mTitle;
    private TextView mPrice;
    private TextView mDzprice;
    List<String> list = new ArrayList<>();
    /**
     * 购物车
     */
    private Button mButton1;
    private Button mButton2;
    private LinearLayout mActivityMain;
    private DetailPresenter detailPresenter;
    private AddCartPresenter addCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        detailPresenter = new DetailPresenter(this);
        detailPresenter.getData();
        addCartPresenter = new AddCartPresenter(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailPresenter.deaach();
        addCartPresenter.deaach();
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTitle = (TextView) findViewById(R.id.title);
        mPrice = (TextView) findViewById(R.id.price);
        mDzprice = (TextView) findViewById(R.id.dzprice);
        mButton1 = (Button) findViewById(R.id.button1);
        mButton2 = (Button) findViewById(R.id.button2);
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mActivityMain = (LinearLayout) findViewById(R.id.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button1:
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                addCartPresenter.AddCart();
                break;
        }
    }

    @Override
    public void show(Detailbean detailbean) {

        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        String[] split = detailbean.getData().getImages().split("\\|");
        for (int i = 0; i <split.length ; i++) {
            list.add(split[i]);
        }
        mBanner.setImages(list);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
        mTitle.setText(detailbean.getData().getTitle());
        mPrice.setText(detailbean.getData().getPrice()+"");
        mDzprice.setText(detailbean.getData().getBargainPrice()+"");
    }

    @Override
    public void show(String string) {
        Toast.makeText(SecondActivity.this,string,Toast.LENGTH_SHORT).show();

    }
}
