package com.lhd.p2pinvest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.common.AppNetConfig;
import com.lhd.p2pinvest.domain.Login;
import com.lhd.p2pinvest.utils.CacheUtils;
import com.lhd.p2pinvest.utils.MD5Utils;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class LoginActivity extends Activity
{

    private static final String TAG = LoginActivity.class.getSimpleName();
    private ImageView iv_title_back;
    private ImageView iv_title_setting;
    private TextView tv_title;
    private EditText log_ed_mob;
    private EditText log_ed_pad;

    //    private Button log_log_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //        TextView textView=new TextView(this);
        //        textView.setText("我是登录页面");
        //        textView.setTextSize(40);
        //        textView.setGravity(Gravity.CENTER);
        //        setContentView(textView);

        initView();
        initTitle();

    }

    private void initView()
    {
        iv_title_back = (ImageView) findViewById(R.id.iv_title_back);
        iv_title_setting = (ImageView) findViewById(R.id.iv_title_setting);
        tv_title = (TextView) findViewById(R.id.tv_title);
        log_ed_mob = (EditText) findViewById(R.id.log_ed_mob);
        log_ed_pad = (EditText) findViewById(R.id.log_ed_pad);
        //        log_log_btn = (Button)findViewById(R.id.log_log_btn);
    }

    protected void initTitle()
    {
        iv_title_back.setVisibility(View.VISIBLE);
        tv_title.setText("用户登录");
        iv_title_setting.setVisibility(View.INVISIBLE);
    }


    //    @OnClick(R.id.iv_top_back)
    //    public void back(View view){
    ////        this.removeCurrentActivity();
    //        this.removeAll();
    //        goToActivity(MainActivity.class,null);
    //    }

    public void login(View view)
    {
        //1.获取手机号和加密以后的密码
        String number = log_ed_mob.getText().toString().trim();
        String password = log_ed_pad.getText().toString().trim();
        //判断输入的信息是否存在空
        if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(password))
        {
            //2.联网将用户数据发送给服务器，其中手机号和密码作为请求参数
            final String url = AppNetConfig.LOGIN;
            RequestParams params = new RequestParams(url);
            params.addBodyParameter("phone", number);
            params.addBodyParameter("password", MD5Utils.MD5(password));
            //            client.post(url, params, new AsyncHttpResponseHandler()
            //            {
            //                //3.得到响应数据：成功
            //                @Override
            //                public void onSuccess(String content)
            //                {
            //                    Log.e("TAG", content);
            //                    //3.1解析json数据
            //                                JSONObject jsonObject = JSON.parseObject(content);
            //                    boolean isSuccess = jsonObject.getBoolean("success");
            //                    if (!isSuccess)
            //                    {
            //                        Toast.makeText(LoginActivity.this, "用户名不存在或密码不正确", Toast
            // .LENGTH_SHORT)
            //                                .show();
            //
            //                    }
            //                    else
            //                    {
            //                        String data = jsonObject.getString("data");
            //                        User user = JSON.parseObject(data, User.class);
            //                        //3.2保存得到的用户信息（使用sp存储）
            //                        saveUser(user);
            //                        //3.3重新加载页面，显示用户的信息在MeFragment中
            //                                    LoginActivity.this.removeAll();
            //                        LoginActivity.this.goToActivity(MainActivity.class, null);
            //                    }
            //
            //                }
            //
            //                //4.连接失败
            //                @Override
            //                public void onFailure(Throwable error, String content)
            //                {
            //                    Log.e("TAG", "fail");
            //                    Toast.makeText(LoginActivity.this, "联网失败", Toast.LENGTH_SHORT)
            // .show();
            //                }
            //            });

            x.http().post(params, new Callback.CommonCallback<String>()
            {
                @Override
                public void onSuccess(String result)
                {
                    Log.e(TAG, "得到相应数据===" + result);
                    JSONObject jsonObject = null;
                    try
                    {
                        jsonObject = new JSONObject(result);
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                    boolean isSuccess = jsonObject.optBoolean("success");
                    if (!isSuccess)
                    {
                        Toast.makeText(LoginActivity.this, "用户名不存在或密码不正确", Toast.LENGTH_SHORT)
                                .show();
                    }
                    else
                    {
                        //保存并解析
                        CacheUtils.putString(LoginActivity.this, url, result);
                        processData(result);
                    }


                }

                @Override
                public void onError(Throwable ex, boolean isOnCallback)
                {
                    Log.e(TAG, "联网失败===" + ex.getMessage());

                }

                @Override
                public void onCancelled(CancelledException cex)
                {
                    Log.e(TAG, "onCancelled==");
                }

                @Override
                public void onFinished()
                {
                    Log.e(TAG, "onFinished==");

                }
            });
        }
        else
        {
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        }
    }

    private void processData(String json)
    {
        Login login = parseJson(json);
        Log.e(TAG, "解析数据从成功，名字===" + login.getData().getName());


        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private Login parseJson(String json)
    {
        return new Gson().fromJson(json, Login.class);
    }
}
