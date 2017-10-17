package com.sampson.yjj.xianba.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sampson.yjj.xianba.R;

/**
 * Created by yjj on 2017/10/17.
 */

public class ShouyeFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye_home,container,false);
        return view;
    }
}
