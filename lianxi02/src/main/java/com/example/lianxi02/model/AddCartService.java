package com.example.lianxi02.model;



import com.example.lianxi02.bean.AddCartbean;
import com.example.lianxi02.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public interface AddCartService {
    void AddCart(Map<String, String> parms, OnNetListener<AddCartbean> onNetListener);
}
