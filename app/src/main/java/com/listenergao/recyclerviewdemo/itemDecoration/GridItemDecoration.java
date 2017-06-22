package com.listenergao.recyclerviewdemo.itemDecoration;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by gys on 2017/6/22 11:13.
 * 网格布局的ItemDecoration
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    public GridItemDecoration(int space) {
        mSpace = space;
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
        //得到每行或列的个数
        int spanCount = getSpanCount(parent);
        //获取每个Item的位置
        int itemPosition = parent.getChildLayoutPosition(view);

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (VERTICAL == orientation) {
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
                Log.d("TAG","grid VERTICAL执行了.....");
            } else {
                if (itemPosition < spanCount) {
                    outRect.left = 0;
                } else {
                    outRect.left = mSpace;
                }

                if (itemPosition % spanCount == 0) {
                    outRect.top = 0;
                } else {
                    outRect.top = mSpace;
                }
                Log.d("TAG","grid HORIZONTAL执行了.....");
            }
        }

    }
}
