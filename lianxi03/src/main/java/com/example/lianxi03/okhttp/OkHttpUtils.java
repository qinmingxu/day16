package com.example.lianxi03.okhttp;

import android.widget.ExpandableListView;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by QinQinBaoBei on 2017/12/16.
 */

public class OkHttpUtils {

    private static  OkHttpUtils okHttpUtils=null;
    private static OkHttpClient client;

    private OkHttpUtils(){

    }

    public static OkHttpUtils getInstance(){
        if(okHttpUtils == null){
            okHttpUtils = new OkHttpUtils();
            client = new OkHttpClient().newBuilder()
                    .readTimeout(20, TimeUnit.SECONDS)
                    .connectTimeout(20,TimeUnit.SECONDS)
                    .build();

        }
        return okHttpUtils;
    }
public void doGet(String url, Callback callback){
    Request request = new Request.Builder()
            .url(url)
            .build();

    client.newCall(request).enqueue(callback);

}
    public void doPost(String url, Map<String,String> params,Callback callback){

        FormBody.Builder builder = new FormBody.Builder();

        for (Map.Entry<String,String> entry : params.entrySet()){
            builder.add(entry.getKey(),entry.getValue());
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
