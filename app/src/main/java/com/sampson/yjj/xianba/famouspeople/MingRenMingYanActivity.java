package com.sampson.yjj.xianba.famouspeople;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.FamousAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.FamousPeopleBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MingRenMingYanActivity extends BaseActivity {
    FamousPeopleBean famousPeopleBean = null;
    private ImageView famous_back_img;
    private EditText famous_edit;
    private TextView famous_search;
    private TextView mingyan_txt;
    private TextView mingren_txt;
    private ListView famous_listview;
    private List<FamousPeopleBean> mList;
    @Override
    public int getContentViewId() {
        return R.layout.activity_ming_ren_ming_yan;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initCliclListener();
        getMingRenMingYanData(UrlContents.FAMOUS_PEOPLE);
    }
    private void initView(){
        famous_back_img = (ImageView)findViewById(R.id.famous_back_img);
        famous_edit = (EditText)findViewById(R.id.famous_edit);
        famous_search = (TextView)findViewById(R.id.famous_search);
        mingyan_txt = (TextView)findViewById(R.id.mingyan_txt);
        mingren_txt = (TextView)findViewById(R.id.mingren_txt);
        famous_listview = (ListView)findViewById(R.id.famous_listview);
    }
    private void initCliclListener(){
        famous_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        famous_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMingYanData(famous_edit.getText().toString());
            }
        });
    }
    /**
     * 随机获取一条数据
     */
    private void getMingRenMingYanData(String str){
        OkHttpUtil.sendOkHttpRequest(str, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                famousPeopleBean = HandleJokeToBean.jsonToBean(responseText,FamousPeopleBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mingyan_txt.setText(famousPeopleBean.getFamous_saying());
                        mingren_txt.setText("---------"+famousPeopleBean.getFamous_name());
                    }
                });
            }
        });
    } /**
     * 随机获取一条数据
     */
    private void getMingYanData(String str){
        OkHttpUtil.sendOkHttpRequest(UrlContents.FAMOUS_PEOPLE_CHAXUN+str, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mList = HandleJokeToBean.json2TOList(responseText,FamousPeopleBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        mingyan_txt.setText(mList.get(0).getFamous_saying()+"------00");
                        initAdapter(mList);
                    }
                });
            }
        });
    }
    private void initAdapter(List<FamousPeopleBean> mList){
        FamousAdapter mAdapter = new FamousAdapter(mList,MingRenMingYanActivity.this);
        famous_listview.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
