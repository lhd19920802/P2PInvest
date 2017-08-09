package com.lhd.p2pinvest.domain;

import java.util.List;

/**
 * Created by lihuaidong on 2017/8/9 16:51.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class ProductBean
{

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public List<DataBean> getData()
    {
        return data;
    }

    public void setData(List<DataBean> data)
    {
        this.data = data;
    }

    public static class DataBean
    {
        private String id;
        private String name;
        private String money;
        private String yearRate;
        private String suodingDays;
        private String minTouMoney;
        private String memberNum;
        private String progress;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }

        public String getYearRate()
        {
            return yearRate;
        }

        public void setYearRate(String yearRate)
        {
            this.yearRate = yearRate;
        }

        public String getSuodingDays()
        {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays)
        {
            this.suodingDays = suodingDays;
        }

        public String getMinTouMoney()
        {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney)
        {
            this.minTouMoney = minTouMoney;
        }

        public String getMemberNum()
        {
            return memberNum;
        }

        public void setMemberNum(String memberNum)
        {
            this.memberNum = memberNum;
        }

        public String getProgress()
        {
            return progress;
        }

        public void setProgress(String progress)
        {
            this.progress = progress;
        }
    }
}
