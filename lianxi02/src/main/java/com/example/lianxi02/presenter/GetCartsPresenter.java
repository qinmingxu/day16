package com.example.lianxi02.presenter;


import com.example.lianxi02.bean.GetCartsbean;
import com.example.lianxi02.model.GetCartsModel;
import com.example.lianxi02.model.GetCartsService;
import com.example.lianxi02.net.OnNetListener;
import com.example.lianxi02.view.IThirdListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public class GetCartsPresenter {
    private IThirdListener iThirdListener;
    private final GetCartsService getCartsService;

    public GetCartsPresenter(IThirdListener iThirdListener) {
        this.iThirdListener = iThirdListener;
        getCartsService = new GetCartsModel();
    }
    public void deaach(){
        iThirdListener=null;
    }
    public void getCarts(){
        Map<String,String> map = new HashMap<>();
        map.put("uid","1234");
        getCartsService.getCarts(map, new OnNetListener<GetCartsbean>() {
            @Override
            public void onSuccess(GetCartsbean getCartsbean) {
                if(iThirdListener!=null){
                    List<GetCartsbean.DataBean> group = getCartsbean.getData();
                    group.remove(0);
                    List<List<GetCartsbean.DataBean.ListBean>> child= new ArrayList<>();
                    for (int i = 0; i <group.size() ; i++) {
                        child.add(group.get(i).getList());
                    }
                    iThirdListener.show(group,child);
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
