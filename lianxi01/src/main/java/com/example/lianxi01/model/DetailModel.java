package com.example.lianxi01.model;

import android.os.Handler;
import android.os.Looper;

import com.example.lianxi01.bean.Detailbean;
import com.example.lianxi01.net.OKHttpUntils;
import com.example.lianxi01.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/17.
 */

public class DetailModel implements DetailService{
    private Handler handler = new Handler(Looper.getMainLooper());
    private String path="https://www.zhaoapi.cn/product/getProductDetail";
    @Override
    public void getData(Map<String, String> parms, final OnNetListener<Detailbean> onNetListener) {
        OKHttpUntils.getOkHttpUntils().doPost(path, parms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final Detailbean detailbean = new Gson().fromJson(string, Detailbean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(detailbean);
                    }
                });
            }
        });
    }
}
