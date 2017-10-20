package com.sampson.yjj.xianba.xiaohua;

import com.sampson.yjj.xianba.BasePresenter;
import com.sampson.yjj.xianba.BaseView;

/**
 * Created by yjj on 2017/10/19.
 */

public interface XiaoHuaContract {
    interface View extends BaseView<Presenter> {
        void initView();
        void showMessage();
    }

    interface Presenter extends BasePresenter {
        void getResponseData();
    }
}
