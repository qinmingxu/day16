package com.example.wan.day16.presenter;

import com.example.wan.day16.bean.AddCartBean;
import com.example.wan.day16.model.AddCartModel;
import com.example.wan.day16.model.AddCartService;
import com.example.wan.day16.net.OnNetListener;
import com.example.wan.day16.view.IMainListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wan on 2017/12/15.
 */

public class AddCartPresenter {
    private IMainListener iMainListener;
    private final AddCartService addCartService;

    public AddCartPresenter(IMainListener iMainListener) {
        this.iMainListener = iMainListener;
        addCartService = new AddCartModel();
    }
    public void deach(){
        iMainListener=null;
    }

    public void addCart() {
        Map<String, String> params = new HashMap<>();
        params.put("pid", "71");
        params.put("uid", "39");
        addCartService.addCart(params, new OnNetListener<AddCartBean>() {
            @Override
            public void onSuccess(AddCartBean addCartBean) {
                if (iMainListener != null) {
                    iMainListener.show(addCartBean.getCode().equals("0") ? "添加成功了" : "添加失败了");
                }            }

            @Override
            public void onFailure(Exception e) {

            }
        });

    }
}
