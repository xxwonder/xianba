package com.sampson.yjj.xianba.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by yjj on 2017/10/17.
 */

public class MyHomeFragmentAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mHomeFrgList;

    public MyHomeFragmentAdapter(FragmentManager fm,ArrayList<Fragment> list){
        super(fm);
        this.mHomeFrgList = list;
    }
    @Override
    public Fragment getItem(int i) {
        return mHomeFrgList.get(i);
    }

    @Override
    public int getCount() {
        return mHomeFrgList.size();
    }
}
