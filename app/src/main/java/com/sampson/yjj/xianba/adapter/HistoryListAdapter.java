package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sampson.yjj.xianba.bean.HistoryDayBean;

import java.util.List;

/**
 * Created by yjj on 2017/10/31.
 */

public class HistoryListAdapter extends BaseAdapter {
    private Context context;
    private List<HistoryDayBean> mList;
    public HistoryListAdapter(List<HistoryDayBean> mList,Context context) {
        super();
        this.mList = mList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView =null;
        if(textView == null){
            textView = new TextView(context);
        }
        textView.setText((position+1)+". " + mList.get(position).getTitle());
//        textView.setHeight(80);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.offsetTopAndBottom(30);
        return textView;
    }
}
