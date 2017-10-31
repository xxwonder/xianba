package com.sampson.yjj.xianba.bean;

import java.io.Serializable;

/**
 * Created by yjj on 2017/10/31.
 */

public class XingShiBean implements Serializable{

    private String xing;
    private String intro;

    public String getXing() {
        return xing;
    }

    public void setXing(String xing) {
        this.xing = xing;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
