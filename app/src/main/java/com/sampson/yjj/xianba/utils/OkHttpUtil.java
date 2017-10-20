package com.sampson.yjj.xianba.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by yjj on 2017/10/19.
 */

public class OkHttpUtil {

        /**
         * 传入一个请求地址，并注册一个回调来处理服务器响应
         * @param address
         * @param callback
         */
        public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(address).build();
            client.newCall(request).enqueue(callback);
        }

}
