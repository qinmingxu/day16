package com.example.lianxi01.view;

import com.example.lianxi01.bean.GetCartsbean;

import java.util.List;

/**
 * Created by wan on 2017/12/18.
 */

public interface ISecondListener {
    void show(List<GetCartsbean.DataBean> group, List<List<GetCartsbean.DataBean.ListBean>> child);
}
