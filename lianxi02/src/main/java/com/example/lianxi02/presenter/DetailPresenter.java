package com.example.lianxi02.presenter;

import android.os.Handler;
import android.os.Looper;


import com.example.lianxi02.bean.Detailbean;
import com.example.lianxi02.model.DetailModel;
import com.example.lianxi02.model.DetailService;
import com.example.lianxi02.net.OnNetListener;
import com.example.lianxi02.view.ISecondListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/17.
 */

public class DetailPresenter {
    private Handler handler = new Handler(Looper.getMainLooper());

    private ISecondListener iSecondListener;
    private final DetailService detailService;

    public DetailPresenter(ISecondListener iSecondListener) {
        this.iSecondListener = iSecondListener;
        detailService = new DetailModel();
    }
    public void deaach(){
        iSecondListener=null;
    }
    public void getData(){
        Map<String, String> map = new HashMap<>();
        map.put("pid","71");
        detailService.getData(map, new OnNetListener<Detailbean>() {
            @Override
            public void onSuccess(Detailbean detailbean) {
                if(iSecondListener!=null){
                    iSecondListener.show(detailbean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
