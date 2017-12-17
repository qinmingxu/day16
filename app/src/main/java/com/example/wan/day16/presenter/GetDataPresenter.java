package com.example.wan.day16.presenter;

import com.example.wan.day16.bean.DetailsBean;
import com.example.wan.day16.model.GetDataModel;
import com.example.wan.day16.model.GetDataService;
import com.example.wan.day16.net.OnNetListener;
import com.example.wan.day16.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/15.
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
    public void getData(){
        Map<String, String> map = new HashMap<>();
        map.put("pid","71");
        getDataService.getData(map, new OnNetListener<DetailsBean>() {
            @Override
            public void onSuccess(DetailsBean detailsBean) {
                if(iMainListener!=null){
                    iMainListener.show(detailsBean);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
