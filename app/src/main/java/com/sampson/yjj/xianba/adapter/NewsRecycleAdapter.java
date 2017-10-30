package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.NewsTopBean;
import com.sampson.yjj.xianba.news.NewsActivity;
import com.sampson.yjj.xianba.news.NewsDetailActivity;

import java.util.List;

/**
 * Created by yjj on 2017/10/30.
 */

public class NewsRecycleAdapter extends RecyclerView.Adapter<NewsRecycleAdapter.ViewHolder>{
    private List<NewsTopBean> mList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View newsView;
        private ImageView thumbImg;
        private TextView titleTxt;
        private TextView authorTxt;
        private TextView timeTxt;
        public ViewHolder(View itemView) {
            super(itemView);
            newsView = itemView;
            thumbImg = (ImageView)itemView.findViewById(R.id.newsthumb_img);
            titleTxt = (TextView)itemView.findViewById(R.id.newstitle_txt);
            authorTxt = (TextView)itemView.findViewById(R.id.newsauthor_txt);
            timeTxt = (TextView)itemView.findViewById(R.id.newstime_txt);
        }
    }
    public NewsRecycleAdapter(Context context, List<NewsTopBean> mList) {
        super();
        this.mList = mList;
        this.context = context;
    }

    @Override
    public NewsRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.newsrecycle_item,viewGroup,false);
        ViewHolder mHolder = new ViewHolder(view);

        return mHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecycleAdapter.ViewHolder viewHolder, final int i) {
        NewsTopBean mNesBean = mList.get(i);
        viewHolder.titleTxt.setText(mNesBean.getTitle());
        viewHolder.authorTxt.setText(mNesBean.getAuthor_name());
        viewHolder.timeTxt.setText(mNesBean.getDate());
        Glide.with(context).load(mNesBean.getThumbnail_pic_s()).
                placeholder(R.drawable.app_icon).into(viewHolder.thumbImg);
        viewHolder.newsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewsDetailActivity.class);
                intent.putExtra("url",mList.get(i).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
