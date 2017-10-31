package com.sampson.yjj.xianba.utils;

import com.google.gson.Gson;
import com.sampson.yjj.xianba.bean.QuTuBean;
import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * 将返回的JSON数据解析成笑话实体类
     * 笑话最新
     */
    public static List<QuTuBean> handleJokeResponseList(String response)
    {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            List<QuTuBean> mList = new ArrayList<>();
//            List<XiaoHuaZuiXinBean> mList = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++)
            {
                String JokeContent = jsonArray.getJSONObject(i).toString();
                mList.add(new Gson().fromJson(JokeContent,QuTuBean.class));
//                mList.add(new Gson().fromJson(JokeContent,XiaoHuaZuiXinBean.class));
            }
            return mList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 将返回的JSON数据解析成Qu图实LIST体类
     *
     */
    public static List<QuTuBean> handleQuTuResponseList(String response)
    {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            List<QuTuBean> mList = new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++)
            {
                String QuTuContent = jsonArray.getJSONObject(i).toString();
                mList.add(new Gson().fromJson(QuTuContent,QuTuBean.class));
            }
            return mList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> jsonTOList(String responseStr, Class<T> cls){
        List<T> mList = null;
        try {
            JSONObject jsonObject = new JSONObject(responseStr);
            JSONObject jsonObject1 = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonObject1.getJSONArray("data");

            if(mList==null)
            {
                mList=new ArrayList<>();
            }
            for (int i=0;i<jsonArray.length();i++)
            {
                String content = jsonArray.getJSONObject(i).toString();

                mList.add(new Gson().fromJson(content,cls));
            }
            return mList;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 姓氏起源随机
     */
    public static <T> T jsonToBean(String responseStr, Class<T> cls){
        T t =null;
        try {
            JSONObject jsonObject = new JSONObject(responseStr);
            String content = jsonObject.getJSONObject("result").toString();
            t = new Gson().fromJson(content,cls);
            return t;
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
