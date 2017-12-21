package com.example.lianxi02.model;

import android.os.Handler;
import android.os.Looper;

import com.example.lianxi02.bean.GetCartsbean;
import com.example.lianxi02.net.OKHttpUntils;
import com.example.lianxi02.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/18.
 */

public class GetCartsModel implements GetCartsService {
    private Handler handler = new Handler(Looper.getMainLooper());
    private String path = "http://120.27.23.105/product/getCarts";
    @Override
    public void getCarts(Map<String, String> parms, final OnNetListener<GetCartsbean> onNetListener) {
        OKHttpUntils.getOkHttpUntils().doPost(path, parms, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final GetCartsbean getCartsbean = new Gson().fromJson(string, GetCartsbean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(getCartsbean);
                    }
                });
            }
        });
    }
}
