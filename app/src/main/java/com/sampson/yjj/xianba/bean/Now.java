package com.sampson.yjj.xianba.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yjj on 2017/6/19.
 */

public class Now {
    @SerializedName("tmp")
    public String temperature;
    @SerializedName("cond")
    public More more;
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
