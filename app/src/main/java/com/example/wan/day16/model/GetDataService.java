package com.example.wan.day16.model;

import com.example.wan.day16.bean.DetailsBean;
import com.example.wan.day16.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/15.
 */

public interface GetDataService {
    void getData(Map<String,String> map, OnNetListener<DetailsBean> onNetListener);
}
