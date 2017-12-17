package com.example.zhoumolianxi.model;

import com.example.zhoumolianxi.bean.Databean;
import com.example.zhoumolianxi.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/16.
 */

public interface GetDataService {
    void getData(Map<String,String> parms, OnNetListener<Databean> onNetListener);
}
