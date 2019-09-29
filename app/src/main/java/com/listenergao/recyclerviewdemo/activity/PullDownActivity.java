package com.listenergao.recyclerviewdemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jaeger.library.StatusBarUtil;
import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class PullDownActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;


    private List<String> mData;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_down);

        initToolbar();
        initView();
        initData();

    }

    private void initToolbar() {

        //设置状态栏为全透明。
        StatusBarUtil.setTransparent(this);

        mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
//            supportActionBar.setTitle("仿网易云音乐歌单");
            supportActionBar.setDisplayShowTitleEnabled(false);
            supportActionBar.setDisplayHomeAsUpEnabled(true);

            mToolbar.setTitle("下拉放大图片");
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    private void initView() {
        mAppBarLayout = findViewById(R.id.app_bar);
        mCollapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mRecyclerView = findViewById(R.id.recycler_view);

        /**
         * 当AppbarLayout 的偏移发生改变的时候回调，也就是子View滑动
         */
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                Log.d("gys", "i = " + i);
            }
        });

    }


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
        ///设置Item分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider_bg2);
        itemDecoration.setDrawable(drawable);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setNestedScrollingEnabled(false);
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
