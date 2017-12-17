package com.example.zhoumolianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhoumolianxi.adapter.Myadapter;
import com.example.zhoumolianxi.bean.Databean;
import com.example.zhoumolianxi.presenter.GetDataPresenter;
import com.example.zhoumolianxi.view.IMainListener;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainListener {

    private ImageView image;
    private GetDataPresenter getDataPresenter;
    private XRecyclerView mRecycle;
    private int num=1;
    List<Databean.DataBean> list = new ArrayList<>();
    private Myadapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        image = (ImageView) findViewById(R.id.image);
        getDataPresenter = new GetDataPresenter(this);
        getDataPresenter.getData(num);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDataPresenter.deach();
    }

    @Override
    public void show(final Databean databean) {

        list.addAll(databean.getData());
        LinearLayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycle.setLayoutManager(layoutManager);
        setAdapter();
        mRecycle.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                num=1;
                list.clear();
                getDataPresenter.getData(num);
                mRecycle.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                num++;
                list.addAll(databean.getData());
                getDataPresenter.getData(num);
                mRecycle.refreshComplete();
            }
        });
    }

    private void setAdapter() {
        if (myadapter==null){
            myadapter=new Myadapter(MainActivity.this,list);
            mRecycle.setAdapter(myadapter);
        }else {
            myadapter.notifyDataSetChanged();
        }

    }

    private void initView() {
        mRecycle = (XRecyclerView) findViewById(R.id.recycle);
    }
}
