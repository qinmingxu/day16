package com.example.lianxi01.model;

import com.example.lianxi01.bean.Detailbean;
import com.example.lianxi01.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/17.
 */

public interface DetailService {
    void getData(Map<String,String> parms, OnNetListener<Detailbean> onNetListener);
}
