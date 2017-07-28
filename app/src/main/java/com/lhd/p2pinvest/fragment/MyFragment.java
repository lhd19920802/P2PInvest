package com.lhd.p2pinvest.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.BaseFragment;

/**
 * Created by lihuaidong on 2017/7/26 15:53.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class MyFragment extends BaseFragment
{
    private static final String TAG = MyFragment.class.getSimpleName();
    private ImageView iv_title_back;
    private ImageView iv_title_setting;
    private TextView tv_title;
    @Override
    public View initView()
    {
        Log.e(TAG, "我是我的资产");
        View view = View.inflate(mContext, R.layout.my_fragment, null);
        iv_title_back= (ImageView) view.findViewById(R.id.iv_title_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);
        iv_title_back.setVisibility(View.GONE);
        tv_title.setText("我的资产");
        return view;
    }

    @Override
    protected void initData()
    {
        super.initData();
    }
}
