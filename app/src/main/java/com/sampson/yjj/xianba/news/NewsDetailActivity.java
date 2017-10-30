package com.sampson.yjj.xianba.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sampson.yjj.xianba.R;

public class NewsDetailActivity extends AppCompatActivity {

    private WebView news_webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        init();
    }
    private void init(){
        String url = getIntent().getStringExtra("url");
        news_webview = (WebView)findViewById(R.id.news_webview);
        news_webview.setWebChromeClient(new WebChromeClient());
        news_webview.setWebViewClient(new WebViewClient());
        news_webview.loadUrl(url);
    }
}
