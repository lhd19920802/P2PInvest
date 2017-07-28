package com.lhd.p2pinvest.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.BaseFragment;

/**
 * Created by lihuaidong on 2017/7/26 15:53.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class HomeFragment extends BaseFragment
{
    private static final String TAG = HomeFragment.class.getSimpleName();
    private ImageView iv_title_back;
    private ImageView iv_title_setting;
    @Override
    public View initView()
    {
        Log.e(TAG, "我是主页");
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        iv_title_back= (ImageView) view.findViewById(R.id.iv_title_back);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);
        iv_title_back.setVisibility(View.GONE);
        iv_title_setting.setVisibility(View.GONE);
        return view;
    }

    @Override
    protected void initData()
    {
        super.initData();
    }
}
