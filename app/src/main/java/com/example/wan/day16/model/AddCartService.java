package com.example.wan.day16.model;


import com.example.wan.day16.bean.AddCartBean;
import com.example.wan.day16.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/14.
 */

public interface AddCartService {
    void addCart(Map<String, String> parms, OnNetListener<AddCartBean> onNetListener);
}
