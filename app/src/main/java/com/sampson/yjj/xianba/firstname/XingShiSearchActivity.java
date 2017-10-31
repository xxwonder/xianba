package com.sampson.yjj.xianba.firstname;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

public class XingShiSearchActivity extends BaseActivity implements View.OnClickListener{
    private XingShiBean mXingShiBean;
    String xingshiUrl = UrlContents.FIRST_NAME_CHAXUN+UrlContents.FIRST_NAME_KEY+"&xingshi=";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.xingshi_search_btn:
//                Toast.makeText(XingShiSearchActivity.this,"123",Toast.LENGTH_SHORT).show();
                getXingshiOrigin(xingshi_edit_content.getText().toString());
                break;
            case R.id.xingshi_search_back_img:
                finish();
                break;
            case R.id.text_yang:
                getXingshiOrigin("杨");
                break;
            case R.id.text_zhang:
                getXingshiOrigin("张");
                break;
            case R.id.text_zhao:
                getXingshiOrigin("赵");
                break;
        }
    }

    private EditText xingshi_edit_content;
    private TextView xingshi_search_btn;
    private TextView text_yang;
    private TextView text_zhang;
    private TextView text_zhao;
    private ImageView xingshi_search_back_img;
    @Override
    public int getContentViewId() {
        return R.layout.activity_xing_shi_search;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
    }
    private void initListener(){
        xingshi_search_btn.setOnClickListener(this);
        xingshi_search_back_img.setOnClickListener(this);
        text_yang.setOnClickListener(this);
        text_zhang.setOnClickListener(this);
        text_zhao.setOnClickListener(this);
    }
    private void init(){
        xingshi_edit_content =(EditText) findViewById(R.id.xingshi_edit_content);
        xingshi_search_btn =(TextView) findViewById(R.id.xingshi_search_btn);
        text_yang =(TextView) findViewById(R.id.text_yang);
        text_zhang =(TextView) findViewById(R.id.text_zhang);
        text_zhao =(TextView) findViewById(R.id.text_zhao);
        xingshi_search_back_img =(ImageView) findViewById(R.id.xingshi_search_back_img);


    }
    /**
     * 获取搜索的姓氏内容
     */
    private void getXingshiOrigin(String str){
        OkHttpUtil.sendOkHttpRequest(xingshiUrl+str, new Callback() {
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
                        Intent intent = new Intent(XingShiSearchActivity.this,FirstNameActivity.class);
//                        intent.putExtra("bean",mXingShiBean);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", mXingShiBean);
                        intent.putExtras(bundle);
                        intent.setFlags(1);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
