package com.lhd.p2pinvest.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.base.BaseFragment;
import com.lhd.p2pinvest.common.AppNetConfig;
import com.lhd.p2pinvest.domain.Index;
import com.lhd.p2pinvest.utils.CacheUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

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
    private ViewPager vp_baner;
    private Banner banner;

    @Override
    public View initView()
    {
        Log.e(TAG, "我是主页");
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        iv_title_back = (ImageView) view.findViewById(R.id.iv_title_back);
        iv_title_setting = (ImageView) view.findViewById(R.id.iv_title_setting);
        //        vp_baner = (ViewPager) view.findViewById(R.id.vp_baner);
        banner = (Banner) view.findViewById(R.id.banner);
        iv_title_back.setVisibility(View.GONE);
        iv_title_setting.setVisibility(View.GONE);
        return view;
    }

    @Override
    protected void initData()
    {
        super.initData();
        String saveJson = CacheUtils.getString(mContext, AppNetConfig.INDEX);
        if (saveJson != null)
        {
            parseJson(saveJson);
        }
        getDataFromNet();

    }

    public void getDataFromNet()
    {
        RequestParams entity = new RequestParams(AppNetConfig.INDEX);
        x.http().get(entity, new Callback.CommonCallback<String>()
        {
            @Override
            public void onSuccess(String result)
            {
                Log.e(TAG, "获取数据成功==onSuccess" + result);
                CacheUtils.putString(mContext, AppNetConfig.INDEX, result);
                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback)
            {

                Log.e(TAG, "获取数据失败==onError" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex)
            {
                Log.e(TAG, "获取数据失败==onCancelled" + cex.getMessage());

            }

            @Override
            public void onFinished()
            {

                Log.e(TAG, "onFinished");
            }
        });
    }

    private void processData(String json)
    {
        Index index = parseJson(json);
        Log.e(TAG, index.getImageArr().get(1).getIMAURL());
        //        HomePagerAdapter adapter=new HomePagerAdapter(mContext,index.getImageArr());
        //        vp_baner.setAdapter(adapter);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);

        //设置图片集合
        List<String> imagesUrl = new ArrayList<>();
        for (int i = 0; i < index.getImageArr().size(); i++)
        {
            imagesUrl.add(index.getImageArr().get(i).getIMAURL());
        }
        banner.setImages(imagesUrl, new OnLoadImageListener()
        {
            @Override
            public void OnLoadImage(ImageView view, Object url)
            {
                Glide.with(mContext).load(url).into(view);
            }
        });
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomOut);
        //设置标题集合（当banner样式有显示title时）
        String[] titles = {"硅谷金融金秋加息2%", "乐享活180天计划", "超级新手计划升级版", "FASHION安心钱包计划"};
        banner.setBannerTitle(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);



    }

    private Index parseJson(String json)
    {
        return new Gson().fromJson(json, Index.class);
    }
}
