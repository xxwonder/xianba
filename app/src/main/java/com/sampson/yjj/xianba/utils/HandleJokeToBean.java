package com.sampson.yjj.xianba.utils;

import com.google.gson.Gson;
import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yjj on 2017/10/20.
 */

public class HandleJokeToBean {
    /**
     * 将返回的JSON数据解析成笑话实体类
     * 笑话最新
     */
    public static XiaoHuaZuiXinBean handleJokeResponse(String response)
    {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            String JokeContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(JokeContent,XiaoHuaZuiXinBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
