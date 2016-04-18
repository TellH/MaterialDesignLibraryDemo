package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.View;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tlh on 2016/4/17.
 */
public class MyAvatarBehavior extends CoordinatorLayout.Behavior<CircleImageView>  {
    private static final String TAG = "MyAvatarBehavior";
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, CircleImageView child, View dependency) {
        Log.d(TAG, "layoutDependsOn() called with: " + "parent = [" + parent + "], child = [" + child + "], dependency = [" + dependency + "]");
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, CircleImageView child, View dependency) {
        Log.d(TAG,"PivotY"+child.getPivotY());
        return true;
    }

}
