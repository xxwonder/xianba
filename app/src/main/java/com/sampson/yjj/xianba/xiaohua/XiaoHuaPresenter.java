package com.sampson.yjj.xianba.xiaohua;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.LogUtils;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

/**
 * Created by yjj on 2017/10/19.
 */

public class XiaoHuaPresenter implements XiaoHuaContract.Presenter {

    private String urlXiaoHuaZuiXin = UrlContents.XIAOHUA_ZUIXIN +"?key="+ UrlContents.XIAOHUAN_APPKEY;
    private Context context;
    private XiaoHuaContract.View view;
    String responseText ;

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Toast.makeText(getContext(),"加载失败",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getContext(),"加载成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    public XiaoHuaPresenter(Context context){
        this.context = context;
    }
    public XiaoHuaPresenter(Context context, XiaoHuaContract.View view){
        this.context = context;
        this.view = view;
    }
    @Override
    public void getResponseData() {
        OkHttpUtil.sendOkHttpRequest(//UrlContents.XIAOHUA_ZUIXIN +"?key="+ UrlContents.XIAOHUAN_APPKEY,
                "http://api.avatardata.cn/Joke/NewstJoke?key=2d7f299aad11407ba4bd2a69322adc96",
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                        mHandler.sendEmptyMessage(0);
//                        return "请求失败";
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        responseText = response.body().string();
                        LogUtils.e("yjj",responseText);
                        mHandler.sendEmptyMessage(1);
//                        return responseText;
                    }
                });
//        return responseText+"890";
    }

    @Override
    public void start() {

    }
}
