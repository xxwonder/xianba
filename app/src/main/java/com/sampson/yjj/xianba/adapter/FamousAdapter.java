package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.FamousPeopleBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjj on 2017/11/1.
 */

public class FamousAdapter extends BaseAdapter {
    List<FamousPeopleBean> mList = new ArrayList<>();
    private Context context;
    public FamousAdapter(List<FamousPeopleBean> mList, Context context) {
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
        ViewHolder mHolder = null;
        if(convertView == null)
        {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.famous_list_item,parent,false);
            mHolder.famous_ren_txt = (TextView) convertView.findViewById(R.id.mingren_item_txt);
            mHolder.famous_yan_txt = (TextView) convertView.findViewById(R.id.mingyan_item_txt);
            convertView.setTag(mHolder);
        }else{
            mHolder = (ViewHolder)convertView.getTag();

        }
        mHolder.famous_ren_txt.setText(mList.get(position).getFamous_name());
        mHolder.famous_yan_txt.setText(mList.get(position).getFamous_saying());
        return convertView;
    }
    class ViewHolder {
        private TextView famous_ren_txt;
        private TextView famous_yan_txt;
    }
}
