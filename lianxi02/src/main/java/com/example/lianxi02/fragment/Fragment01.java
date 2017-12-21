package com.example.lianxi02.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lianxi02.R;
import com.example.lianxi02.adapter.MyfragmentAdapter;
import com.example.lianxi02.bean.GetOrdersbean;
import com.example.lianxi02.net.OKHttpUntils;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by wan on 2017/12/21.
 */

public class Fragment01 extends android.support.v4.app.Fragment {
    Handler handler = new Handler(Looper.getMainLooper());
    private XRecyclerView recycle;
    private String path="http://120.27.23.105/product/getOrders";
    List<GetOrdersbean.DataBean> list = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        recycle = view.findViewById(R.id.recycle);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        int status = bundle.getInt("status", 3);
        if(status==3){
            Map<String,String> map = new HashMap<>();
            map.put("uid","71");
            OKHttpUntils.getOkHttpUntils().doPost(path, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    final GetOrdersbean getOrdersbean = new Gson().fromJson(string, GetOrdersbean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(getOrdersbean.getData());
                            MyfragmentAdapter myfragmentAdapter = new MyfragmentAdapter(getActivity(),list);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recycle.setLayoutManager(linearLayoutManager);
                            recycle.setAdapter(myfragmentAdapter);
                        }
                    });


                }
            });
        }else if(status==2){
            Map<String,String> map = new HashMap<>();
            map.put("uid","71");
            map.put("status","2");
            OKHttpUntils.getOkHttpUntils().doPost(path, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    final GetOrdersbean getOrdersbean = new Gson().fromJson(string, GetOrdersbean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(getOrdersbean.getData());
                            MyfragmentAdapter myfragmentAdapter = new MyfragmentAdapter(getActivity(),list);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recycle.setLayoutManager(linearLayoutManager);
                            recycle.setAdapter(myfragmentAdapter);
                        }
                    });


                }
            });
        }else if(status==1){
            Map<String,String> map = new HashMap<>();
            map.put("uid","71");
            map.put("status","1");
            OKHttpUntils.getOkHttpUntils().doPost(path, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    final GetOrdersbean getOrdersbean = new Gson().fromJson(string, GetOrdersbean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(getOrdersbean.getData());
                            MyfragmentAdapter myfragmentAdapter = new MyfragmentAdapter(getActivity(),list);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recycle.setLayoutManager(linearLayoutManager);
                            recycle.setAdapter(myfragmentAdapter);
                        }
                    });


                }
            });
        }else if(status==0){
            Map<String,String> map = new HashMap<>();
            map.put("uid","71");
            map.put("status","0");
            OKHttpUntils.getOkHttpUntils().doPost(path, map, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String string = response.body().string();
                    final GetOrdersbean getOrdersbean = new Gson().fromJson(string, GetOrdersbean.class);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            list.addAll(getOrdersbean.getData());
                            MyfragmentAdapter myfragmentAdapter = new MyfragmentAdapter(getActivity(),list);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                            recycle.setLayoutManager(linearLayoutManager);
                            recycle.setAdapter(myfragmentAdapter);
                        }
                    });


                }
            });
        }
    }
}
