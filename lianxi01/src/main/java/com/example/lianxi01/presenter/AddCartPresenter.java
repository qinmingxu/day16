package com.example.lianxi01.presenter;

import com.example.lianxi01.bean.AddCartbean;
import com.example.lianxi01.model.AddCartModel;
import com.example.lianxi01.model.AddCartService;
import com.example.lianxi01.net.OnNetListener;
import com.example.lianxi01.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public class AddCartPresenter {
    private IMainListener iMainListener;
    private final AddCartService addCartService;

    public AddCartPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        addCartService = new AddCartModel();
    }
    public void deaach(){
        iMainListener=null;
    }
    public void AddCart(){
        Map<String, String> map = new HashMap<>();
        map.put("uid","100");
        map.put("pid","71");
        addCartService.AddCart(map, new OnNetListener<AddCartbean>() {
            @Override
            public void onSuccess(AddCartbean addCartbean) {
                if(iMainListener!=null){
                    iMainListener.show(addCartbean.getMsg());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
