package com.example.lianxi01.presenter;

import com.example.lianxi01.bean.GetCartsbean;
import com.example.lianxi01.model.GetCartsModel;
import com.example.lianxi01.model.GetCartsService;
import com.example.lianxi01.net.OnNetListener;
import com.example.lianxi01.view.ISecondListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public class GetCartsPresenter {
    private ISecondListener iSecondListener;
    private final GetCartsService getCartsService;

    public GetCartsPresenter(ISecondListener iSecondListener) {
        this.iSecondListener = iSecondListener;
        getCartsService = new GetCartsModel();
    }
    public void deaach(){
        iSecondListener=null;
    }
    public void getCarts(){
        Map<String,String> map = new HashMap<>();
        map.put("uid","1234");
        getCartsService.getCarts(map, new OnNetListener<GetCartsbean>() {
            @Override
            public void onSuccess(GetCartsbean getCartsbean) {
                if(iSecondListener!=null){
                    List<GetCartsbean.DataBean> group = getCartsbean.getData();
                    List<List<GetCartsbean.DataBean.ListBean>> child= new ArrayList<>();
                    for (int i = 0; i <group.size() ; i++) {
                        child.add(group.get(i).getList());
                    }
                    iSecondListener.show(group,child);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
