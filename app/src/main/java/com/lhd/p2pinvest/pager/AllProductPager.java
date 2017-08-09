package com.lhd.p2pinvest.pager;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.adapter.AllProductBaseAdapter;
import com.lhd.p2pinvest.base.MenuDetailBasePager;
import com.lhd.p2pinvest.common.AppNetConfig;
import com.lhd.p2pinvest.domain.ProductBean;
import com.lhd.p2pinvest.utils.CacheUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by lihuaidong on 2017/8/9 15:49.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class AllProductPager extends MenuDetailBasePager
{
    private static final String TAG = AllProductPager.class.getSimpleName();
//    private TextView textView;
    private ListView lv_product_list;
    public AllProductPager(Context context)
    {
        super(context);
    }

    @Override
    public View initView()
    {
        Log.e(TAG, "我是全部理财页面");
        View view = View.inflate(mContext, R.layout.all_product, null);
        lv_product_list = (ListView) view.findViewById(R.id.lv_product_list);
        return view;
    }

    @Override
    public void initData()
    {
        super.initData();
//        textView.setText("我是全部理财页面");
        String saveJson = CacheUtils.getString(mContext, AppNetConfig.PRODUCT);
        if(saveJson!=null) {
            processData(saveJson);
        }
        getDataFromNet();

    }

    private void getDataFromNet()
    {
        new Thread(){
            public void run(){
                RequestParams entity=new RequestParams(AppNetConfig.PRODUCT);
                x.http().get(entity, new Callback.CommonCallback<String>()
                {
                    @Override
                    public void onSuccess(String result)
                    {
                        Log.e(TAG, "联网成功===="+result);
                        CacheUtils.putString(mContext, AppNetConfig.PRODUCT, result);
                        processData(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback)
                    {

                        Log.e(TAG, "联网失败===="+ex.getMessage());
                    }

                    @Override
                    public void onCancelled(CancelledException cex)
                    {

                        Log.e(TAG, "onCancelled===="+cex.getMessage());
                    }

                    @Override
                    public void onFinished()
                    {

                        Log.e(TAG, "onFinished====");
                    }
                });
            }
        }.start();
    }

    private void processData(String json)
    {
        ProductBean productBean=parseJson(json);
        Log.e(TAG, "解析成功==="+productBean.getData().get(1).getName());
        AllProductBaseAdapter adapter=new AllProductBaseAdapter(mContext,productBean.getData());
        lv_product_list.setAdapter(adapter);
    }

    private ProductBean parseJson(String json)
    {

        return new Gson().fromJson(json,ProductBean.class);
    }


}
