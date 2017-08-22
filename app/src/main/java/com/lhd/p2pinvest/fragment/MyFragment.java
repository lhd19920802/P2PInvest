package com.lhd.p2pinvest.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.activity.LoginActivity;
import com.lhd.p2pinvest.base.BaseFragment;
import com.lhd.p2pinvest.common.AppNetConfig;
import com.lhd.p2pinvest.domain.Login;
import com.lhd.p2pinvest.utils.BitmapUtils;
import com.lhd.p2pinvest.utils.CacheUtils;
import com.lhd.p2pinvest.utils.UIUtils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

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
    //头像
    private ImageView imageView1;
    //用户名字
    private TextView textView11;
    @Override
    public View initView()
    {
        Log.e(TAG, "我是我的资产");
        View view = View.inflate(mContext, R.layout.my_fragment, null);
        iv_title_back = (ImageView) view.findViewById(R.id.iv_title_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);

        imageView1 = (ImageView) view.findViewById(R.id.imageView1);

        textView11 = (TextView) view.findViewById(R.id.textView11);

        iv_title_back.setVisibility(View.GONE);
        tv_title.setText("我的资产");
        return view;
    }

    @Override
    protected void initData()
    {
        super.initData();

        //判断是否需要进行登录的提示
        isLogin();
    }

    private void isLogin()
    {
        String result = CacheUtils.getString(mContext, AppNetConfig.LOGIN);
        if (TextUtils.isEmpty(result))
        {
            //提示登录
            Login();
        }
        else
        {
            //显示用户信息
            showUserInfo(result);
        }
    }



    private void showUserInfo(String json)
    {
        processData(json);
    }

    private void processData(String json)
    {
        Login login = parseJson(json);
        Log.e(TAG, "解析数据从成功，名字===" + login.getData().getName());
//        x.image().bind(imageView1,login.getData().getImageurl());
        //加载显示用户头像 并将其裁剪为圆形
        Picasso.with(getActivity()).load(login.getData().getImageurl()).transform(new Transformation()
        {
            @Override
            public Bitmap transform(Bitmap source)
            {//source:矩形的Bitmap对象
                //1.压缩处理
                Bitmap zoomBitmp = BitmapUtils.zoom(source, UIUtils.dp2px(62), UIUtils.dp2px(62));
                //2.圆形处理
                Bitmap bitmap = BitmapUtils.circleBitmap(zoomBitmp);
                //回收source
                source.recycle();
                return bitmap;//返回圆形的Bitmap对象
            }

            @Override
            public String key()
            {
                return "";//只要返回值不为空即可
            }
        }).into(imageView1);
        textView11.setText(login.getData().getName());
    }

    private Login parseJson(String json)
    {
        return new Gson().fromJson(json, Login.class);
    }

    private void Login()
    {
        new AlertDialog.Builder(mContext).setTitle("提示").setMessage("请先登录哦").setPositiveButton
                ("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                //跳转到用户登录页面
                Intent intent = new Intent(mContext, LoginActivity.class);

                MyFragment.this.getActivity().startActivity(intent);
            }
        }).setCancelable(false).show();
    }

//    /**
//     * 跳转到用户登录页面
//     */
//
//    private void startLoginActivity()
//    {
//
//    }


}
