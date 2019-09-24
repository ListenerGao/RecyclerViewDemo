package com.listenergao.recyclerviewdemo.itemDecoration;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

/**
 * Created by gys on 2017/6/13 16:54.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private int mOrientation;

    private static final int HORIZONTAL = 0;
    private static final int VERTICAL = 1;

    public SpaceItemDecoration(int mSpace) {
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

    private int getOrientation(RecyclerView parent) {
        //默认为Vertical
        int orientation = 1;

        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            orientation = ((LinearLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof GridLayoutManager) {
            orientation = ((GridLayoutManager) layoutManager).getOrientation();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            orientation = ((StaggeredGridLayoutManager) layoutManager).getOrientation();
        }
        return orientation;
    }

    /**
     * 是否是最后一行
     *
     * @param parent
     * @param itemPosition
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastRow(RecyclerView parent, int itemPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == VERTICAL) {
                //得到总行数
                Log.d("TAG,", "itemPosition = " + itemPosition);
                int row = childCount / spanCount;
                int temp = (itemPosition / spanCount + 1);
                Log.d("TAG,", "temp = " + temp);
                if (temp >= row) {
                    return true;
                }
            } else if (orientation == HORIZONTAL) {
                if ((itemPosition + 1) % spanCount == 0) {
                    return true;
                }
            }

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

        } else {

        }

        return false;
    }

    /**
     * 是否是最后一列
     *
     * @param parent
     * @param itemPosition
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isLastColumn(RecyclerView parent, int itemPosition, int spanCount, int childCount) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int orientation = ((GridLayoutManager) layoutManager).getOrientation();
            if (orientation == VERTICAL) {
                if ((itemPosition + 1) % spanCount == 0) {
                    return true;
                }
            } else if (orientation == HORIZONTAL) {
//                int columnCount;
//                if (childCount % spanCount == 0) {
//                    columnCount = childCount / spanCount;
//                } else {
//                    columnCount = (childCount / spanCount) + 1;
//                }
//                int currentColumn = (itemPosition + 1) / spanCount + 1;
//                if (currentColumn > columnCount) {
//                    return true;
//                }
                return false;
            }

        } else if (layoutManager instanceof StaggeredGridLayoutManager) {

        } else {

        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        //得到每行或列的个数
        int spanCount = getSpanCount(parent);
        //得到Item的总个数
        int childCount = parent.getAdapter().getItemCount();
        //获取每个Item的位置
        int itemPosition = parent.getChildLayoutPosition(view);
        if (isLastColumn(parent, itemPosition, spanCount, childCount)) {    // 如果是最后一列，则最右边的space为0
            outRect.set(0, 0, 0, mSpace);
        } /*else if (isLastRow(parent, itemPosition, spanCount, childCount)) {    //如果是最后一行，则最底部的space为0
            outRect.set(0, 0, mSpace, 0);
        }*/ else {
            outRect.set(0, 0, mSpace, mSpace);
        }

        Log.d("TAG", "itemPosition=" + itemPosition + "    spanCount=" + spanCount + "    childCount=" + childCount);
        Log.d("TAG", "是否是最后一行：" + isLastRow(parent, itemPosition, spanCount, childCount));
        Log.d("TAG", "是否是最后一列：" + isLastColumn(parent, itemPosition, spanCount, childCount));

    }


}
