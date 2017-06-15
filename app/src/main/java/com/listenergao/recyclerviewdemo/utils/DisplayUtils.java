package com.listenergao.recyclerviewdemo.utils;

import com.listenergao.recyclerviewdemo.MyApplication;

/**
 * Created by gys on 2017/6/14 11:03.
 * dp px转换工具
 */

public class DisplayUtils {

    private DisplayUtils() {

    }

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(float dpValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(float pxValue) {
        final float scale = MyApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
