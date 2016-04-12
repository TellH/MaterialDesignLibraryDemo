package com.tellh.materialdesignlibrarydemo.BottomSheet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tellh.materialdesignlibrarydemo.R;

/**
 * Created by tlh on 2016/4/12.
 */
public class BottomSheetActivity extends AppCompatActivity {
    private NestedScrollView bottomSheet;
    private BottomSheetBehavior<NestedScrollView> behavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        initView();
        behavior = BottomSheetBehavior.from(bottomSheet);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });
    }

    private void initView() {
        bottomSheet = (NestedScrollView) findViewById(R.id.bottom_sheet);
    }
}
