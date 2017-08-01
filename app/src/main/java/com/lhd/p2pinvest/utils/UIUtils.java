package com.lhd.p2pinvest.utils;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.lhd.p2pinvest.common.MyApplication;

/**
 * Created by lihuaidong on 2017/7/28 14:52.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class UIUtils
{
    public static Context getContext()
    {
        return MyApplication.context;
    }

    public static Handler getHandler()
    {
        return MyApplication.handler;
    }

    public static int getColor(int colorId)
    {
        return getContext().getResources().getColor(colorId);
    }

    public static View getView(int viewId)
    {
        return View.inflate(getContext(), viewId, null);
    }

    public static String[] getStringArr(int arrId)
    {
        return getContext().getResources().getStringArray(arrId);
    }

    //与屏幕分辨率相关的
    public static int dp2px(int dp)
    {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5);
    }

    public static int px2dp(int px)
    {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5);
    }

}
