package com.lhd.p2pinvest.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 * Created by lihuaidong on 2017/7/28 14:48.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：代表整个软件
 */
public class MyApplication extends Application
{
    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
        //初始化当前的异常处理器
        CrashHandler.getInstance().init();



    }
}
