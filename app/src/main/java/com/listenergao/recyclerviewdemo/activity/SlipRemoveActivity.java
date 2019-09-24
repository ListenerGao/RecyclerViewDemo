package com.listenergao.recyclerviewdemo.activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.SlipRemoveAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlipRemoveActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;

    @Override
    protected void initView() {
        setTitle("RecyclerView侧滑删除");
        setBackArrow();
        setContentLayout(R.layout.activity_slip_remove);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("Item " + i);
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        SlipRemoveAdapter adapter = new SlipRemoveAdapter(mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(adapter);
    }

}
