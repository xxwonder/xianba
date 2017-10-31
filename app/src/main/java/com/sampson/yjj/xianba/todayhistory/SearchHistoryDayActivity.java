package com.sampson.yjj.xianba.todayhistory;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.HistoryListAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.HistoryDayBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchHistoryDayActivity extends BaseActivity implements View.OnClickListener{
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.history_back_img:
                finish();
                break;
            case R.id.history_search:
                getHistoryDayData(Integer.parseInt(history_month.getText().toString())
                        ,Integer.parseInt(history_day.getText().toString()));
                break;
        }
    }

    private ListView history_search_listview;
    private EditText history_month;
    private EditText history_day;
    private ImageView history_back_img;
    private TextView history_search;
    List<HistoryDayBean> mHistoryDayList;
    @Override
    public int getContentViewId() {
        return R.layout.activity_search_history_day;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        init();
    }
    private void init(){
        history_back_img.setOnClickListener(this);
        history_search.setOnClickListener(this);
    }
    private void initView(){
        history_month = (EditText) findViewById(R.id.history_month);
        history_day = (EditText) findViewById(R.id.history_day);
        history_back_img = (ImageView) findViewById(R.id.history_back_img);
        history_search = (TextView) findViewById(R.id.history_search);
        history_search_listview = (ListView) findViewById(R.id.history_search_listview);
    }
    /**
     * 获取莫一天的历史上
     */
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
                        initAdapter(mHistoryDayList,SearchHistoryDayActivity.this);
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
        history_search_listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
