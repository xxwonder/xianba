package com.sampson.yjj.xianba.xiaohua;

import android.media.Image;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.ShouyeRecAdapter;
import com.sampson.yjj.xianba.adapter.XiaoHuanRecAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.QuTuBean;
import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.DataUtil;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.LogUtils;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import static org.litepal.LitePalApplication.getContext;

public class XiaoHuaActivity extends BaseActivity{
    private List<QuTuBean> mList1;
    private List<XiaoHuaZuiXinBean> mList2;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
//                    textView.setText(mXiaoHuaList.get(1).getContent()+mXiaoHuaList.get(1).getUpdatetime());

                    initRecycleView();
                    break;
                case 1:
//                    textView.setText(mQuTuList.get(0).getUrl());
////                    imageView.setImageURI(Uri.parse(mQuTuList.get(0).getUrl()));
//                    Glide.with(XiaoHuaActivity.this).load(Uri.parse(mQuTuList.get(0).getUrl())).into(imageView);
                    break;
            }
            return true;
        }
    });
    @Override
    public int getContentViewId() {
        return R.layout.activity_xiao_hua;
    }

    private RecyclerView recyclerView;
    private TextView textView;
    private ImageView imageView;
    List<XiaoHuaZuiXinBean> mXiaoHuaList;
    List<QuTuBean> mQuTuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        textView = (TextView)findViewById(R.id.txt);
//        imageView = (ImageView)findViewById(R.id.image);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_xiaohua);
        getXiaoHuaListData();
        getQuTuData();
//        initRecycleView();
    }
    private void initRecycleView(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (1,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        XiaoHuanRecAdapter adapter = new XiaoHuanRecAdapter(this,mXiaoHuaList,mQuTuList);
        recyclerView.setAdapter(adapter);
    }


    /**
     *笑话文字信息和时间
     */
        public void getXiaoHuaListData() {
        String str =  "http://api.avatardata.cn/Joke/QueryJokeByTime?key=2d7f299aad11407ba4bd2a69322adc96&page=2&rows=10&sort=asc&time=1418745237";
        OkHttpUtil.sendOkHttpRequest(str
                //UrlContents.XIAOHUA_CHAXUN + "?key=" + UrlContents.XIAOHUAN_APPKEY + "&page=2&rows=10&sort=asc&time=1418745237"
                , new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mXiaoHuaList = HandleJokeToBean.handleJokeResponseList(responseText);
                mList2=mXiaoHuaList;
                mHandler.sendEmptyMessage(1);
            }

        });
    }
    /**
     * 趣图部分
     */
    private void getQuTuData(){
        String qutuStr = UrlContents.QUTU_CHAXUN+"?key="+UrlContents.XIAOHUAN_APPKEY+"&page=2&rows=10&sort=asc&time=1418745237";
        OkHttpUtil.sendOkHttpRequest(qutuStr, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mQuTuList = HandleJokeToBean.handleQuTuResponseList(responseText);
                mList1 = mQuTuList;
                mHandler.sendEmptyMessage(0);
            }
        });
    }
}
