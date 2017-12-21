package com.example.lianxi03.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lianxi03.R;
import com.example.lianxi03.adaper.MyAdapter;
import com.example.lianxi03.bean.DataDataBean;
import com.example.lianxi03.presenter.MyPresenterOther;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

/**
 * Created by QinQinBaoBei on 2017/12/16.
 */

public class FragmentThree extends Fragment implements MyPresenterOther.PresenterListerenOther {
    private RecyclerView recyclerView;
    private SpringView springView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item3,container,false);
        recyclerView = view.findViewById(R.id.recyclerView01);
        springView = view.findViewById(R.id.springView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MyPresenterOther myPresenter = new MyPresenterOther();
        myPresenter.getData("71","1","1",this);
    }

    @Override
    public void success(List<DataDataBean.DataBean> data) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        MyAdapter myAdapter = new MyAdapter(getActivity(),data);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void failed() {

    }
}
