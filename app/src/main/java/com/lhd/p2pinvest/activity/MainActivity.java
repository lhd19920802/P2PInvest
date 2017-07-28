package com.lhd.p2pinvest.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.BaseFragment;
import com.lhd.p2pinvest.fragment.HomeFragment;
import com.lhd.p2pinvest.fragment.InvestFragment;
import com.lhd.p2pinvest.fragment.MoreFragment;
import com.lhd.p2pinvest.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity
{

    private FrameLayout fl_content_main;
    private RadioGroup rg_bottom;
    private int position;

    private BaseFragment preFragment;

    private List<BaseFragment> fragments;

    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            flag = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initFragments();
        initListener();

        rg_bottom.check(R.id.rb_home);
    }

    private void initFragments()
    {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new InvestFragment());
        fragments.add(new MyFragment());
        fragments.add(new MoreFragment());

    }

    private void initListener()
    {
        rg_bottom.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener
    {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId)
        {
            switch (checkedId)
            {
                case R.id.rb_home:
                    position = 0;
                    break;
                case R.id.rb_invest:
                    position = 1;

                    break;
                case R.id.rb_my:
                    position = 2;

                    break;
                case R.id.rb_more:
                    position = 3;

                    break;
            }
            BaseFragment fragment = fragments.get(position);
            switchFragment(preFragment, fragment);
        }
    }

    private void switchFragment(BaseFragment tempFragment, BaseFragment fragment)
    {
        if (tempFragment != fragment)
        {
            preFragment = fragment;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!fragment.isAdded())
            {
                //隐藏tempFragment
                if (tempFragment != null)
                {
                    transaction.hide(tempFragment);
                }
                //添加新的fragment 并提交
                if (fragment != null)
                {
                    transaction.add(R.id.fl_content_main, fragment).commit();
                }


            }
            else
            {//隐藏tempFragment
                if (tempFragment != null)
                {
                    transaction.hide(tempFragment);
                }
                //显示新的fragment 并提交
                if (fragment != null)
                {
                    transaction.show(fragment).commit();
                }


            }
        }
    }

    private void initView()
    {
        fl_content_main = (FrameLayout) findViewById(R.id.fl_content_main);
        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);
    }

    /**
     * 双击两次返回退出应用
     */
    private boolean flag = false;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event)
    {


        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            if (!flag)
            {
                flag = true;
                Toast.makeText(MainActivity.this, "再点一次退出硅谷金融", Toast.LENGTH_SHORT).show();
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }

        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        handler.removeMessages(0);
    }
}
