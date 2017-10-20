package com.sampson.yjj.xianba.homepage;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
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

    private ImageView home_shouye_img;
    private ImageView home_fenlei_img;
    private ImageView home_wo_img;
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

        home_shouye_img =(ImageView) findViewById(R.id.home_shouye_img);
        home_fenlei_img = (ImageView) findViewById(R.id.home_fenlei_img);
        home_wo_img =(ImageView) findViewById(R.id.home_wo_img);
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
//
            switch (arg0){
                case 0:
                    home_shouye_img.setImageResource(R.drawable.home_shouye2);
                    home_fenlei_img.setImageResource(R.drawable.home_fenlei1);
                    home_wo_img.setImageResource(R.drawable.home_wo1);
                    break;
                case 1:
                    home_shouye_img.setImageResource(R.drawable.home_shouye1);
                    home_fenlei_img.setImageResource(R.drawable.home_fenlei2);
                    home_wo_img.setImageResource(R.drawable.home_wo1);
                    break;
                case 2:
                    home_shouye_img.setImageResource(R.drawable.home_shouye1);
                    home_fenlei_img.setImageResource(R.drawable.home_fenlei1);
                    home_wo_img.setImageResource(R.drawable.home_wo2);
                    break;
            }
        }
    }
    private void InitBottomLinearLayout(){
        homeFenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeVp.setCurrentItem(1);
                home_shouye_img.setImageResource(R.drawable.home_shouye1);
                home_fenlei_img.setImageResource(R.drawable.home_fenlei2);
                home_wo_img.setImageResource(R.drawable.home_wo1);
            }
        });
        homeShouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeVp.setCurrentItem(0);
                home_shouye_img.setImageResource(R.drawable.home_shouye2);
                home_fenlei_img.setImageResource(R.drawable.home_fenlei1);
                home_wo_img.setImageResource(R.drawable.home_wo1);
            }
        });

        homeWo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeVp.setCurrentItem(2);
                home_shouye_img.setImageResource(R.drawable.home_shouye1);
                home_fenlei_img.setImageResource(R.drawable.home_fenlei1);
                home_wo_img.setImageResource(R.drawable.home_wo2);
            }
        });

    }

    @Override
    public int getContentViewId() {
        return R.layout.activity_home;
    }
}
