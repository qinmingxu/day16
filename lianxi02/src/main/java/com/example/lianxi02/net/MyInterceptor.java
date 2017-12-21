package com.example.lianxi02.net;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/17.
 */

public class MyInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        FormBody body = (FormBody) request.body();
        FormBody.Builder builder = new FormBody.Builder();
        for (int i=0;i<body.size();i++){
            String name = body.name(i);
            String value = body.value(i);
            builder.add(name,value);
        }
        FormBody.Builder builder1 = builder.add("source", "android");
        FormBody build = builder1.build();
        Request request1 = request.newBuilder().post(build).build();
        return chain.proceed(request1);
    }
}
