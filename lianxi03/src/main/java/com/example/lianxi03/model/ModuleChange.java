package com.example.lianxi03.model;


import com.example.lianxi03.okhttp.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by QinQinBaoBei on 2017/12/20.
 */

public class ModuleChange {
    public void getData(String status, String id, final ModuleChangeListener moduleChangeListener){
        OkHttpUtils.getInstance().doGet("http://120.27.23.105/product/updateOrder?uid=71&status="+status+"&orderId="+id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                moduleChangeListener.success(string);
            }
        });
    }
    public interface ModuleChangeListener{
        void success(String s);
    }
}
