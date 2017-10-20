package com.sampson.yjj.xianba.homepage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.adapter.LunBoViewPagerAdapter;
import com.sampson.yjj.xianba.adapter.ShouyeRecAdapter;
import com.sampson.yjj.xianba.bean.ShouYeRecyleItem;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjj on 2017/10/17.
 */

public class ShouyeFragment  extends Fragment {
    private static final String LOG_TAG = "MainActivity";
    private ImageHandler handler = new ImageHandler(new WeakReference<ShouyeFragment>(this));

    private RecyclerView recyclerView;
    private List<ShouYeRecyleItem> mList = new ArrayList<ShouYeRecyleItem>();
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shouye_home, container, false);
        init(inflater,container,view);
        return view;
    }

    private void init(LayoutInflater inflater, ViewGroup container, View view) {
//        View view = inflater.inflate(R.layout.fragment_shouye_home,container,false);
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        ImageView view1 = (ImageView) inflater.inflate(R.layout.item, null);
        ImageView view2 = (ImageView) inflater.inflate(R.layout.item, null);
        ImageView view3 = (ImageView) inflater.inflate(R.layout.item, null);
        view1.setImageResource(R.drawable.vp_one);
        view2.setImageResource(R.drawable.vp_two);
        view3.setImageResource(R.drawable.vp_threwe);
        ArrayList<ImageView> views = new ArrayList<ImageView>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        viewPager.setAdapter(new LunBoViewPagerAdapter(views));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //配合Adapter的currentItem字段进行设置。
            @Override
            public void onPageSelected(int arg0) {
                handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            //覆写该方法实现轮播效果的暂停和恢复
            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch (arg0) {
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                        break;
                    default:
                        break;
                }
            }
        });
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2);//默认在中间，使用户看不到边界
        //开始轮播效果
        handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);

        /**
         * 主题recycleview
         */
        initData();
        recyclerView =(RecyclerView) view.findViewById(R.id.home_frg_recycleview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager
                (2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        ShouyeRecAdapter adapter = new ShouyeRecAdapter(mList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化mList数据
     */
    private void initData(){
        mList.clear();
        //单例
        ShouYeRecyleItem shouYeRecyleItem1 = new ShouYeRecyleItem("农业新闻",R.drawable.shouye_nongye);
        mList.add(shouYeRecyleItem1);
        ShouYeRecyleItem shouYeRecyleItem2 = new ShouYeRecyleItem("天气状况",R.drawable.shouye_tianqi);
        mList.add(shouYeRecyleItem2);
        ShouYeRecyleItem shouYeRecyleItem3 = new ShouYeRecyleItem("健康问答",R.drawable.shouye_jiankang);
        mList.add(shouYeRecyleItem3);
        ShouYeRecyleItem shouYeRecyleItem4 = new ShouYeRecyleItem("历史上的今天曾经...",R.drawable.shouye_lishi);
        mList.add(shouYeRecyleItem4);
        ShouYeRecyleItem shouYeRecyleItem5 = new ShouYeRecyleItem("有人说过...",R.drawable.shouye_mingren);
        mList.add(shouYeRecyleItem5);
        ShouYeRecyleItem shouYeRecyleItem6 = new ShouYeRecyleItem("姓氏起源",R.drawable.shouye_baijiaxing);
        mList.add(shouYeRecyleItem6);
        ShouYeRecyleItem shouYeRecyleItem7 = new ShouYeRecyleItem("幽默笑话",R.drawable.shouye_xiaohua);
        mList.add(shouYeRecyleItem7);
    }


    private static  class ImageHandler extends Handler {

        /**
         * 请求更新显示的View。
         */
        protected static final int MSG_UPDATE_IMAGE = 1;
        /**
         * 请求暂停轮播。
         */
        protected static final int MSG_KEEP_SILENT = 2;
        /**
         * 请求恢复轮播。
         */
        protected static final int MSG_BREAK_SILENT = 3;
        /**
         * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
         * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
         * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
         */
        protected static final int MSG_PAGE_CHANGED = 4;

        //轮播间隔时间
        protected static final long MSG_DELAY = 3000;

        //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
        private WeakReference<ShouyeFragment> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<ShouyeFragment> wk) {
            weakReference = wk;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            Log.d(LOG_TAG, "receive message " + msg.what);
//            MainActivity activity = weakReference.get();
            ShouyeFragment fragment = weakReference.get();

            if (fragment == null) {
                //Activity已经回收，无需再处理UI了
                return;
            }
            //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
            if (fragment.handler.hasMessages(MSG_UPDATE_IMAGE)) {

                fragment.handler.removeMessages(MSG_UPDATE_IMAGE);
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    currentItem++;
                    fragment.viewPager.setCurrentItem(currentItem);
                    //准备下次播放
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    //只要不发送消息就暂停了
                    break;
                case MSG_BREAK_SILENT:
                    fragment.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    //记录当前的页号，避免播放的时候页面显示不正确。
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }

}


