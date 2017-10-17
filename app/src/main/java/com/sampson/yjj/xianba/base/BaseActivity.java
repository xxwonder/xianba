package com.sampson.yjj.xianba.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Window;


import com.sampson.yjj.xianba.utils.LogUtils;

/**
 * Created by yjj on 2017/10/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentViewId());
        LogUtils.e("--->onCreate");
    }
    public abstract int getContentViewId();

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.e("--->onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.e("--->onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.e("--->onRestart");
    }
    @Override
    protected void onPause(){
        super.onPause();
        LogUtils.e("--->onpause");
    }
    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.e("--->onResume");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.e("--->onDestory");
    }
}
