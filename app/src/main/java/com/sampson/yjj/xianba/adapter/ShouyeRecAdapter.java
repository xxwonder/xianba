package com.sampson.yjj.xianba.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sampson.yjj.xianba.R;
import com.sampson.yjj.xianba.bean.ShouYeRecyleItem;

import java.util.List;

/**
 * Created by yjj on 2017/10/18.
 */

public class ShouyeRecAdapter extends RecyclerView.Adapter<ShouyeRecAdapter.ViewHolder> {
    private List<ShouYeRecyleItem> mList;
    public ShouyeRecAdapter(List<ShouYeRecyleItem> list) {
        super();
        mList = list;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View shouyeRecItem;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view)
        {
            super(view);
            shouyeRecItem = view;
            imageView = (ImageView)view.findViewById(R.id.shouye_recycle_item_img);
            textView = (TextView)view.findViewById(R.id.shouye_recycle_item_txt);
        }
    }


    @Override
    public ShouyeRecAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.shouye_recycle_item,viewGroup,false);
       final ViewHolder mHolder = new ViewHolder(view);
        mHolder.shouyeRecItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = mHolder.getAdapterPosition();
                switch (position){
                    case 0:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(v.getContext(),"位置"+position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return mHolder;
    }
    @Override
    public void onBindViewHolder(ShouyeRecAdapter.ViewHolder viewHolder, int i) {
        ShouYeRecyleItem shouYeRecyleItem = mList.get(i);
        viewHolder.imageView.setImageResource(shouYeRecyleItem.getId());
        viewHolder.textView.setText(shouYeRecyleItem.getName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
