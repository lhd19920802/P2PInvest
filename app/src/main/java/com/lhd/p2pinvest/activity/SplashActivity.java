package com.lhd.p2pinvest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.lhd.p2pinvest.R;

public class SplashActivity extends Activity
{

    private RelativeLayout rl_splash;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //隐藏顶部状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
        startAnimation();
        startMainActivity();
    }

    /**
     * 启动动画
     */
    private void startAnimation()
    {

        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        rl_splash.startAnimation(aa);
    }

    private void startMainActivity()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }


}
