package com.listenergao.recyclerviewdemo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.listenergao.recyclerviewdemo.R;
import com.listenergao.recyclerviewdemo.adapter.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomBehaviorActivity extends BaseActivity implements View.OnClickListener {

    private BottomSheetDialog mBottomSheetDialog;

    @Override
    protected void initView() {
        super.initView();

        setTitle("自定义Behavior");
        setBackArrow();
        setContentLayout(R.layout.activity_custom_behavior);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(PullDownActivity.class);
                break;
            case R.id.button2:
                showBottomSheetDialog();
                break;
            case R.id.button3:
                startActivity(ScaleUpActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * BottomSheetDialog, 一看名字就知道，它就是一个Dialog，使用方法和Dialog 一样，
     * 它是对BottomSheetBehavior 进行了包装，从底部弹出一个Dialog。BottomSheetDialog 使用起来比BottomSheetBehavior更方便，效果更佳。
     * <p>
     * 链接：https://www.jianshu.com/p/82d18b0d18f4
     */
    private void showBottomSheetDialog() {
        if (mBottomSheetDialog == null) {
            mBottomSheetDialog = new BottomSheetDialog(this);
            // 可在布局文件中指定dialog高度
            View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_sheet, null);

            handleList(view);

            mBottomSheetDialog.setContentView(view);
            mBottomSheetDialog.setCancelable(true);
            mBottomSheetDialog.setCanceledOnTouchOutside(true);
            mBottomSheetDialog.show();
        } else {
            mBottomSheetDialog.show();
        }
    }

    private void handleList(View contentView) {
        RecyclerView recyclerView = contentView.findViewById(R.id.recycler_view);

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add("item " + i);
        }

        CommonAdapter adapter = new CommonAdapter(data);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加Item分割线
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
//        mRecyclerView.addItemDecoration(new SpaceItemDecoration(this,SpaceItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        //设置Item点击事件
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getApplication(), "onItemClick--当前位置为=" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //设置Item长按事件
        adapter.setOnItemLongClickListener(new CommonAdapter.OnItemLongClickListener() {
            @Override
            public void OnItemLongClick(View view, int position) {
                Toast.makeText(getApplication(), "OnItemLongClick--当前位置为=" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
