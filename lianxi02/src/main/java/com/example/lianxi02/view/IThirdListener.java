package com.example.lianxi02.view;

import com.example.lianxi02.bean.GetCartsbean;

import java.util.List;

/**
 * Created by wan on 2017/12/20.
 */

public interface IThirdListener {
    void show(List<GetCartsbean.DataBean> group,List<List<GetCartsbean.DataBean.ListBean>> child);
}
