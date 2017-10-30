package com.sampson.yjj.xianba.utils;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by yjj on 2017/10/30.
 */

public class TimeUtil {
//    /**
//     * 获取时间戳
//     * @return
//     */
//    public static int getCurrentTime(){

    public static String getTodayDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        return format.format(new Date());
    }
    /**
 * 掉此方法输入所要转换的时间输入例如（"2014年06月14日16时09分00秒"）返回时间戳
 *
 * @param time
 * @return
 */
        public static String data(String time) {
            SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
                    Locale.CHINA);
            Date date;
            String times = null;
            try {
                date = sdr.parse(time);
                long l = date.getTime();
                String stf = String.valueOf(l);
                times = stf.substring(0, 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return times;
        }

}
