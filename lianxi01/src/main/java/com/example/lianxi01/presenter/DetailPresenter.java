package com.example.lianxi01.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.lianxi01.bean.Detailbean;
import com.example.lianxi01.model.DetailModel;
import com.example.lianxi01.model.DetailService;
import com.example.lianxi01.net.OnNetListener;
import com.example.lianxi01.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/17.
 */

public class DetailPresenter {
    private Handler handler = new Handler(Looper.getMainLooper());

    private IMainListener iMainListener;
    private final DetailService detailService;

    public DetailPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        detailService = new DetailModel();
    }
    public void deaach(){
        iMainListener=null;
    }
    public void getData(){
        Map<String, String> map = new HashMap<>();
        map.put("pid","71");
        detailService.getData(map, new OnNetListener<Detailbean>() {
            @Override
            public void onSuccess(Detailbean detailbean) {
                if(iMainListener!=null){
                    iMainListener.show(detailbean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
