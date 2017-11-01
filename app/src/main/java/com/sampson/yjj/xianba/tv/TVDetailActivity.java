package com.sampson.yjj.xianba.tv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.base.BaseActivity;

public class TVDetailActivity extends BaseActivity {
    private WebView tv3_webview;
    @Override
    public int getContentViewId() {
        return R.layout.activity_tvdetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    private void init(){
        tv3_webview =(WebView) findViewById(R.id.tv3_webview);
        WebSettings webSettings = tv3_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        tv3_webview.setWebViewClient(new WebViewClient());
        webSettings.setUseWideViewPort(true);//设置推荐使用窗口
        webSettings.setSupportZoom(true); // 支持缩放
        tv3_webview.loadUrl(getIntent().getStringExtra("url"));

    }
}
