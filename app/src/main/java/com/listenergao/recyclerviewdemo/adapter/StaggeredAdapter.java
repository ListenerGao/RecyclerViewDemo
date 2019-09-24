package com.listenergao.recyclerviewdemo.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.listenergao.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gys on 2017/6/15 15:48.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private List<String> mData;
    //用于存放动态设置每个Item高度的集合
    private List<Integer> mHights;

    public StaggeredAdapter(List<String> data) {
        this.mData = data;
        mHights = new ArrayList<>();
        for (int i = 0; i < mData.size(); i++) {
            mHights.add(((int) (160 + Math.random() * 400)));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staggered, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        params.height = mHights.get(position);
        Log.d("TAG", "item高度 = " + mHights.get(position));
        holder.itemView.setLayoutParams(params);

        holder.tv.setText(mData.get(position));
//        holder.iv.setImageResource(R.mipmap.ic_launcher_round);
    }

    @Override
    public int getItemCount() {
        return mData.size() > 0 ? mData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
//        ImageView iv;

        ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.textView);
//            iv = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    /**
     * 添加数据
     *
     * @param position 添加到数据中的位置
     */
    public void addData(int position) {
        mData.add(position, "这是添加的数据");
        //注意，这里更新数据集不是用adapter.notifyDataSetChanged()而是notifyItemInserted(position)与notifyItemRemoved(position)
        // 否则没有动画效果。
        notifyItemInserted(position);
    }

    /**
     * 删除数据
     *
     * @param position
     */
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

}
