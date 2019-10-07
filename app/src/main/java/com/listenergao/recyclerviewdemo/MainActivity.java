package com.listenergao.recyclerviewdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.listenergao.recyclerviewdemo.activity.BaseActivity;
import com.listenergao.recyclerviewdemo.activity.CommonRecyclerViewActivity;
import com.listenergao.recyclerviewdemo.activity.CustomBehaviorActivity;
import com.listenergao.recyclerviewdemo.activity.DragAndSlideActivity;
import com.listenergao.recyclerviewdemo.activity.GridLayoutActivity;
import com.listenergao.recyclerviewdemo.activity.MultipleItemActivity;
import com.listenergao.recyclerviewdemo.activity.PullDownActivity;
import com.listenergao.recyclerviewdemo.activity.SlipRemoveActivity;
import com.listenergao.recyclerviewdemo.activity.SongListActivity;
import com.listenergao.recyclerviewdemo.activity.StaggeredActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mCommonRecyclerViewActivity;
    private Button mGridLayoutActivity;
    private Button mStaggeredActivity;
    private Button mDragAndSlideActivity;
    private Button mSlipRemoveActivity;


    @Override
    protected void initView() {

        setTitle("RecyclerViewDemo");
        setContentLayout(R.layout.activity_main);

        mCommonRecyclerViewActivity = ((Button) findViewById(R.id.button1));
        mGridLayoutActivity = ((Button) findViewById(R.id.button2));
        mStaggeredActivity = ((Button) findViewById(R.id.button3));
        mDragAndSlideActivity = ((Button) findViewById(R.id.button4));
        mSlipRemoveActivity = ((Button) findViewById(R.id.button5));
        mCommonRecyclerViewActivity.setOnClickListener(this);
        mGridLayoutActivity.setOnClickListener(this);
        mStaggeredActivity.setOnClickListener(this);
        mDragAndSlideActivity.setOnClickListener(this);
        mSlipRemoveActivity.setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(CommonRecyclerViewActivity.class);
                break;
            case R.id.button2:
                startActivity(GridLayoutActivity.class);
                break;
            case R.id.button3:
                startActivity(StaggeredActivity.class);
                break;
            case R.id.button4:
                startActivity(DragAndSlideActivity.class);
                break;
            case R.id.button5:
                startActivity(SlipRemoveActivity.class);
                break;
            case R.id.button6:
                startActivity(SongListActivity.class);
                break;
            case R.id.button7:
                startActivity(CustomBehaviorActivity.class);
                break;
            case R.id.button8:
                startActivity(MultipleItemActivity.class);
                break;
        }
    }
}
