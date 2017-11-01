package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.NewsTopBean;
import com.sampson.yjj.xianba.bean.TVOneBean;
import com.sampson.yjj.xianba.tv.TVActivity;
import com.sampson.yjj.xianba.tv.TVSortActivity;

import java.util.List;

/**
 * Created by yjj on 2017/11/1.
 */

public class TV1RecycleAdapter extends RecyclerView.Adapter<TV1RecycleAdapter.ViewHolder> {
    private List<TVOneBean> mList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv1_content;
        public ViewHolder(View itemView) {
            super(itemView);
            tv1_content = (TextView) itemView.findViewById(R.id.tv1_content);
        }
    }
    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.tv1,viewGroup,false);
        TV1RecycleAdapter.ViewHolder mHolder = new TV1RecycleAdapter.ViewHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {

        viewHolder.tv1_content.setText(mList.get(i).getName());
        viewHolder.tv1_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,TVSortActivity.class);
                intent.setFlags(0);
                intent.putExtra("id",(i+1));
                context.startActivity(intent);
            }
        });
    }

    public TV1RecycleAdapter(List<TVOneBean> mList,Context context) {
        super();
        this.context = context;
        this.mList = mList;
    }
}
