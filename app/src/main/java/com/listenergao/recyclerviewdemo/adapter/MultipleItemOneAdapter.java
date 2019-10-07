package com.listenergao.recyclerviewdemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.bean.MultipleItemBean;

import java.util.List;

public class MultipleItemOneAdapter extends RecyclerView.Adapter {

    private static final int TYPE_ONE = 1;
    private static final int TYPE_TWO = 2;

    private List<MultipleItemBean> mData;

    public MultipleItemOneAdapter(List<MultipleItemBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder holder;
        if (TYPE_ONE == viewType) {
            View view = inflater.inflate(R.layout.item_one_layout, parent, false);
            holder = new ViewHolderOne(view);
        } else {
            View view = inflater.inflate(R.layout.item_two_layout, parent, false);
            holder = new ViewHolderTwo(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MultipleItemBean itemBean = mData.get(position);
        if (holder instanceof ViewHolderOne) {
            ViewHolderOne one = (ViewHolderOne) holder;
            one.imageView.setImageResource(itemBean.resType);
            one.textView.setText(itemBean.content);
        } else {
            ViewHolderTwo two = (ViewHolderTwo) holder;
            two.imageView.setImageResource(itemBean.resType);
            two.imageView1.setImageResource(itemBean.resType1);
            two.textView.setText(itemBean.content);
            two.textView1.setText(itemBean.content1);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        MultipleItemBean multipleItemBean = mData.get(position);
        switch (multipleItemBean.displayType) {
            case TYPE_ONE:
                return TYPE_ONE;
            case TYPE_TWO:
                return TYPE_TWO;
        }
        return super.getItemViewType(position);
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        ViewHolderOne(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img);
            textView = itemView.findViewById(R.id.tv_content);
        }
    }

    static class ViewHolderTwo extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ImageView imageView1;
        private TextView textView;
        private TextView textView1;

        ViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_img);
            imageView1 = itemView.findViewById(R.id.iv_img_1);
            textView = itemView.findViewById(R.id.tv_content);
            textView1 = itemView.findViewById(R.id.tv_content_1);
        }
    }
}
