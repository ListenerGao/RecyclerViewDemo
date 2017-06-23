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
 * Created by gys on 2017/6/22 18:57.
 */

public class DragAndSlideAdapter extends RecyclerView.Adapter<DragAndSlideAdapter.ViewHolder> {

    private List<String> mData;

    public DragAndSlideAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.iv.setImageResource(R.mipmap.ic_launcher_round);
        holder.tv.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size() > 0 ? mData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        private ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView);
            tv = (TextView) itemView.findViewById(R.id.textView);
        }
    }
}
