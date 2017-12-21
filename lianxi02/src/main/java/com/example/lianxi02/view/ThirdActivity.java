package com.example.lianxi02.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lianxi02.R;
import com.example.lianxi02.adapter.Myadapter;
import com.example.lianxi02.bean.GetCartsbean;
import com.example.lianxi02.bean.PriceAndCount;
import com.example.lianxi02.presenter.GetCartsPresenter;

import java.util.List;

public class ThirdActivity extends AppCompatActivity implements IThirdListener{

    private ExpandableListView mExlistview;
    /**
     * 全选
     */
    private CheckBox mBox;
    /**
     * 合计:
     */
    private TextView mTotal;
    /**
     * 去结算(0)
     */
    private TextView mCount;
    private GetCartsPresenter getCartsPresenter;
    private Myadapter myadapter;
    private int count;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        getCartsPresenter = new GetCartsPresenter(this);
        getCartsPresenter.getCarts();
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCartsPresenter.deaach();
    }

    private void initView() {
        mExlistview = (ExpandableListView) findViewById(R.id.exlistview);
        mBox = (CheckBox) findViewById(R.id.box);
        mTotal = (TextView) findViewById(R.id.total);
        mCount = (TextView) findViewById(R.id.count);
        mBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter.AllOrNone(mBox.isChecked());
            }
        });
        mCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0){
                    Toast.makeText(ThirdActivity.this,"请选择商品",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(ThirdActivity.this, FourActivity.class);
                    intent.putExtra("price",price+"");
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public void show(List<GetCartsbean.DataBean> group, List<List<GetCartsbean.DataBean.ListBean>> child) {
        myadapter = new Myadapter(this, group, child);
        mExlistview.setAdapter(myadapter);
        for (int i = 0; i < group.size(); i++) {
            mExlistview.expandGroup(i);
        }
    }
    public void setPriceAndCount(PriceAndCount priceAndCount){
        count = priceAndCount.getCount();
        price = priceAndCount.getPrice();
        mTotal.setText("合计:"+price);
        mCount.setText("去结算("+ count +")");
    }
    public void setAllChecked(boolean b){
        mBox.setChecked(b);
    }
}
