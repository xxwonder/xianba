package com.sampson.yjj.xianba.news;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.NewsRecycleAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.NewsTopBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class NewsActivity extends BaseActivity {
    @Override
    public int getContentViewId() {
        return R.layout.activity_news;
    }

    String newsUrl = UrlContents.NEWS_TOUTIAO+UrlContents.NEWS_APPKEY+"&type=top";
    private ImageView backImg;
    private List<NewsTopBean> mNewsList;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog("","正在加载");
        initView();
        initSwiprefreshLayout();
        getNewsData(newsUrl);
    }
    private void initView(){
        backImg = (ImageView)findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView = (RecyclerView)findViewById(R.id.recycleview_news);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiprefresh_news);
    }
    /**
     * 获取新闻信息
     */

    private void getNewsData(String url){
        OkHttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mNewsList = HandleJokeToBean.jsonTOList(responseText,NewsTopBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initRecycleAdapter();
                        hideProgressDialog();
                    }
                });
            }
        });
    }

    /**
     * 初始化swiprefreshlayout
     */
    private void initSwiprefreshLayout(){
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                refreshNews();
                initRecycleAdapter();
                hideProgressDialog();
            }
        });
    }
    /**
     * 初始化recycleview
     */
    private void initRecycleAdapter(){
        NewsRecycleAdapter mAdapter = new NewsRecycleAdapter(this,mNewsList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(NewsActivity.this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

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
}
