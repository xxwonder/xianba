package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.TVOneBean;
import com.sampson.yjj.xianba.bean.TVTwoBean;
import com.sampson.yjj.xianba.tv.TVDetailActivity;
import com.sampson.yjj.xianba.tv.TVSortActivity;

import java.util.List;

/**
 * Created by yjj on 2017/11/1.
 */

public class TV2RecycleAdapter extends RecyclerView.Adapter<TV2RecycleAdapter.ViewHolder>{
    private List<TVTwoBean> mList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv2_content;
        private TextView tv2_show;
        public ViewHolder(View itemView) {
            super(itemView);
            tv2_content = (TextView) itemView.findViewById(R.id.tv2_content);
            tv2_show = (TextView) itemView.findViewById(R.id.tv2_show);
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public TV2RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.tv2,viewGroup,false);
        TV2RecycleAdapter.ViewHolder mHolder = new TV2RecycleAdapter.ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(TV2RecycleAdapter.ViewHolder viewHolder, final int i) {

        viewHolder.tv2_content.setText(mList.get(i).getChannelName());
        viewHolder.tv2_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TVDetailActivity.class);
                intent.putExtra("url",mList.get(i).getUrl());
                context.startActivity(intent);
            }
        });
    }

    public TV2RecycleAdapter(List<TVTwoBean> mList,Context context) {
        super();
        this.context = context;
        this.mList = mList;
    }
}
