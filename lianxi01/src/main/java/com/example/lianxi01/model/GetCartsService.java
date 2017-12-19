package com.example.lianxi01.model;

import com.example.lianxi01.bean.GetCartsbean;
import com.example.lianxi01.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public interface GetCartsService {
    void getCarts(Map<String,String> parms, OnNetListener<GetCartsbean> onNetListener);
}
