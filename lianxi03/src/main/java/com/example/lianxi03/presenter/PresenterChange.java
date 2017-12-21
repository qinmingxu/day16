package com.example.lianxi03.presenter;

import android.os.Handler;
import android.os.Looper;

import com.example.lianxi03.bean.DataChangeBean;
import com.example.lianxi03.model.ModuleChange;
import com.google.gson.Gson;

/**
 * Created by QinQinBaoBei on 2017/12/20.
 */

public class PresenterChange {
     private ModuleChange moduleChange = new ModuleChange();
     PresenterChangeListener presenterChangeListener;

    public PresenterChange(PresenterChangeListener presenterChangeListener) {
        this.presenterChangeListener = presenterChangeListener;
    }

    private Handler handler = new Handler(Looper.getMainLooper());
    public void getData(String status,String id){
        moduleChange.getData(status, id, new ModuleChange.ModuleChangeListener() {
            @Override
            public void success(final String s) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        DataChangeBean dataChangeBean = new Gson().fromJson(s, DataChangeBean.class);
                         if(presenterChangeListener !=null){

                             presenterChangeListener.mySuccess(dataChangeBean);

                         }
                    }
                });

            }
        });
    }
    public interface PresenterChangeListener{
        void mySuccess(DataChangeBean dataChangeBean);
    }
}
