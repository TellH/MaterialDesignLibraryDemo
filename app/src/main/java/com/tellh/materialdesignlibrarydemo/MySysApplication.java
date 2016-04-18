package com.tellh.materialdesignlibrarydemo;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;

/**
 * Created by tlh on 2016/4/18.
 */
public class MySysApplication extends Application {
    //全局context
    private static Context context;
    //application实例
    private static MySysApplication instance;
    public static int screenWidth;
    public static int screenHeight;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        instance = this;
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        Logger.init("tlh");
    }

    public static Context getContext() {
        return context;
    }

    public static MySysApplication getInstance() {
        return instance;
    }
}
