package com.example.lianxi01.net;

import android.util.Log;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by wan on 2017/12/17.
 */

public class OKHttpUntils {
    private static OKHttpUntils okHttpUntils;
    private final OkHttpClient client;

    private OKHttpUntils(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        client = new OkHttpClient.Builder()
                .addInterceptor(new MyInterceptor())
                .build();
    }
    public static OKHttpUntils getOkHttpUntils(){
        if(okHttpUntils==null){
            synchronized (OKHttpUntils.class){
                if(okHttpUntils==null){
                    okHttpUntils = new OKHttpUntils();
                }
            }
        }
        return okHttpUntils;
    }
    public void doGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param callback
     */
    public void doPost(String url, Map<String, String> params, Callback callback) {
        if (params == null) {
            throw new RuntimeException("参数为空了");
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Log.e("OkHttpUtils", "请求地址：" + url + " 请求的参数：");
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        client.newCall(request).enqueue(callback);
    }
}
