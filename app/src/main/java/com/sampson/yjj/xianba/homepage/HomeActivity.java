package com.sampson.yjj.xianba.homepage;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.MyHomeFragmentAdapter;
import com.sampson.yjj.xianba.base.BaseActivity;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity {
    private LinearLayout homeShouye;
    private LinearLayout homeFenlei;
    private LinearLayout homeWo;
    private ViewPager homeVp;

    private ArrayList<Fragment> fragments;

    private int currIndex;
    private static int bmpW;//横线图片宽度
    private static int offset;//图片移动的偏移量
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewPager();
        InitBottomLinearLayout();
    }

    public void initView(){
        homeShouye = (LinearLayout)findViewById(R.id.home_shouye);
        homeFenlei = (LinearLayout)findViewById(R.id.home_fenlei);
        homeWo = (LinearLayout)findViewById(R.id.home_wo);
        homeVp = (ViewPager)findViewById(R.id.home_viewpager);
    }
    private void initViewPager(){

        fragments = new ArrayList<Fragment>();
        Fragment fragmentShouye = new ShouyeFragment();
        Fragment fragmentFenlei = new FenleiFragment();
        Fragment fragmentWo = new WoFragment();
        fragments.add(fragmentShouye);
        fragments.add(fragmentFenlei);
        fragments.add(fragmentWo);
        homeVp.setAdapter(new MyHomeFragmentAdapter(getSupportFragmentManager(), fragments));
        homeVp.setCurrentItem(0);
        homeVp.setOnPageChangeListener(new myOnPageChangeListener());
    }

    public class myOnPageChangeListener implements ViewPager.OnPageChangeListener {
        private int one = offset*2 +bmpW;//两个相邻页面的偏移量
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }
        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
        }
        @Override
        public void onPageSelected(int arg0) {
            // TODO Auto-generated method stub
            Animation animation = new TranslateAnimation(currIndex*one,arg0*one,0,0);//平移动画
            currIndex = arg0;
            animation.setFillAfter(true);//动画终止时停留在最后一帧，不然会回到没有执行前的状态
            animation.setDuration(200);//动画持续时间0.2秒

        }
    }
    private void InitBottomLinearLayout(){

        homeShouye.setOnClickListener(new LinearListener(0));
        homeFenlei.setOnClickListener(new LinearListener(1));
        homeWo.setOnClickListener(new LinearListener(2));
    }
    //内部类 重写TextView点击事件
    public class LinearListener implements View.OnClickListener{
        private int index = 0;
        public LinearListener(int i){
            index = i;
        }
        @Override
        public void onClick(View v){
            homeVp.setCurrentItem(index);
        }
    }
    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }
}
