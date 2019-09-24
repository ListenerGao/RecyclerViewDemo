package com.listenergao.recyclerviewdemo.activity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.SwipeRemoveAdapter;
import com.listenergao.recyclerviewdemo.listener.OnSwipeListener;

import java.util.ArrayList;
import java.util.List;

public class SlipRemoveActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private List<String> mData;
    private SwipeRemoveAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void initView() {
        setTitle("RecyclerView侧滑删除");
        setBackArrow();
        setContentLayout(R.layout.activity_slip_remove);

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
        mAdapter = new SwipeRemoveAdapter(mData);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.addSwipeListener(new OnSwipeListener() {
            @Override
            public void onDelete(int position) {
                super.onDelete(position);
                if (position >= 0 && position < mData.size()) {
                    mData.remove(position);
                    mAdapter.notifyItemRemoved(position);//推荐用这个
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用
                    //((SwipeMenuLayout) holder.itemView).quickClose();
                    //mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTop(int position) {
                super.onTop(position);
                if (position > 0 && position < mData.size()) {
                    String s = mData.get(position);
                    mData.remove(s);
                    mAdapter.notifyItemInserted(0);
                    mData.add(0, s);
                    mAdapter.notifyItemRemoved(position + 1);
                    if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
                        mRecyclerView.scrollToPosition(0);
                    }
                    //notifyItemRangeChanged(0,holder.getAdapterPosition()+1);
                }
            }
        });
    }

}
