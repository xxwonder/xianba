package com.sampson.yjj.xianba.homepage;

import android.support.annotation.NonNull;

import com.sampson.yjj.xianba.BasePresenter;
import com.sampson.yjj.xianba.BaseView;

/**
 * Created by yjj on 2017/10/17.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {

        boolean isActive();

        void showFilteringPopUpMenu();
    }

    interface Presenter extends BasePresenter {


    }
}
