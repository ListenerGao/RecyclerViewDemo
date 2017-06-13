package com.listenergao.recyclerviewdemo.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommonRecyclerViewActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;


    @Override
    protected void initView() {

        setTitle("RecyclerView线性布局");
        setBackArrow();
        setContentLayout(R.layout.activity_common_recycler_view);

        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("item " + i);
        }

        initRecyclerView(mData);
    }

    private void initRecyclerView(List<String> data) {
        CommonAdapter adapter = new CommonAdapter(data);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //添加Item分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), linearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(adapter);

        //设置Item点击事件
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplication(), "onItemClick--当前位置为=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置Item长按事件
        adapter.setOnItemLongClickListener(new CommonAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(getApplication(), "OnItemLongClick--当前位置为=" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
