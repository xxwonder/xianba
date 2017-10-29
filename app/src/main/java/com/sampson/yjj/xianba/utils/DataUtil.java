package com.sampson.yjj.xianba.utils;

import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;
import com.sampson.yjj.xianba.common.UrlContents;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by yjj on 2017/10/29.
 */

public class DataUtil {

    static List<XiaoHuaZuiXinBean> mXiaoHuaList;
    public static List<XiaoHuaZuiXinBean>getXiaoHuaListData() {

        OkHttpUtil.sendOkHttpRequest(UrlContents.XIAOHUA_CHAXUN + "?key=" + UrlContents.XIAOHUAN_APPKEY + "&page=2&rows=10&sort=asc&time=1418745237", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mXiaoHuaList = HandleJokeToBean.handleJokeResponseList(responseText);
            }

        });
        return mXiaoHuaList;
    }
}
