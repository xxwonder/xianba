package com.sampson.yjj.xianba.todayhistory;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.HistoryListAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.HistoryDayBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.DateUtil;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HistoryToday extends BaseActivity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.historyday_back_img:
                finish();
                break;
            case R.id.historyday_search_img:
                Intent intent = new Intent(HistoryToday.this,SearchHistoryDayActivity.class);
                startActivity(intent);
                break;
        }
    }

    String historyTodayUrl = UrlContents.HISTORY_TODAY+"&yue="+ DateUtil.getCurrentMonth()+
            "&ri="+DateUtil.getCurrentMonthOfDay()+"&type=1";
    List<HistoryDayBean> mHistoryDayList;
    private ListView history_day_listview;
    private ImageView historyday_back_img;
    private ImageView historyday_search_img;
    @Override
    public int getContentViewId() {
        return R.layout.activity_history_today;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        init();
        initListener();
    }
    private void initListener(){
        historyday_back_img.setOnClickListener(this);
        historyday_search_img.setOnClickListener(this);
    }
    private void initView(){
        history_day_listview = (ListView)findViewById(R.id.history_day_listview);
        historyday_back_img = (ImageView)findViewById(R.id.historyday_back_img);
        historyday_search_img= (ImageView)findViewById(R.id.historyday_search_img);
    }
    private void init(){
        switch (getIntent().getFlags()){
            case 0:
                getHistoryDayData(DateUtil.getCurrentMonth(),DateUtil.getCurrentMonthOfDay());
                break;
            case 1:
                break;
        }
    }
    private void getHistoryDayData(int yue,int day){
        OkHttpUtil.sendOkHttpRequest(UrlContents.HISTORY_TODAY +
                "&yue=" + yue + "&ri=" + day + "&type=1", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mHistoryDayList = HandleJokeToBean.json2TOList(responseText,HistoryDayBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initAdapter(mHistoryDayList,HistoryToday.this);
                    }
                });
            }
        });
    }
    /**
     * initAdapter
     */
    private void initAdapter(List<HistoryDayBean> mlist, Context context){
        HistoryListAdapter mAdapter = new HistoryListAdapter(mlist,context);
        history_day_listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
