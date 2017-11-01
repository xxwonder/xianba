package com.sampson.yjj.xianba.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yjj on 2017/6/19.
 */

public class ForeCast {
    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;
    public class Temperature{
        public String max;
        public String min;
    }
    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
