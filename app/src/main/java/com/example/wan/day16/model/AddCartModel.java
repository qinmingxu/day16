package com.example.wan.day16.model;

import android.os.Handler;
import android.os.Looper;


import com.example.wan.day16.bean.AddCartBean;
import com.example.wan.day16.net.OkHttpUtils;
import com.example.wan.day16.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/14.
 */

public class AddCartModel implements AddCartService {
    private Handler handler = new Handler(Looper.getMainLooper());
    private String path="https://www.zhaoapi.cn/product/addCart";

    @Override
    public void addCart(Map<String, String> parms, final OnNetListener<AddCartBean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(path, parms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Gson gson = new Gson();
                final AddCartBean addCartBean = gson.fromJson(string, AddCartBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(addCartBean);
                    }
                });
            }
        });
    }
}
