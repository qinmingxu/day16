package com.example.zhoumolianxi.model;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.zhoumolianxi.bean.Databean;
import com.example.zhoumolianxi.net.OkHttpUtils;
import com.example.zhoumolianxi.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/16.
 */

public class GetDataModel implements GetDataService{
    private Handler handler = new Handler(Looper.getMainLooper());

    private String path = "https://www.zhaoapi.cn/product/getOrders";

    @Override
    public void getData(Map<String, String> parms, final OnNetListener<Databean> onNetListener) {
        OkHttpUtils.getOkHttpUtils().doPost(path, parms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final Databean databean = gson.fromJson(string, Databean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(databean);
                    }
                });
            }
        });
    }
}
