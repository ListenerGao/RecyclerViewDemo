package com.listenergao.recyclerviewdemo.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.GridAdapter;
import com.listenergao.recyclerviewdemo.itemDecoration.GridItemDecoration;
import com.listenergao.recyclerviewdemo.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gys on 2017/6/13 16:08.
 * 网格布局
 */
public class GridLayoutActivity extends BaseActivity {

    private static final String TAG = "GridLayoutActivity";
    private RecyclerView mRecyclerView;
    private List<String> mData;

    @Override
    protected void initView() {
        setTitle("RecyclerView网格布局");
        setBackArrow();
        setContentLayout(R.layout.activity_grid_layout);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 65; i++) {
            mData.add("item " + i);
        }

        initRecyclerView(mData);
    }

    private void initRecyclerView(List<String> data) {
        GridAdapter adapter = new GridAdapter(data);
        /**
         * Context context,.
         * int spanCount,   每一行或列的个数
         * int orientation, 布局方向
         * boolean reverseLayout   是否反转 true 反转，false 不反转
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5, GridLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        GridItemDecoration itemDecoration = new GridItemDecoration(DisplayUtils.dp2px(5));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(adapter);

        Log.d(TAG, LinearLayoutManager.HORIZONTAL + "");
        Log.d(TAG, LinearLayoutManager.VERTICAL + "");
        Log.d(TAG, GridLayoutManager.HORIZONTAL + "");
        Log.d(TAG, GridLayoutManager.VERTICAL + "");
        Log.d(TAG, StaggeredGridLayoutManager.HORIZONTAL + "");
        Log.d(TAG, StaggeredGridLayoutManager.VERTICAL + "");
    }
}
