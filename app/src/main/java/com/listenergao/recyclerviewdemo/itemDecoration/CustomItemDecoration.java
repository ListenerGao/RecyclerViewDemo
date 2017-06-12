package com.listenergao.recyclerviewdemo.itemDecoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by gys on 2017/6/11.
 * 自定义Item分割线（主要用于LinearLayoutManager布局管理器）
 * 继承RecyclerView.ItemDecoration
 */

public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private static final int HORIZONTAL= LinearLayoutManager.HORIZONTAL;
    private static final int VERTICAL= LinearLayoutManager.VERTICAL;



    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //可以通过outRect.set()为没个Item设置偏移量，主要用于绘制Decoration

    }
}
