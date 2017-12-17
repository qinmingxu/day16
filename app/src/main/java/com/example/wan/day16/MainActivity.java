package com.example.wan.day16;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wan.day16.bean.DetailsBean;
import com.example.wan.day16.net.GlideImageLoader;
import com.example.wan.day16.presenter.AddCartPresenter;
import com.example.wan.day16.presenter.GetDataPresenter;
import com.example.wan.day16.view.IMainListener;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,IMainListener {

    private Banner mBanner;
    private TextView mTitle;
    private TextView mPrice;
    private TextView mBanprice;
    /**
     * 加入购车
     */
    private Button mButton;
    private GetDataPresenter getDataPresenter;
    private AddCartPresenter addCartPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataPresenter = new GetDataPresenter(this);
        addCartPresenter = new AddCartPresenter(this);

        getDataPresenter.getData();
        initView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDataPresenter.deach();
        addCartPresenter.deach();
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTitle = (TextView) findViewById(R.id.title);
        mPrice = (TextView) findViewById(R.id.price);
        mBanprice = (TextView) findViewById(R.id.banprice);
        mButton = (Button) findViewById(R.id.button);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button:
                addCartPresenter.addCart();
                break;
        }
    }

    @Override
    public void show(DetailsBean detailsBean) {
        String images = detailsBean.getData().getImages();
        String[] split = images.split("\\|");
        List<String> list = new ArrayList<>();
        for (int i=0;i<split.length;i++){
            list.add(split[i]);
        }
        mBanner.setImageLoader(new GlideImageLoader());
        mBanner.setImages(list);
        mBanner.start();
        TextPaint paint = mPrice.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        mTitle.setText(detailsBean.getData().getTitle());
        mPrice.setText("原价：" + detailsBean.getData().getPrice());
        mBanprice.setText("优惠价：" +detailsBean.getData().getBargainPrice() );
    }

    @Override
    public void show(String str) {
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
    }
}
