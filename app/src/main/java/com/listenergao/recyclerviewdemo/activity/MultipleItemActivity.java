package com.listenergao.recyclerviewdemo.activity;

import android.view.View;

import com.listenergao.recyclerviewdemo.R;

public class MultipleItemActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void initView() {
        setTitle("RecyclerView (Multiple Item)");
        setBackArrow();
        setContentLayout(R.layout.activity_multiple_item);

        findViewById(R.id.button).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                startActivity(MultipleItemOneActivity.class);
                break;
            default:
                break;
        }
    }
}
