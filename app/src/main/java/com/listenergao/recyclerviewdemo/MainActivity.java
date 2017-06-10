package com.listenergao.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.listenergao.recyclerviewdemo.activity.CommonRecyclerViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mCommonRecyclerViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        iniData();
    }

    private void initView() {
        mCommonRecyclerViewActivity = ((Button) findViewById(R.id.button1));
        mCommonRecyclerViewActivity.setOnClickListener(this);
    }

    private void iniData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(CommonRecyclerViewActivity.class);
                break;
        }
    }

    private void startActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
