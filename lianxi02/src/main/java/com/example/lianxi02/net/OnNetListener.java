package com.example.lianxi02.net;

/**
 * Created by wan on 2017/12/17.
 */

public interface OnNetListener<T> {
    void onSuccess(T t);
    void onFailure(Exception e);
}
