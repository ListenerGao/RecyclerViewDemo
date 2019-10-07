package com.listenergao.recyclerviewdemo.activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.MultipleItemOneAdapter;
import com.listenergao.recyclerviewdemo.bean.MultipleItemBean;
import com.listenergao.recyclerviewdemo.itemDecoration.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MultipleItemOneActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<MultipleItemBean> mData;

    @Override
    protected void initView() {
        setTitle("Multiple Item one");
        setBackArrow();
        setContentLayout(R.layout.activity_multiple_one);

        mRecyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                mData.add(new MultipleItemBean(R.mipmap.ic_launcher_round, "item " + i, 1));
            } else {
                mData.add(new MultipleItemBean(R.mipmap.ic_launcher_round, R.mipmap.ic_launcher, "item " + i, "Multiple Item", 2));
            }
        }

        initRecyclerView();


    }

    private void initRecyclerView() {
        MultipleItemOneAdapter adapter = new MultipleItemOneAdapter(mData);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_bg2));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(adapter);
    }
}
