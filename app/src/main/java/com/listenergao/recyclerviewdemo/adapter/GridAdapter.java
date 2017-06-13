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
 * Created by gys on 2017/6/13 11:34.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    private List<String> mData;

    public GridAdapter(List<String> mData) {
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
        holder.iv.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;
        private TextView tv;

        private ViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.textView);
            iv = (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
