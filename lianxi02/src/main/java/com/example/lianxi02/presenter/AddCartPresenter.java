package com.example.lianxi02.presenter;



import com.example.lianxi02.bean.AddCartbean;
import com.example.lianxi02.model.AddCartModel;
import com.example.lianxi02.model.AddCartService;
import com.example.lianxi02.net.OnNetListener;
import com.example.lianxi02.view.ISecondListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/18.
 */

public class AddCartPresenter {
    private ISecondListener iSecondListener;
    private final AddCartService addCartService;

    public AddCartPresenter(ISecondListener iSecondListener) {
        this.iSecondListener = iSecondListener;
        addCartService = new AddCartModel();
    }
    public void deaach(){
        iSecondListener=null;
    }
    public void AddCart(){
        Map<String, String> map = new HashMap<>();
        map.put("uid","100");
        map.put("pid","71");
        addCartService.AddCart(map, new OnNetListener<AddCartbean>() {
            @Override
            public void onSuccess(AddCartbean addCartbean) {
                if(iSecondListener!=null){
                    iSecondListener.show(addCartbean.getMsg());
                }
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }
}
