package com.lhd.p2pinvest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lhd.p2pinvest.R;
import com.lhd.p2pinvest.domain.ProductBean;
import com.lhd.p2pinvest.ui.RoundProgress;

import java.util.List;

/**
 * Created by lihuaidong on 2017/8/9 16:38.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class AllProductBaseAdapter extends BaseAdapter
{


    private final Context mContext;
    private final List<ProductBean.DataBean> data
            ;

    public AllProductBaseAdapter(Context mContext, List<ProductBean.DataBean> data)
    {
        this.mContext=mContext;
        this.data=data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_all_product, null);
            viewHolder.pName= (TextView) convertView.findViewById(R.id.p_name);
            viewHolder.pMoney= (TextView) convertView.findViewById(R.id.p_money);
            viewHolder.pYearlv= (TextView) convertView.findViewById(R.id.p_yearlv);
            viewHolder.pSuodingdays= (TextView) convertView.findViewById(R.id.p_suodingdays);
            viewHolder.pMinzouzi= (TextView) convertView.findViewById(R.id.p_minzouzi);
            viewHolder.pMinnum= (TextView) convertView.findViewById(R.id.p_minnum);
            viewHolder.pProgresss= (RoundProgress) convertView.findViewById(R.id.p_progresss);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProductBean.DataBean dataBean = data.get(position);
        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pYearlv.setText(dataBean.getYearRate());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
        viewHolder.pMinnum.setText(dataBean.getMemberNum());
        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        return convertView;
    }
    static class ViewHolder
    {
        TextView pName;
        TextView pMoney;
        TextView pYearlv;
        TextView pSuodingdays;
        TextView pMinzouzi;
        TextView pMinnum;
        RoundProgress pProgresss;
    }
}
