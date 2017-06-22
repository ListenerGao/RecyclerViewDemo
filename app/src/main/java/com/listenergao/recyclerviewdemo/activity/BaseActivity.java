package com.listenergao.recyclerviewdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.listenergao.recyclerviewdemo.R;
/**
 * Created by gys on 2017/6/13 10:07.
 * Activity基类
 */
public class BaseActivity extends AppCompatActivity {

    private RelativeLayout mContent;
    private static final String TAG = "BaseActivity";
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        initToolbar();
        initView();
        initData();
    }

    /**
     * 初始化布局 子类使用
     */
    protected void initView() {
    }

    /**
     * 初始化数据 子类使用
     */
    protected void initData() {
    }

    /**
     * 初始化ToolBar
     */
    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        mContent = (RelativeLayout) findViewById(R.id.content);
    }

    /**
     * 设置Toolbar下面的内容区域
     *
     * @param layoutId 布局ID
     */
    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.addView(contentView, params);
    }

    /**
     * 设置头部的标题
     *
     * @param msg 标题内容
     */
    protected void setTitle(String msg) {
        if (mToolbar != null) {
            mToolbar.setTitle(msg);
        }
    }

    /**
     * 头部返回按钮点击事件
     */
    protected void setBackArrow() {
//        Drawable backArrow = getResources().getDrawable(R.drawable.img_back);
//        //为toolbar设置左侧图标
//        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置图标点击事件
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置Menu的点击事件
     *
     * @param listener menu点击事件
     */
    protected void setToolbarMenuOnClickListener(Toolbar.OnMenuItemClickListener listener) {
        mToolbar.setOnMenuItemClickListener(listener);
    }
}
