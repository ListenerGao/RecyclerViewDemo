package com.listenergao.recyclerviewdemo.adapter;

/**
 * Created by gys on 2017/6/24 10:11.
 * 主要用于ItemTouchHelper类中onMove()和onSwiped()方法的回调
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
