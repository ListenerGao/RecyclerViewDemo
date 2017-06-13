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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //绑定数据
        String s = mData.get(position);
        holder.iv.setImageResource(R.mipmap.ic_launcher_round);
        holder.tv.setText(s);

        //如果设置了回调，则设置Item点击事件
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }

        //如果设置了回调，则设置Item点击事件
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemLongClickListener.OnItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        //Item条目数
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        private ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textView);
            iv = (ImageView) view.findViewById(R.id.imageView);

        }
    }

    /***************************Item点击事件**********************/
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    /***************************Item长按事件**********************/
    private OnItemLongClickListener mOnItemLongClickListener;

    public interface OnItemLongClickListener {
        void OnItemLongClick(View view, int position);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }
}
