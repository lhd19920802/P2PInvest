package com.lhd.p2pinvest.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lhd.p2pinvest.domain.Index;

import org.xutils.x;

import java.util.List;

/**
 * Created by lihuaidong on 2017/7/31 17:15.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class HomePagerAdapter extends PagerAdapter
{
    private final Context mComtext;
    private final List<Index.ImageArrBean> imageArr;

    public HomePagerAdapter(Context mContext, List<Index.ImageArrBean> imageArr)
    {
        this.mComtext=mContext;
        this.imageArr=imageArr;
    }

    @Override
    public int getCount()
    {
        return imageArr==null?0:imageArr.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        Index.ImageArrBean imageArrBean = imageArr.get(position);
        ImageView imageView = new ImageView(mComtext);
        x.image().bind(imageView,imageArrBean.getIMAURL());
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
