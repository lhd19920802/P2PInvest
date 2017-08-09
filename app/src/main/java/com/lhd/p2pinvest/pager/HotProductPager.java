package com.lhd.p2pinvest.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.lhd.p2pinvest.base.MenuDetailBasePager;

/**
 * Created by lihuaidong on 2017/8/9 15:49.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class HotProductPager extends MenuDetailBasePager
{
    private static final String TAG = HotProductPager.class.getSimpleName();
    private TextView textView;
    public HotProductPager(Context context)
    {
        super(context);
    }

    @Override
    public View initView()
    {
        Log.e(TAG, "我是热门理财页面");
        textView = new TextView(mContext);
        textView.setTextColor(30);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData()
    {
        super.initData();
        textView.setText("我是热门理财页面");
    }
}
