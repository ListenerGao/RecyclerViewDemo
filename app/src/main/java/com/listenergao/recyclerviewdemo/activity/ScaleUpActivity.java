package com.listenergao.recyclerviewdemo.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaeger.library.StatusBarUtil;
import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.CommonAdapter;
import com.listenergao.recyclerviewdemo.animator.AnimatorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 19/09/29
 * 点击Fab上滑到顶部
 *
 * @author listenergao
 */
public class ScaleUpActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;


    private List<String> mData;

    private boolean isInitializeFAB = false;


    @Override
    protected void initView() {
        setTitle("上滑到顶部");
        setBackArrow();
        setContentLayout(R.layout.activity_scale_up);

        mRecyclerView = findViewById(R.id.recycler_view);
        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add("item " + i);
        }


        initRecyclerView(mData);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!isInitializeFAB) {
            isInitializeFAB = true;
            hideFAB();
        }
    }

    private void hideFAB() {
        mFab.postDelayed(new Runnable() {
            @Override
            public void run() {
                AnimatorUtil.scaleHide(mFab, new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {
                    }
                    @Override
                    public void onAnimationEnd(View view) {
                        view.setVisibility(View.GONE);
                    }
                    @Override
                    public void onAnimationCancel(View view) {
                    }
                });
            }
        }, 500);
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


    @Override
    public void onClick(View view) {

    }
}
