package com.listenergao.recyclerviewdemo.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.listenergao.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by gys on 2017/6/10 18:22.
 */

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {

    private List<String> mData;

    public CommonAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //绑定数据
        String s = mData.get(position);
        holder.iv.setImageResource(R.mipmap.ic_launcher_round);
        holder.tv.setText(s);
    }

    @Override
    public int getItemCount() {
        //Item条目数
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textView);
            iv = (ImageView) view.findViewById(R.id.imageView);

        }
    }
}
