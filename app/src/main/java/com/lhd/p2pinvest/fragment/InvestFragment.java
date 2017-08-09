package com.lhd.p2pinvest.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.BaseFragment;
import com.lhd.p2pinvest.base.MenuDetailBasePager;
import com.lhd.p2pinvest.pager.AllProductPager;
import com.lhd.p2pinvest.pager.HotProductPager;
import com.lhd.p2pinvest.pager.RecommendProductPager;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lihuaidong on 2017/7/26 15:53.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class InvestFragment extends BaseFragment
{
    private static final String TAG = InvestFragment.class.getSimpleName();
    private ImageView iv_title_back;
    private ImageView iv_title_setting;
    private TextView tv_title;
    private TabPageIndicator tab_indictor;
    private ViewPager pager;

    //页签页面的数据的集合
    private List<MenuDetailBasePager> tableDetailList;

    @Override
    public View initView()
    {
        Log.e(TAG, "我是投资");
        View view = View.inflate(mContext, R.layout.invest_fragment, null);
        iv_title_back = (ImageView) view.findViewById(R.id.iv_title_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);
        tab_indictor = (TabPageIndicator) view.findViewById(R.id.tab_indictor);
        pager = (ViewPager) view.findViewById(R.id.pager);
        iv_title_back.setVisibility(View.GONE);
        iv_title_setting.setVisibility(View.GONE);
        tv_title.setText("投资");

        return view;
    }


    @Override
    protected void initData()
    {
        super.initData();
        tableDetailList = new ArrayList<>();


        tableDetailList.add(new AllProductPager(mContext));
        tableDetailList.add(new RecommendProductPager(mContext));
        tableDetailList.add(new HotProductPager(mContext));


        pager.setAdapter(new MyPagerAdapter());
        tab_indictor.setViewPager(pager);


    }

    class MyPagerAdapter extends PagerAdapter

    {
        @Override
        public int getCount()
        {
            return tableDetailList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            MenuDetailBasePager menuDetailBasePager = tableDetailList.get(position);

            container.addView(menuDetailBasePager.rootView);
            menuDetailBasePager.initData();
            return menuDetailBasePager.rootView;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return getContext().getResources().getStringArray(R.array.invest_tab)[position];

        }
    }


}
