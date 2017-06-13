package com.listenergao.recyclerviewdemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.listenergao.recyclerviewdemo.activity.BaseActivity;
import com.listenergao.recyclerviewdemo.activity.CommonRecyclerViewActivity;
import com.listenergao.recyclerviewdemo.activity.GridLayoutActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mCommonRecyclerViewActivity;
    private Button mGridLayoutActivity;


    @Override
    protected void initView() {

        setTitle("RecyclerViewDemo");
        setContentLayout(R.layout.activity_main);

        mCommonRecyclerViewActivity = ((Button) findViewById(R.id.button1));
        mGridLayoutActivity = ((Button) findViewById(R.id.button2));
        mCommonRecyclerViewActivity.setOnClickListener(this);
        mGridLayoutActivity.setOnClickListener(this);
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
        }
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
