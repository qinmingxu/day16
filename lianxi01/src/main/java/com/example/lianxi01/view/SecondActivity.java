package com.example.lianxi01.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lianxi01.R;
import com.example.lianxi01.adapter.Myadapter;
import com.example.lianxi01.bean.GetCartsbean;
import com.example.lianxi01.bean.PriceAndCount;
import com.example.lianxi01.presenter.GetCartsPresenter;

import java.util.List;

public class SecondActivity extends AppCompatActivity implements ISecondListener {

    private GetCartsPresenter getCartsPresenter;
    private ExpandableListView mExlistview;
    /**
     * 全选
     */
    private CheckBox mBox;
    /**
     * 合计:
     */
    private TextView mHeji;
    /**
     * 去结算(0)
     */
    private TextView mCount;
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        getCartsPresenter = new GetCartsPresenter(this);
        getCartsPresenter.getCarts();
    }


    private void initView() {
        mExlistview = (ExpandableListView) findViewById(R.id.exlistview);
        mBox = (CheckBox) findViewById(R.id.box);
        mHeji = (TextView) findViewById(R.id.heji);
        mCount = (TextView) findViewById(R.id.count);
        mBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter.AllOrNone(mBox.isChecked());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getCartsPresenter.deaach();
    }
    public void setPriceAndCount(PriceAndCount priceAndCount){
        mHeji.setText("合计:"+priceAndCount.getNum());
        mCount.setText("去结算("+priceAndCount.getPrice()+")");
    }
    public void setAllChecked(boolean b){
        mBox.setChecked(b);
    }

}
