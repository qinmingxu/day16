package com.example.lianxi02.net;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wan on 2017/12/20.
 */

public class SharedUtil {
    public static String CONFIG  = "config";//保存的文件的名字
    public static SharedPreferences preferences;

    /**
     * 保存布尔类型的数据
     * @param context
     * @param key 键
     * @param value 存的值
     */
    public static void saveBooleanData(Context context, String key, boolean value){
        if (preferences == null) {
            preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        //存值
        preferences.edit().putBoolean(key, value).commit();
    }
    /**
     * 取值................boolean数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBooleanData(Context context,String key,boolean defValue){
        if (preferences == null) {
            preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return preferences.getBoolean(key, defValue);
    }
    ///存string类型的数据
    public static void saveStringData(Context context,String key,String value){
        if (preferences == null) {
            preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }
        //存值
        preferences.edit().putString(key, value).commit();
    }
    //获取string的数据
    public static String getStringData(Context context,String key,String defValue){
        if (preferences == null) {
            preferences = context.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        }

        return preferences.getString(key, defValue);
    }
    /////////////////用法
    //进入页面之后获取存的那个boolean数据,判断...false表示没进入过,第一次进入
    /*boolean flag = SharedUtil.getBooleanData(MainActivity.this, "flag", false);
    if (flag) {//已经进入过...延时跳转到第三个界面
        handler.sendEmptyMessageDelayed(0, 5000);

    }else {//存入已经进入的boolean数据,,,延时跳转到第二个界面
        SharedUtil.saveBooleanData(MainActivity.this, "flag", true);

        handler.sendEmptyMessageDelayed(1, 5000);*//*
    }*/
}
