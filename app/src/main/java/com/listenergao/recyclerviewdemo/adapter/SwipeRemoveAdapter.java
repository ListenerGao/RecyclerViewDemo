package com.listenergao.recyclerviewdemo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.listener.OnSwipeListener;

import java.util.List;

/**
 * Created by gys on 2017/6/26 15:59.
 * 侧滑删除
 */

public class SwipeRemoveAdapter extends RecyclerView.Adapter<SwipeRemoveAdapter.ViewHolder> {

    private List<String> mData;
    private OnSwipeListener mSwipeListener;

    public SwipeRemoveAdapter(List<String> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_swip_remove, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tv.setText(mData.get(position));
        holder.iv.setImageResource(R.mipmap.ic_launcher_round);


        //删除
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSwipeListener != null) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mSwipeListener.onDelete(holder.getAdapterPosition());
                }
            }
        });

        //置顶
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwipeListener.onTop(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size() > 0 ? mData.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        Button btnTop;
        Button btnUnRead;
        Button btnDelete;

        private ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.imageView);
            tv = (TextView) itemView.findViewById(R.id.textView);
            btnTop = itemView.findViewById(R.id.btnTop);
            btnUnRead = itemView.findViewById(R.id.btnUnRead);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    /**
     * 和Activity通信接口
     */
    public void addSwipeListener(OnSwipeListener listener) {
        this.mSwipeListener = listener;
    }
}
