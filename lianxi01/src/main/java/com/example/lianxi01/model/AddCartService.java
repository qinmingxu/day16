package com.example.lianxi01.model;

import com.example.lianxi01.bean.AddCartbean;
import com.example.lianxi01.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public interface AddCartService {
    void AddCart(Map<String,String> parms, OnNetListener<AddCartbean> onNetListener);
}
