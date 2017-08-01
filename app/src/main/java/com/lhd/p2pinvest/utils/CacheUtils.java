package com.lhd.p2pinvest.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lihuaidong on 2017/7/31 16:31.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：缓存工具类
 */
public class CacheUtils
{
    public static void putString(Context context, String key, String value)
    {

        SharedPreferences sp = context.getSharedPreferences("save_json", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(Context mContext, String key)
    {

        SharedPreferences sp = mContext.getSharedPreferences("save_json", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }
}
