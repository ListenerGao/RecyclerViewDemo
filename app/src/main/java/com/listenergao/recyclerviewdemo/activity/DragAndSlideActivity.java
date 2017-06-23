package com.listenergao.recyclerviewdemo.activity;

import android.app.Service;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.DragAndSlideAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gys on 2017/6/22 18:50.
 * RecyclerView 拖拽和滑动删除
 */
public class DragAndSlideActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private DragAndSlideAdapter mAdapter;

    @Override
    protected void initView() {
        setTitle("RecyclerView拖拽和滑动删除");
        setBackArrow();
        setContentLayout(R.layout.activity_drag_and_slide);


        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mData.add("Item " + i);
        }

        initRecyclerView();

    }

    private void initRecyclerView() {
        mAdapter = new DragAndSlideAdapter(mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        //设置Item分割线
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.divider_bg2);
        itemDecoration.setDrawable(drawable);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper touchHelper = new ItemTouchHelper(new Callback());
        touchHelper.attachToRecyclerView(mRecyclerView);
    }

    private class Callback extends ItemTouchHelper.Callback {
        //该方法用于处理拖拽和滑动事件
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

            int dragFlags;  //拖拽标识
            int swipeFlags; //滑动标识
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlags = 0;
            } else {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlags = 0;
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
            int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }
            mAdapter.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        //当手指长按Item时（拖拽开始时）调用
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            super.onSelectedChanged(viewHolder, actionState);

            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                //设置Item背景颜色
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);

                //获取系统震动服务
                Vibrator vib = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
                //震动50毫秒
                vib.vibrate(50);
            }

        }

        //当手指松开的时候（拖拽完成的时候）调用
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            //清除Item背景颜色
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
        }
    }

}