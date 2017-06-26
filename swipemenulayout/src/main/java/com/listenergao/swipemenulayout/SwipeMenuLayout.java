package com.listenergao.swipemenulayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.util.ArrayList;

/**
 * Created by gys on 2017/6/24 15:59.
 */

public class SwipeMenuLayout extends ViewGroup {

    private static final String TAG = SwipeMenuLayout.class.getSimpleName();
    /**
     * 手指滑动的距离
     * 手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件，如viewpager就是用这个距离来判断用户是否翻页
     */
    private int mScaledTouchSlop;
    /**
     * 用于处理滑动的工具类
     * 使用：http://blog.csdn.net/guolin_blog/article/details/48719871
     */
    private Scroller mScroller;

    private final ArrayList<View> mMatchParentChildren = new ArrayList<>(1);

    private int mLeftViewResID;
    private int mRightViewResID;
    private int mContentViewResID;
    private boolean isShowLeftView;
    private boolean isShowRightView;
    private float mFraction = 0.5f;

    private View mLeftView;
    private View mRightView;
    private View mContentView;
    private MarginLayoutParams mContentViewLayoutParams;

    private PointF mLastP;
    private PointF mFirstP;
    private boolean isSwipeing;
    private boolean isTouched;
    private boolean mCanLeftSwipe = true;
    private boolean mCanRightSwipe = true;

    private static SwipeMenuLayout mViewCache;
    private static State mStateCache;

    public SwipeMenuLayout(Context context) {
        this(context, null);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        //创建辅助对象,包含了方法和标准的常量用来设置UI的超时、大小和距离
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        //获取手指滑动的距离
        mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        mScroller = new Scroller(context);

        //获取自定义属性
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout, defStyleAttr, 0);
//        TypedArray typedArray1 = context.obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout);

        int indexCount = typedArray.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.SwipeMenuLayout_leftView) {
                mLeftViewResID = typedArray.getResourceId(R.styleable.SwipeMenuLayout_leftView, -1);
            } else if (attr == R.styleable.SwipeMenuLayout_rightView) {
                mRightViewResID = typedArray.getResourceId(R.styleable.SwipeMenuLayout_rightView, -1);
            } else if (attr == R.styleable.SwipeMenuLayout_contentView) {
                mContentViewResID = typedArray.getResourceId(R.styleable.SwipeMenuLayout_contentView, -1);
            } else if (attr == R.styleable.SwipeMenuLayout_isShowLeftView) {
                isShowLeftView = typedArray.getBoolean(R.styleable.SwipeMenuLayout_isShowLeftView, true);
            } else if (attr == R.styleable.SwipeMenuLayout_isShowRightView) {
                isShowRightView = typedArray.getBoolean(R.styleable.SwipeMenuLayout_isShowRightView, true);
            } else if (attr == R.styleable.SwipeMenuLayout_fraction) {
                mFraction = typedArray.getFloat(R.styleable.SwipeMenuLayout_fraction, 0.5f);
            }
        }

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到childView的个数
        int childCount = getChildCount();
        //参考frameLayout测量代码
        final boolean measureMatchParentChildren =
                MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY ||
                        MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY;
        mMatchParentChildren.clear();
        int maxHeight = 0;
        int maxWidth = 0;
        int childState = 0;
        //遍历childViews
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);

            if (child.getVisibility() != GONE) {
                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                maxWidth = Math.max(maxWidth,
                        child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                maxHeight = Math.max(maxHeight,
                        child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
                if (measureMatchParentChildren) {
                    if (lp.width == LayoutParams.MATCH_PARENT ||
                            lp.height == LayoutParams.MATCH_PARENT) {
                        mMatchParentChildren.add(child);
                    }
                }
            }
        }
        // Check against our minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(maxHeight, heightMeasureSpec,
                        childState << MEASURED_HEIGHT_STATE_SHIFT));

        childCount = mMatchParentChildren.size();
        if (childCount > 1) {
            for (int i = 0; i < childCount; i++) {
                final View child = mMatchParentChildren.get(i);
                final MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();

                final int childWidthMeasureSpec;
                if (lp.width == LayoutParams.MATCH_PARENT) {
                    final int width = Math.max(0, getMeasuredWidth()
                            - lp.leftMargin - lp.rightMargin);
                    childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(
                            width, MeasureSpec.EXACTLY);
                } else {
                    childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                            lp.leftMargin + lp.rightMargin,
                            lp.width);
                }

                final int childHeightMeasureSpec;
                if (lp.height == FrameLayout.LayoutParams.MATCH_PARENT) {
                    final int height = Math.max(0, getMeasuredHeight()
                            - lp.topMargin - lp.bottomMargin);
                    childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                            height, MeasureSpec.EXACTLY);
                } else {
                    childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                            lp.topMargin + lp.bottomMargin,
                            lp.height);
                }

                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        Log.d(TAG, "childCount = " + childCount + "  paddingLeft = " + paddingLeft + "  paddingTop = " + paddingTop);

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (mLeftView == null && childView.getId() == mLeftViewResID) {
                //找到左边View
                mLeftView = childView;
                mLeftView.setClickable(true);
            } else if (mRightView == null && childView.getId() == mRightViewResID) {
                //找到右边View
                mRightView = childView;
                mRightView.setClickable(true);
            } else if (mContentView == null && childView.getId() == mContentViewResID) {
                //找到内容View
                mContentView = childView;
                mContentView.setClickable(true);
            }
        }

        if (mContentView != null) {
            mContentViewLayoutParams = ((MarginLayoutParams) mContentView.getLayoutParams());
            int contentViewLeft = paddingLeft + mContentViewLayoutParams.leftMargin;
            int contentViewTop = paddingTop + mContentViewLayoutParams.topMargin;
            int contentViewRight = paddingLeft + mContentViewLayoutParams.leftMargin + mContentView.getMeasuredWidth();
            int contentViewBottom = contentViewTop + mContentView.getMeasuredHeight();
            mContentView.layout(contentViewLeft, contentViewTop, contentViewRight, contentViewBottom);
        }

        if (mLeftView != null) {
            MarginLayoutParams mLeftViewLayoutParams = (MarginLayoutParams) mLeftView.getLayoutParams();
            int leftViewLeft = -mLeftView.getMeasuredWidth() + mLeftViewLayoutParams.leftMargin + mLeftViewLayoutParams.rightMargin;
            int leftViewTop = paddingTop + mLeftViewLayoutParams.topMargin;
            int leftViewRight = -mLeftViewLayoutParams.rightMargin;
            int leftViewBottom = leftViewTop + mLeftView.getMeasuredHeight();
            mLeftView.layout(leftViewLeft, leftViewTop, leftViewRight, leftViewBottom);

        }

        if (mRightView != null) {
            MarginLayoutParams mRightViewLayoutParams = (MarginLayoutParams) mRightView.getLayoutParams();
            int rightViewLeft = mContentView.getRight() + mContentViewLayoutParams.leftMargin + mRightViewLayoutParams.rightMargin;
            int rightViewTop = paddingTop + mRightViewLayoutParams.topMargin;
            int rightViewRight = rightViewLeft + mRightView.getMeasuredWidth();
            int rightViewBottom = rightViewTop + mRightView.getMeasuredHeight();
            mRightView.layout(rightViewLeft, rightViewTop, rightViewRight, rightViewBottom);

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //   System.out.println(">>>>dispatchTouchEvent() ACTION_DOWN");

                isSwipeing = false;
                if (mLastP == null) {
                    mLastP = new PointF();
                }
                mLastP.set(ev.getRawX(), ev.getRawY());
                if (mFirstP == null) {
                    mFirstP = new PointF();
                }
                mFirstP.set(ev.getRawX(), ev.getRawY());
                if (mViewCache != null) {
                    if (mViewCache != this) {
                        mViewCache.handlerSwipeMenu(State.CLOSE);

                    }
//                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                // System.out.println(">>>>dispatchTouchEvent() ACTION_MOVE getScrollX:" + getScrollX());
                isSwipeing = true;
                float distanceX = mLastP.x - ev.getRawX();
                float distanceY = mLastP.y - ev.getRawY();
                if (Math.abs(distanceY) > mScaledTouchSlop * 2) {
                    break;
                }
                //当处于水平滑动时，禁止父类拦截
                if (Math.abs(distanceX) > mScaledTouchSlop * 2 || Math.abs(getScrollX()) > mScaledTouchSlop * 2) {
                    requestDisallowInterceptTouchEvent(true);
                }
                scrollBy((int) (distanceX), 0);//滑动使用scrollBy

                //越界修正
                if (getScrollX() < 0) {
                    if (!mCanRightSwipe || mLeftView == null) {
                        scrollTo(0, 0);
                    } else {//左滑
                        if (getScrollX() < mLeftView.getLeft()) {
                            scrollTo(mLeftView.getLeft(), 0);
                        }

                    }
                } else if (getScrollX() > 0) {
                    if (!mCanLeftSwipe || mRightView == null) {
                        scrollTo(0, 0);
                    } else {
                        if (getScrollX() > mRightView.getRight() - mContentView.getRight() - mContentViewLayoutParams.rightMargin) {
                            scrollTo(mRightView.getRight() - mContentView.getRight() - mContentViewLayoutParams.rightMargin, 0);
                        }
                    }
                }

                mLastP.set(ev.getRawX(), ev.getRawY());

                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL: {
                //    System.out.println(">>>>dispatchTouchEvent() ACTION_CANCEL OR ACTION_UP");
                State result = isShouldOpen(getScrollX());
                handlerSwipeMenu(result);
                break;
            }
            default: {
                break;
            }
        }

        return super.dispatchTouchEvent(ev);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        // Log.d(TAG, "dispatchTouchEvent() called with: " + "ev = [" + event + "]");

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                //对左边界进行处理
                float distance = mLastP.x - event.getRawX();
                if (Math.abs(distance) > mScaledTouchSlop) {
                    // 当手指拖动值大于mScaledTouchSlop值时，认为应该进行滚动，拦截子控件的事件
                    return true;
                }
                break;

            }

        }
        return super.onInterceptTouchEvent(event);
    }

    /**
     * 自动设置状态
     *
     * @param result
     */
    private void handlerSwipeMenu(State result) {
        if (result == State.LEFTOPEN) {
            mScroller.startScroll(getScrollX(), 0, mLeftView.getLeft() - getScrollX(), 0);
            mViewCache = this;
            mStateCache = result;
        } else if (result == State.RIGHTOPEN) {
            mViewCache = this;
            mScroller.startScroll(getScrollX(), 0, mRightView.getRight() - mContentView.getRight() - mContentViewLayoutParams.rightMargin - getScrollX(), 0);
            mStateCache = result;
        } else {
            mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0);
            mViewCache = null;
            mStateCache = null;

        }
        invalidate();
    }


    @Override
    public void computeScroll() {
        //判断Scroller是否执行完毕：
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            //通知View重绘-invalidate()->onDraw()->computeScroll()
            invalidate();
        }
    }


    /**
     * 根据当前的scrollX的值判断松开手后应处于何种状态
     *
     * @param scrollX
     * @return
     */
    private State isShouldOpen(int scrollX) {
        if (getScrollX() < 0 && mLeftView != null) {
            //➡滑动
            //获得leftView的测量长度
            if (Math.abs(mLeftView.getWidth() * mFraction) < Math.abs(getScrollX())) {
                return State.LEFTOPEN;
            }

        } else if (getScrollX() > 0 && mRightView != null) {
            //⬅️滑动
            if (Math.abs(mRightView.getWidth() * mFraction) < Math.abs(getScrollX())) {
                return State.RIGHTOPEN;
            }

        }
        return State.CLOSE;
    }


    @Override
    protected void onDetachedFromWindow() {
        if (this == mViewCache) {
            mViewCache.handlerSwipeMenu(State.CLOSE);
        }
        super.onDetachedFromWindow();
        //  Log.i(TAG, ">>>>>>>>onDetachedFromWindow");

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this == mViewCache) {
            mViewCache.handlerSwipeMenu(mStateCache);
        }
        // Log.i(TAG, ">>>>>>>>onAttachedToWindow");
    }

    public void resetStatus() {
        if (mViewCache != null) {
            if (mStateCache != null && mStateCache != State.CLOSE && mScroller != null) {
                mScroller.startScroll(mViewCache.getScrollX(), 0, -mViewCache.getScrollX(), 0);
                mViewCache.invalidate();
                mViewCache = null;
                mStateCache = null;
            }
        }
    }


    public float getFraction() {
        return mFraction;
    }

    public void setFraction(float mFraction) {
        this.mFraction = mFraction;
    }

    public boolean isCanLeftSwipe() {
        return mCanLeftSwipe;
    }

    public void setCanLeftSwipe(boolean mCanLeftSwipe) {
        this.mCanLeftSwipe = mCanLeftSwipe;
    }

    public boolean isCanRightSwipe() {
        return mCanRightSwipe;
    }

    public void setCanRightSwipe(boolean mCanRightSwipe) {
        this.mCanRightSwipe = mCanRightSwipe;
    }

    public static SwipeMenuLayout getViewCache() {
        return mViewCache;
    }


    public static State getStateCache() {
        return mStateCache;
    }


}
