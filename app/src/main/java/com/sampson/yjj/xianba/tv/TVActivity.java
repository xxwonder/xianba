package com.sampson.yjj.xianba.tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.TV1RecycleAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.TVOneBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TVActivity extends BaseActivity {
    private ImageView tv1_back_img;
    private TextView tv1_title;
    private RecyclerView tv1_recycle;
    private List<TVOneBean> mList;
    @Override
    public int getContentViewId() {
        return R.layout.activity_tv;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        getTV1Data(UrlContents.TV_ONE);
    }
    private void initView(){
        tv1_recycle=(RecyclerView)findViewById(R.id.tv1_recycle);
        tv1_title=(TextView)findViewById(R.id.tv1_title);
        tv1_back_img=(ImageView)findViewById(R.id.tv1_back_img);
        tv1_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initRecycle(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tv1_recycle.setLayoutManager(gridLayoutManager);
        TV1RecycleAdapter mAdapter = new TV1RecycleAdapter(mList,this);
        tv1_recycle.setAdapter(mAdapter);
    }
    /**
     * 请求数据
     */
    private void getTV1Data(String str){
        OkHttpUtil.sendOkHttpRequest(str, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mList = HandleJokeToBean.json2TOList(responseText, TVOneBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecycle();
                    }
                });
            }
        });
    }
}
