package com.sampson.yjj.xianba.firstname;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.XingShiBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FirstNameActivity extends BaseActivity {
    private XingShiBean mXingShiBean;
    private TextView xingshi_name;
    private TextView xingshi_origin;
    private ImageView xingshi_search_img;
    private ImageView xinshi_back_img;
    String xingshiOriginUrl= UrlContents.FIRST_NAME_RADOM+UrlContents.FIRST_NAME_KEY;
    @Override
    public int getContentViewId() {
        return R.layout.activity_first_name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
//        getXingOriginShiData();
        init();
    }
    private void init(){
        switch (getIntent().getFlags())
        {
            case 1:
                mXingShiBean =(XingShiBean) getIntent().getSerializableExtra("bean");
                showXingshiInfo("姓氏:"+mXingShiBean.getXing(),"起源:"+mXingShiBean.getIntro());
                break;
            case 0:
                getXingOriginShiData();
                break;
        }
    }
    private void initView(){
        xingshi_name =(TextView) findViewById(R.id.xingshi_name);
        xingshi_origin =(TextView) findViewById(R.id.xingshi_origin);
        xingshi_search_img = (ImageView) findViewById(R.id.xingshi_search_img);
        xinshi_back_img = (ImageView) findViewById(R.id.xinshi_back_img);
        xinshi_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xingshi_search_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstNameActivity.this,XingShiSearchActivity.class));
                finish();
            }
        });
    }

    /**
     * 随机获取一条数据
     */
    private void getXingOriginShiData(){
        OkHttpUtil.sendOkHttpRequest(xingshiOriginUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mXingShiBean = HandleJokeToBean.jsonToBean(responseText,XingShiBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showXingshiInfo("姓氏:"+mXingShiBean.getXing(),"起源:"+mXingShiBean.getIntro());
//                        xingshi_name.setText("姓氏:"+mXingShiBean.getXing());
//                        xingshi_origin.setText("起源:"+mXingShiBean.getIntro());
                    }
                });
            }
        });
    }
    /**
     * dialog显示
     */
    private ProgressDialog progressDialog;
    public void showProgressDialog(String title, String message) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, title,message, true, false);
        } else if (progressDialog.isShowing()) {
            progressDialog.setTitle(title);
            progressDialog.setMessage(message);
        }
        progressDialog.show();
    }
    /*
 * 隐藏提示加载
 */
    public void hideProgressDialog() {

        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

    }

    /**
     * 显示姓氏信息
     * @param name
     * @param intro
     */
    private void showXingshiInfo(String name,String intro){
        xingshi_name.setText(name);
        xingshi_origin.setText(intro);
    }
}
