package com.tellh.materialdesignlibrarydemo;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

import com.orhanobut.logger.Logger;

/**
 * Created by tlh on 2016/4/17.
 */
public class QuickReturnBehavior extends CoordinatorLayout.Behavior<View> {
    private static final String TAG ="QuickReturnBehavior";

    public QuickReturnBehavior() {
    }

    public QuickReturnBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    //处理滑动事件
    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        Logger.d("dxConsumed = [" + dxConsumed + "], dyConsumed = [" + dyConsumed + "], dxUnconsumed = [" + dxUnconsumed + "], dyUnconsumed = [" + dyUnconsumed + "]");
        Logger.d("getBottom:"+child.getBottom());
        Logger.d("getTop:"+child.getTop());
        Logger.d("getHeight:"+child.getMeasuredHeight());
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
//        Logger.d("getHeight"+child.getHeight());
        return true;
    }
}
