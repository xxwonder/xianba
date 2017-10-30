package com.sampson.yjj.xianba.xiaohua;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.XiaoHuanRecAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;
import com.sampson.yjj.xianba.bean.QuTuBean;
import com.sampson.yjj.xianba.common.UrlContents;
import com.sampson.yjj.xianba.utils.HandleJokeToBean;
import com.sampson.yjj.xianba.utils.OkHttpUtil;
import com.sampson.yjj.xianba.utils.TimeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class XiaoHuaActivity extends BaseActivity{
    List<QuTuBean> list;
    int a ;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    list = new ArrayList();
                    for(int i=0;i<a;i++)
                    {
                        list.add(mQuTuList.get(i));
                        list.add(mXiaoHuaList.get(i));
                    }
                    initRecycleView();
                    break;
                case 1:
                    break;
            }
            return true;
        }
    });
    @Override
    public int getContentViewId() {
        return R.layout.activity_xiao_hua;
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView textView;
    private ImageView imageView;
//    List<XiaoHuaZuiXinBean> mXiaoHuaList;
    List<QuTuBean> mXiaoHuaList;
    List<QuTuBean> mQuTuList;
    private ImageView backImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgressDialog("提示","正在加载");
        initView();
//        setListener();
        recyclerView = (RecyclerView) findViewById(R.id.recycleview_xiaohua);
        getXiaoHuaListData();
        getQuTuData();
//        initRecycleView();
    }
    private void initView(){
//        swipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.recycle_refresh) ;
//        //调整SwipeRefreshLayout的位置
//        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));

        backImg = (ImageView) findViewById(R.id.back_img);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//    private void setListener(){
//        //swipeRefreshLayout刷新监听
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });
//    }
    private void initRecycleView(){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//        XiaoHuanRecAdapter adapter = new XiaoHuanRecAdapter(this,mXiaoHuaList,mQuTuList);
        XiaoHuanRecAdapter adapter = new XiaoHuanRecAdapter(this,list);
        recyclerView.setAdapter(adapter);
        hideProgressDialog();
    }


    /**
     *笑话文字信息和时间
     */
        public void getXiaoHuaListData() {
//        String str =  "http://api.avatardata.cn/Joke/QueryJokeByTime?key=2d7f299aad11407ba4bd2a69322adc96&page=2&rows=10&sort=asc&time=1418745237";
            String str =  "http://api.avatardata.cn/Joke/QueryJokeByTime?" +
                    "key=2d7f299aad11407ba4bd2a69322adc96&page=2&rows=10&sort=desc&time=" +
//                    TimeUtil.data(TimeUtil.getTodayDateTime());
                    (System.currentTimeMillis()/1000-604800);
            OkHttpUtil.sendOkHttpRequest(str
                //UrlContents.XIAOHUA_CHAXUN + "?key=" + UrlContents.XIAOHUAN_APPKEY + "&page=2&rows=10&sort=asc&time=1418745237"
                , new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mXiaoHuaList = HandleJokeToBean.handleJokeResponseList(responseText);
//                mList2=mXiaoHuaList;
                a = mXiaoHuaList.size();
                mHandler.sendEmptyMessage(1);
            }

        });
    }
    /**
     * 趣图部分
     */
    private void getQuTuData(){
        String qutuStr = UrlContents.QUTU_CHAXUN+"?key="+UrlContents.XIAOHUAN_APPKEY+
                "&page=2&rows=10&sort=desc&time=" +
                (System.currentTimeMillis()/1000-604800);
        OkHttpUtil.sendOkHttpRequest(qutuStr, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                mQuTuList = HandleJokeToBean.handleQuTuResponseList(responseText);
//                mList1 = mQuTuList;
                mHandler.sendEmptyMessage(0);
            }
        });
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
