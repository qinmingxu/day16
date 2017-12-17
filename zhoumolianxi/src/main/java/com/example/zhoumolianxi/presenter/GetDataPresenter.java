package com.example.zhoumolianxi.presenter;

import com.example.zhoumolianxi.bean.Databean;
import com.example.zhoumolianxi.model.GetDataModel;
import com.example.zhoumolianxi.model.GetDataService;
import com.example.zhoumolianxi.net.OnNetListener;
import com.example.zhoumolianxi.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/16.
 */

public class GetDataPresenter {
    private IMainListener iMainListener;
    private final GetDataService getDataService;

    public GetDataPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        getDataService = new GetDataModel();
    }
    public void deach(){
        iMainListener=null;
    }
    public void getData(int num){
        Map<String, String> map = new HashMap<>();
        map.put("uid","71");
        map.put("page",num+"");
        getDataService.getData(map, new OnNetListener<Databean>() {
            @Override
            public void onSuccess(Databean databean) {
                if(iMainListener!=null){
                    iMainListener.show(databean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
