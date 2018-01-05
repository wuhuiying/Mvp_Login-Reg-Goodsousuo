package com.jingdong.wode.twoweekexam.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingdong.wode.twoweekexam.R;
import com.jingdong.wode.twoweekexam.bean.SearchBean;

import java.util.List;

/**
 * Created by 李欣 on 2017/12/9.
 */

public class SearchRecyclerGVAdapter extends RecyclerView.Adapter<SearchRecyclerGVAdapter.ViewHolder> {
    Context context;
    List<SearchBean.DataBean> data;

    public SearchRecyclerGVAdapter(Context context, List<SearchBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchBean.DataBean dataBean = data.get(position);
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        //设置数据
        Glide.with(context).load(split[0]).into(holder.iv);
        holder.tv2.setText(dataBean.getPrice()+"");
        holder.tv1.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1,tv2;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.img);
            tv1 = itemView.findViewById(R.id.title);
            tv2 = itemView.findViewById(R.id.price);

        }
    }
}
