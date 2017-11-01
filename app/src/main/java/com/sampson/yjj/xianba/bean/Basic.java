package com.sampson.yjj.xianba.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yjj on 2017/6/19.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public  String weatherId;

    public  Update update;

    public class Update{
        @SerializedName("loc")
        public String updateTime;
    }
}
