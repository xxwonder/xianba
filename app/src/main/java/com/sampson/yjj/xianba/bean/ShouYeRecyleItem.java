package com.sampson.yjj.xianba.bean;

/**
 * Created by yjj on 2017/10/18.
 */

public class ShouYeRecyleItem {
    private String name;
    private int id;
    public ShouYeRecyleItem(String name,int imageId){
        this.name = name;
        this.id = imageId;
    }
    public String getName(){
        return name;
    }
    public int getId(){
        return id;
    }
}
