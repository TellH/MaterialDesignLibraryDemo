package com.tellh.materialdesignlibrarydemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.orhanobut.logger.Logger;

/**
 * Created by tlh on 2016/4/17.
 */
public class QuickReturnBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "QuickReturnBehavior";
    private int mTouchSlop;
    private boolean once;
    private int distanceToHide;
    private boolean animationTime;
    private static final int DEFAULT = 0;
    private static final int SCALE = 1;
    private static final int DEFAULT_OFFSET = 2;
    private static final int SCALE_OFFSET = 3;
    private int style=DEFAULT_OFFSET;
    private int mOffset;
    private boolean mControlsVisible;

    public QuickReturnBehavior() {
    }

    public QuickReturnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    //经验证，这个方法执行时，child并没有被绘制或初始化，所以不能通过getHeight等方法得到child的尺寸
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        if (!once){
            int screenHeight = MySysApplication.getInstance().screenHeight;
            int top=child.getTop();
            distanceToHide=screenHeight-top;
            Logger.d("screenHeight:"+screenHeight);
            Logger.d("getBottom:"+child.getBottom());
            once=true;
        }
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //处理滑动事件
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.d("TAG","dxConsumed = [" + dxConsumed + "], dyConsumed = [" + dyConsumed + "], dxUnconsumed = [" + dxUnconsumed + "], dyUnconsumed = [" + dyUnconsumed + "]");
        if (animationTime)
            return;
        switch (style){
            case DEFAULT:
                if (dyConsumed > 0&&dyConsumed>mTouchSlop) {//手指上滑
                    hide(child);
                }else if (dyConsumed<0&&dyConsumed<2*mTouchSlop){//手指下滑
                    show(child);
                }
                break;
            case DEFAULT_OFFSET:
                clipOffset();
                Logger.d("mOffset:"+mOffset);
                Logger.d("distanceToHide:"+distanceToHide);
                move(mOffset,child);
                if ((mOffset < distanceToHide && dyConsumed > 0) || (mOffset > 0 && dyConsumed < 0)) {
                    mOffset += dyConsumed;
                }
                break;
            case SCALE:
                break;
            case SCALE_OFFSET:
                break;
            default:
                break;
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        if (style!=DEFAULT_OFFSET&&style!=SCALE_OFFSET)
            return;
        if (mControlsVisible) {
            if (mOffset > mTouchSlop*2) {
                setInvisible(child);
            } else {
                setVisible(child);
            }
        } else {
            if ((distanceToHide - mOffset) > mTouchSlop*2) {
                setVisible(child);
            }
        }

    }

    private void setVisible(View view) {
        show(view);
        mOffset = 0;
        mControlsVisible = true;
    }


    private void setInvisible(View view) {
        hide(view);
        mOffset = distanceToHide;
        mControlsVisible = false;
    }

    private void clipOffset() {
        if (mOffset > distanceToHide) {
            mOffset = distanceToHide;
        } else if (mOffset < 0) {
            mOffset = 0;
        }
    }


    protected void show(View view) {
        if (view != null) {
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(2))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            animationTime=false;
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            animationTime=true;
                        }
                    })
                    .start();
        }
    }

    protected void hide(View view) {
        if (view != null) {
            view.animate()
                    .translationY(distanceToHide)
                    .setInterpolator(new AccelerateInterpolator(2))
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            animationTime=false;
                        }

                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            animationTime=true;
                        }
                    })
                    .start();
        }
    }

    public void move(int distance, View view) {
        view.setTranslationY(distance);
    }


}
