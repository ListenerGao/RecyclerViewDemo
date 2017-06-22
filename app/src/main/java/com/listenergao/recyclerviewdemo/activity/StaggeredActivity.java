package com.listenergao.recyclerviewdemo.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.StaggeredAdapter;
import com.listenergao.recyclerviewdemo.itemDecoration.SpaceItemDecoration;
import com.listenergao.recyclerviewdemo.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gys on 2017/6/15 15:44.
 * 瀑布流布局
 */
public class StaggeredActivity extends BaseActivity implements Toolbar.OnMenuItemClickListener {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private StaggeredAdapter mAdapter;

    @Override
    protected void initView() {
        setTitle("RecyclerView瀑布流");
        setBackArrow();
        setContentLayout(R.layout.activity_staggered);

        setToolbarMenuOnClickListener(this);

        mRecyclerView = ((RecyclerView) findViewById(R.id.recycler_view));

    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            mData.add("item " + i);
        }

        initRecyclerView(mData);
    }

    private void initRecyclerView(List<String> data) {
        mAdapter = new StaggeredAdapter(data);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
//        StaggeredItemDecoration itemDecoration = new StaggeredItemDecoration(DisplayUtils.dp2px(5));
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(DisplayUtils.dp2px(5));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //设置Item动画，使用RecyclerView提供的
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_and_remove,menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                mAdapter.addData(1);
                break;

            case R.id.remove:
                mAdapter.removeData(5);
                break;
        }
        return false;
    }
}
