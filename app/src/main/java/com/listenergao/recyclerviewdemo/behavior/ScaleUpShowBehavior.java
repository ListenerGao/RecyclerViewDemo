package com.listenergao.recyclerviewdemo.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewPropertyAnimatorListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.listenergao.recyclerviewdemo.animator.AnimatorUtil;

/**
 * create on 19/09/29
 * 自定义Behavior之上滑显示返回顶部按钮
 *
 * @author listenergao
 */
public class ScaleUpShowBehavior extends FloatingActionButton.Behavior {

    public ScaleUpShowBehavior() {
    }

    public ScaleUpShowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private boolean isAnimatingOut = false;
    private ViewPropertyAnimatorListener viewPropertyAnimatorListener = new ViewPropertyAnimatorListener() {

        @Override
        public void onAnimationStart(View view) {
            isAnimatingOut = true;
        }

        @Override
        public void onAnimationEnd(View view) {
            isAnimatingOut = false;
            view.setVisibility(View.GONE);
        }

        @Override
        public void onAnimationCancel(View arg0) {
            isAnimatingOut = false;
        }
    };

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        //告诉CoordinatorLayout这个Behavior要监听的滑动发现
        //此处监听上下滑动时显示/隐藏Fab，故我们监听垂直方法的滑动
        Log.d("gys", "axes = " + axes);
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        Log.d("gys", "onNestedPreScroll");
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull FloatingActionButton child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        Log.d("gys", "onNestedScroll");
        if (((dyConsumed > 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed > 0)) && child.getVisibility() != View.VISIBLE) {// 显示
            Log.d("gys", "上滑显示");
            // 上滑显示
            AnimatorUtil.scaleShow(child, null);
        } else if (((dyConsumed < 0 && dyUnconsumed == 0) || (dyConsumed == 0
                && dyUnconsumed < 0)) && child.getVisibility() != View.GONE && !isAnimatingOut) {
            Log.d("gys", "下滑隐藏");
            // 下滑隐藏
            AnimatorUtil.scaleHide(child, viewPropertyAnimatorListener);
        }
    }
}
