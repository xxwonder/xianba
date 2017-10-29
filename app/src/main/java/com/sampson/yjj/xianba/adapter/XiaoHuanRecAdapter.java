package com.sampson.yjj.xianba.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.QuTuBean;
import com.sampson.yjj.xianba.bean.XiaoHuaZuiXinBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjj on 2017/10/20.
 */

public class XiaoHuanRecAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    List<XiaoHuaZuiXinBean>mXiaoHuaList = new ArrayList<>();
    List<QuTuBean>mQuTyList = new ArrayList<>();
    Context context;
    //自定义监听事件
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);
        void onItemLongClick(View view);
    }
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public XiaoHuanRecAdapter(Context context,List<XiaoHuaZuiXinBean>mXiaoHuaList, List<QuTuBean>mQuTyList) {
        super();
        this.context = context;
        this.mQuTyList = mQuTyList;
        this.mXiaoHuaList = mXiaoHuaList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

        if(i%2==0)
        {
//            if(viewHolder instanceof ViewHolder1){
                Glide.with(context).load(mQuTyList.get(i).getUrl()).into(((ViewHolder1) viewHolder).imageView);
                ((ViewHolder1) viewHolder).imageName.setText(mQuTyList.get(i).getContent());
//            }
        }else{
//            if(viewHolder instanceof ViewHolder2){
                ((ViewHolder2) viewHolder).content.setText(mXiaoHuaList.get(i).getContent());
                ((ViewHolder2) viewHolder).time.setText(mXiaoHuaList.get(i).getUpdatetime());
//            }
        }
//        if(viewHolder instanceof ViewHolder1){
//            Glide.with(context).load(mQuTyList.get(i).getUrl()).into(((ViewHolder1) viewHolder).imageView);
//            ((ViewHolder1) viewHolder).imageName.setText(mQuTyList.get(i).getContent());
//        }else if(viewHolder instanceof ViewHolder2){
//            ((ViewHolder2) viewHolder).content.setText(mXiaoHuaList.get(i).getContent());
//            ((ViewHolder2) viewHolder).time.setText(mXiaoHuaList.get(i).getUpdatetime());
//        }
    }

    @Override
    public int getItemCount() {
        return mXiaoHuaList.size()+mQuTyList.size();
//        return 20;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i)
        {
            case 0:
                View view = LayoutInflater.from(context
                ).inflate(R.layout.qutu, viewGroup,
                        false);//这个布局就是一个imageview用来显示图片
                ViewHolder1 holder1 = new ViewHolder1(view);

                //给布局设置点击和长点击监听
//                view.setOnClickListener(this);

                return holder1;
            case 1:
                ViewHolder2 holder2=new ViewHolder2(LayoutInflater.from(
                        context).inflate(R.layout.xiaohua_item, viewGroup,
                        false));//这个布局就是一个textview用来显示页数
                return holder2;

        }
        return null;
    }
    class ViewHolder1 extends RecyclerView.ViewHolder{
        private TextView imageName;
        private ImageView imageView;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageName = (TextView) itemView.findViewById(R.id.qutu_name);
            imageView = (ImageView) itemView.findViewById(R.id.qutu_img);
        }
    }
    class ViewHolder2 extends RecyclerView.ViewHolder{
        private TextView content;
        private TextView time;
        public ViewHolder2(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.xiaohua_txt);
            time = (TextView) itemView.findViewById(R.id.xiaohua_time);

        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position%2==0)
            return 0;
        else
            return 1;
    }
}
