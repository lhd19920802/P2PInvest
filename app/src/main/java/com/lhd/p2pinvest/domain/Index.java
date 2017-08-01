package com.lhd.p2pinvest.domain;

import java.util.List;

/**
 * Created by lihuaidong on 2017/7/31 16:38.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class Index
{

    private ProInfoBean proInfo;
    private List<ImageArrBean> imageArr;

    public ProInfoBean getProInfo()
    {
        return proInfo;
    }

    public void setProInfo(ProInfoBean proInfo)
    {
        this.proInfo = proInfo;
    }

    public List<ImageArrBean> getImageArr()
    {
        return imageArr;
    }

    public void setImageArr(List<ImageArrBean> imageArr)
    {
        this.imageArr = imageArr;
    }

    public static class ProInfoBean
    {
        private String id;
        private String memberNum;
        private String minTouMoney;
        private String money;
        private String name;
        private String progress;
        private String suodingDays;
        private String yearRate;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getMemberNum()
        {
            return memberNum;
        }

        public void setMemberNum(String memberNum)
        {
            this.memberNum = memberNum;
        }

        public String getMinTouMoney()
        {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney)
        {
            this.minTouMoney = minTouMoney;
        }

        public String getMoney()
        {
            return money;
        }

        public void setMoney(String money)
        {
            this.money = money;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getProgress()
        {
            return progress;
        }

        public void setProgress(String progress)
        {
            this.progress = progress;
        }

        public String getSuodingDays()
        {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays)
        {
            this.suodingDays = suodingDays;
        }

        public String getYearRate()
        {
            return yearRate;
        }

        public void setYearRate(String yearRate)
        {
            this.yearRate = yearRate;
        }
    }

    public static class ImageArrBean
    {
        private String ID;
        private String IMAPAURL;
        private String IMAURL;

        public String getID()
        {
            return ID;
        }

        public void setID(String ID)
        {
            this.ID = ID;
        }

        public String getIMAPAURL()
        {
            return IMAPAURL;
        }

        public void setIMAPAURL(String IMAPAURL)
        {
            this.IMAPAURL = IMAPAURL;
        }

        public String getIMAURL()
        {
            return IMAURL;
        }

        public void setIMAURL(String IMAURL)
        {
            this.IMAURL = IMAURL;
        }
    }
}
