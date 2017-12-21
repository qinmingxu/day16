package com.example.lianxi02.model;



import com.example.lianxi02.bean.Detailbean;
import com.example.lianxi02.net.OnNetListener;

import java.util.Map;

/**
 * Created by wan on 2017/12/17.
 */

public interface DetailService {
    void getData(Map<String, String> parms, OnNetListener<Detailbean> onNetListener);
}
