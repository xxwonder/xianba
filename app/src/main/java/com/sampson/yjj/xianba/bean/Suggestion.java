package com.sampson.yjj.xianba.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yjj on 2017/6/19.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;
    @SerializedName("cw")
    public CarWash carWash;
    public Sport sport;
    public class Comfort{
        @SerializedName("txt")
        public String info;
    }
    public class Sport{
        @SerializedName("txt")
        public String info;

    }
    public class CarWash{
        @SerializedName("txt")
        public String info;
    }
}
