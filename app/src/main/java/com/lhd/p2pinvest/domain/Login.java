package com.lhd.p2pinvest.domain;

/**
 * Created by lihuaidong on 2017/8/21 16:41.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class Login
{

    private DataBean data;
    private boolean success;

    public DataBean getData()
    {
        return data;
    }

    public void setData(DataBean data)
    {
        this.data = data;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public static class DataBean
    {
        private String imageurl;
        private String iscredit;
        private String name;
        private String phone;

        public String getImageurl()
        {
            return imageurl;
        }

        public void setImageurl(String imageurl)
        {
            this.imageurl = imageurl;
        }

        public String getIscredit()
        {
            return iscredit;
        }

        public void setIscredit(String iscredit)
        {
            this.iscredit = iscredit;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }
    }
}
