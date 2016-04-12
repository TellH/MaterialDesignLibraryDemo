package com.tellh.materialdesignlibrarydemo.BottomSheet;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by tlh on 2016/4/12.
 */
public class AutoHideBehavior extends CoordinatorLayout.Behavior<View> {
    private boolean isShown=true;
    private int sinceDirectionChange;

    public AutoHideBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //返回值代表关心哪种类型的滑动事件
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        if (dy > 0 && sinceDirectionChange < 0 || dy < 0 && sinceDirectionChange > 0) {
            child.animate().cancel();
            sinceDirectionChange = 0;
        }
        sinceDirectionChange += dy;
        if (sinceDirectionChange > child.getHeight() && child.getVisibility() == View.VISIBLE) {
            hide(child);
        } else if (sinceDirectionChange < 0 && child.getVisibility() == View.GONE) {
            show(child);
        }
    }
    private void hide(@NonNull View child){
        child.animate()
                .translationY(child.getHeight())
                .setInterpolator(new AccelerateInterpolator(2))
                .start();
        isShown=false;
    }
    private void show(@NonNull View child){
        child.animate()
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isShown=true;
    }
}
