package com.sampson.yjj.xianba.xiaohua;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.LogUtils;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

public class XiaoHuaActivity extends AppCompatActivity{

    private TextView textView;
    String responseText = "wu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_hua);

        textView = (TextView)findViewById(R.id.txt);
        getResponseData();

    }


    public void getResponseData() {
        OkHttpUtil.sendOkHttpRequest(//UrlContents.XIAOHUA_ZUIXIN +"?key="+ UrlContents.XIAOHUAN_APPKEY,
                "http://api.avatardata.cn/Joke/NewstJoke?key=2d7f299aad11407ba4bd2a69322adc96",
                new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        responseText = response.body().string();
                        final XiaoHuaZuiXinBean xiaoHuaZuiXinBean = HandleJokeToBean.handleJokeResponse(responseText);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(xiaoHuaZuiXinBean.getContent());
                            }
                        });
                    }
                });
    }
    /**
     *
     */

}
