package com.example.wan.day16.model;

import android.os.Handler;
import android.os.Looper;

import com.example.wan.day16.bean.DetailsBean;
import com.example.wan.day16.net.OkHttpUtils;
import com.example.wan.day16.net.OnNetListener;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/15.
 */

public class GetDataModel implements GetDataService{
    private Handler handler = new Handler(Looper.getMainLooper());

    private String path = "https://www.zhaoapi.cn/product/getProductDetail";
    @Override
    public void getData(Map<String, String> map, final OnNetListener<DetailsBean> onNetListener) {

        OkHttpUtils.getOkHttpUtils().doPost(path, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final DetailsBean detailsBean = new Gson().fromJson(string, DetailsBean.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        onNetListener.onSuccess(detailsBean);
                    }
                });
            }
        });
    }
}
