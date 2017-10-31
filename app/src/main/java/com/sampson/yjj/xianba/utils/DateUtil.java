package com.sampson.yjj.xianba.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by yjj on 2017/10/31.
 */

public class DateUtil {
    /**
     * 获取当前月
     * @return
     */
    public static int getCurrentMonth(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currTime=new Date();
//        int year=currTime.getYear();//年 
        int month = currTime.getMonth()+1;
        // int week = currTime.getDay(); //星期几 
        return month;
    }

    /**
     * 获取当前月的日
     * @return
     */
    public static int getCurrentMonthOfDay(){
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currTime=new Date();
        int day=currTime.getDate();//日
        return day;
    }
}
