package com.sampson.yjj.xianba.tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.TV1RecycleAdapter;
import com.sampson.yjj.xianba.adapter.TV2RecycleAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.TVOneBean;
import com.sampson.yjj.xianba.bean.TVTwoBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TVSortActivity extends BaseActivity {
    private ImageView tv2_back_img;
    private TextView tv2_title;
    private RecyclerView tv2_recycle;
    private List<TVTwoBean> mList;
    @Override
    public int getContentViewId() {
        return R.layout.activity_tvsort;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        init();
    }
    private void init(){
        switch (getIntent().getFlags()){
            case 0:
                getTV2Data(UrlContents.TV_TWO+getIntent().getIntExtra("id",1));
                break;
            case 1:
                break;
        }
    }
    private void initView(){
        tv2_recycle=(RecyclerView)findViewById(R.id.tv2_recycle);
        tv2_title=(TextView)findViewById(R.id.tv2_title);
        tv2_back_img=(ImageView)findViewById(R.id.tv2_back_img);
        tv2_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initRecycle(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tv2_recycle.setLayoutManager(linearLayoutManager);
        TV2RecycleAdapter mAdapter = new TV2RecycleAdapter(mList,this);
        tv2_recycle.setAdapter(mAdapter);
    }
    /**
     * 请求数据
     */
    private void getTV2Data(String str){
        OkHttpUtil.sendOkHttpRequest(str, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mList = HandleJokeToBean.json2TOList(responseText, TVTwoBean.class);
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
