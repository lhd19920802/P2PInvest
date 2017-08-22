package com.lhd.p2pinvest.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.MenuDetailBasePager;
import com.lhd.p2pinvest.ui.randomLayout.StellarMap;
import com.lhd.p2pinvest.utils.UIUtils;

import java.util.Random;

/**
 * Created by lihuaidong on 2017/8/9 15:49.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class RecommendProductPager extends MenuDetailBasePager
{
    private static final String TAG = RecommendProductPager.class.getSimpleName();
    private StellarMap stellarMap;

    private String[] datas = new String[]{"新手福利计划", "财神道90天计划", "硅谷钱包计划", "30天理财计划(加息2%)",
            "180天理财计划(加息5%)", "月月升理财计划(加息10%)", "中情局投资商业经营", "大学老师购买车辆", "屌丝下海经商计划", "美人鱼影视拍摄投资",
            "Android培训老师自己周转", "养猪场扩大经营", "旅游公司扩大规模", "摩托罗拉洗钱计划", "铁路局回款计划", "屌丝迎娶白富美计划"};

    //分为两组数据
    private String[] firstDatas = new String[datas.length / 2];
    private String[] secondDatas = new String[datas.length - datas.length / 2];
    private Random random;

    public RecommendProductPager(Context context)
    {
        super(context);
    }

    @Override
    public View initView()
    {
        Log.e(TAG, "我是推荐理财页面");
        View view = View.inflate(mContext, R.layout.recommend_product, null);
        stellarMap = (StellarMap) view.findViewById(R.id.stellarMap);


        return view;
    }

    @Override
    public void initData()
    {
        super.initData();
        //给两组数组数据赋值
        for (int i = 0; i < datas.length; i++)
        {
            if (i < datas.length / 2)
            {
                firstDatas[i] = datas[i];
            }
            else
            {
                secondDatas[i - datas.length / 2] = datas[i];
            }
        }

        StellerAdapter stellerAdapter = new StellerAdapter();
        //加载显示
        stellarMap.setAdapter(stellerAdapter);

        //必须提供如下两个方法的调用，否则没有显示效果
        //设置初始化显示的组别，以及是否使用动画
        stellarMap.setGroup(0, true);
        //设置x,y轴方向上的稀疏度
        stellarMap.setRegularity(5, 5);
    }

    class StellerAdapter implements StellarMap.Adapter
    {


        //返回显示的组数
        @Override
        public int getGroupCount()
        {
            return 2;
        }

        //返回指定组的元素的个数
        @Override
        public int getCount(int group)
        {
            if (group == 0)
            {
                return datas.length / 2;
            }
            else
            {
                return datas.length - datas.length / 2;
            }
        }

        /**
         * 返回指定组的指定位置上的view
         *
         * @param group
         * @param position    :对于每组数据来讲，position都从0开始
         * @param convertView
         * @return
         */
        @Override
        public View getView(int group, int position, View convertView)
        {
            random = new Random();
            final TextView tv = new TextView(mContext);
            if (group == 0)
            {
                tv.setText(firstDatas[position]);
            }
            else
            {
                tv.setText(secondDatas[position]);
            }
            //提供随机的三色
            int red = random.nextInt(200);
            int green = random.nextInt(200);
            int blue = random.nextInt(200);
            tv.setTextColor(Color.rgb(red, green, blue));

            tv.setTextSize(random.nextInt(UIUtils.dp2px(5)) + UIUtils.dp2px(5));

            //给TextView设置监听
            tv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(mContext, tv.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            return tv;
        }

        /**
         * 下一组显示平移动画的组别。查看源代码发现，此方法从未被调用，不用重写
         *
         * @param group
         * @param degree
         * @return
         */
        @Override
        public int getNextGroupOnPan(int group, float degree)
        {
            return 0;
        }

        /**
         * 下一组显示缩放动画的组别。
         *
         * @param group
         * @param isZoomIn
         * @return
         */
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn)
        {
            if (group == 0)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }


}
