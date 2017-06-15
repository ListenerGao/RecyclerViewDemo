package com.listenergao.recyclerviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by gys on 2017/6/14 11:15.
 */

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    /**
     * 提供Application的Context
     *
     * @return context
     */
    public static Context getContext() {
        return sContext;
    }
}
