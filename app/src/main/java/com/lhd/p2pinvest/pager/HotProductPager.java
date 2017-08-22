package com.lhd.p2pinvest.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.MenuDetailBasePager;
import com.lhd.p2pinvest.ui.FlowLayout;
import com.lhd.p2pinvest.utils.DrawUtils;
import com.lhd.p2pinvest.utils.UIUtils;

import java.util.Random;

/**
 * Created by lihuaidong on 2017/8/9 15:49.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class HotProductPager extends MenuDetailBasePager
{
    private static final String TAG = HotProductPager.class.getSimpleName();

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld",
            "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private FlowLayout flow_hot;

    public HotProductPager(Context context)
    {
        super(context);
    }

    @Override
    public View initView()
    {
        Log.e(TAG, "我是热门理财页面");
        View view = View.inflate(mContext, R.layout.hot_product, null);
        flow_hot = (FlowLayout) view.findViewById(R.id.flow_hot);
        return view;
    }

    @Override
    public void initData()
    {
        super.initData();
        //        textView.setText("我是热门理财页面");
        Random random = new Random();
        for (int i = 0; i < datas.length; i++)
        {
            final TextView tv = new TextView(mContext);


            tv.setText(datas[i]);
            tv.setTextSize(UIUtils.dp2px(10));

            //提供边距的对象，并设置到textView中
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup
                    .LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(5);
            mp.topMargin = UIUtils.dp2px(5);
            mp.rightMargin = UIUtils.dp2px(5);
            mp.bottomMargin = UIUtils.dp2px(5);
            tv.setLayoutParams(mp);
            //设置背景
            int red = random.nextInt(210);
            int green = random.nextInt(210);
            int blue = random.nextInt(210);
            //方式一：
            //            tv.setBackground(DrawUtils.getDrawable(Color.rgb(red,green,blue),
            // UIUtils.dp2px(5)));

            //方式二：
            tv.setBackground(DrawUtils.getSelector(DrawUtils.getDrawable(Color.rgb(red, green,
                    blue), UIUtils.dp2px(5)), DrawUtils.getDrawable(Color.WHITE, UIUtils.dp2px(5)
            )));
            //设置为可点击的
            //            tv.setClickable(true);
            //当设置了点击事件时，默认textView就是可点击的了。
            tv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext, tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            //设置内边距：
            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);

            flow_hot.addView(tv);
        }

    }
}
