package com.listenergao.recyclerviewdemo.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by gys on 2017/6/22 10:18.
 * 瀑布流的ItemDecoration
 */

public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public StaggeredItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    /**
     * 获取每行或列的个数
     *
     * @param parent
     * @return
     */
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager)
                    .getSpanCount();
        }
        return spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        //默认设置Item右边和底部的边距
//        outRect.set(0, 0, mSpace, mSpace);
        //获取每个Item的位置
        int itemPosition = parent.getChildAdapterPosition(view);
        //得到每行或列的个数
        int spanCount = getSpanCount(parent);
        //第一行的头部不设置边距
        if (itemPosition < spanCount) {
            outRect.top = 0;
        } else {
            outRect.top = mSpace;
        }
        //最后一列的右边不设置边距
        if ((itemPosition + 1) % spanCount == 0) {
            outRect.right = 0;
        } else {
            outRect.right = mSpace;
        }
    }
}
