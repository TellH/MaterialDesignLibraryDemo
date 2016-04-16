package com.tellh.materialdesignlibrarydemo.BottomNavigationBar;

import android.animation.Animator;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.tellh.materialdesignlibrarydemo.R;

import java.util.ArrayList;

public class DemoActivity extends AppCompatActivity {

    private DemoFragment currentFragment;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private FragmentManager fragmentManager = getFragmentManager();
    private AHBottomNavigation bottomNavigation;
    private FloatingActionButton floatingActionButton;
    private String TAG = "DemoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initUI();
    }

    /**
     * Init UI
     */
    private void initUI() {

        bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.floating_action_button);

        //通过AHBottomNavigationItem设置
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_maps_place, R.color.color_tab_1);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_maps_local_bar, R.color.color_tab_2);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_maps_local_restaurant, R.color.color_tab_3);

        bottomNavigationItems.add(item1);
        bottomNavigationItems.add(item2);
        bottomNavigationItems.add(item3);
        bottomNavigation.addItems(bottomNavigationItems);
        bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        //设置小圆点的颜色
        bottomNavigation.setNotificationBackgroundColor(Color.parseColor("#F63D2B"));
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, boolean wasReSelected) {
                Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "], wasReSelected = [" + wasReSelected + "]");
                //通过position切换不同的fragment
                if (position == 1) {
                    //移除Notification小圆点
                    bottomNavigation.setNotification(0, 1);

                    if (!wasReSelected) {
                        floatingActionButton.setVisibility(View.VISIBLE);
                        floatingActionButton.setAlpha(0f);
                        floatingActionButton.setScaleX(0f);
                        floatingActionButton.setScaleY(0f);
                        floatingActionButton.animate()
                                .alpha(1)
                                .scaleX(1)
                                .scaleY(1)
                                .setDuration(300)
                                .setInterpolator(new OvershootInterpolator())
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        floatingActionButton.animate()
                                                .setInterpolator(new LinearOutSlowInInterpolator())
                                                .start();
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .start();
                    }
                } else {
                    if (floatingActionButton.getVisibility() == View.VISIBLE) {
                        floatingActionButton.animate()
                                .alpha(0)
                                .scaleX(0)
                                .scaleY(0)
                                .setDuration(300)
                                .setInterpolator(new LinearOutSlowInInterpolator())
                                .setListener(new Animator.AnimatorListener() {
                                    @Override
                                    public void onAnimationStart(Animator animation) {

                                    }

                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationCancel(Animator animation) {
                                        floatingActionButton.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onAnimationRepeat(Animator animation) {

                                    }
                                })
                                .start();
                    }
                }

                if (!wasReSelected) {
                    currentFragment = DemoFragment.newInstance(position);
                    fragmentManager.beginTransaction()
                            .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)//让fragment有一个淡入淡出的效果
                            .replace(R.id.fragment_container, currentFragment)
                            .commit();
                } else if (position > 0) {
                    currentFragment.refresh();
                }
            }
        });

        currentFragment = DemoFragment.newInstance(0);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, currentFragment)
                .commit();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                bottomNavigation.setNotification(16, 1);
                Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
                        Snackbar.LENGTH_SHORT).show();
            }
        }, 3000);
    }

    /**
     * Update the bottom navigation colored param
     */
    public void updateBottomNavigationColor(boolean isColored) {
        bottomNavigation.setColored(isColored);
    }

    /**
     * Return if the bottom navigation is colored
     */
    public boolean isBottomNavigationColored() {
        return bottomNavigation.isColored();
    }

    /**
     * Add or remove items of the bottom navigation
     */
    public void updateBottomNavigationItems(boolean addItems) {

        AHBottomNavigationItem item4 = new AHBottomNavigationItem(getString(R.string.tab_4),
                ContextCompat.getDrawable(this, R.drawable.ic_maps_local_bar),
                ContextCompat.getColor(this, R.color.color_tab_4));
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(getString(R.string.tab_5),
                ContextCompat.getDrawable(this, R.drawable.ic_maps_place),
                ContextCompat.getColor(this, R.color.color_tab_5));

        if (addItems) {
            bottomNavigation.addItem(item4);
            bottomNavigation.addItem(item5);
            //第一个参数是通知数目，第二个参数是通知tab的位置
            bottomNavigation.setNotification(100, 3);
        } else {
            bottomNavigation.removeAllItems();
            bottomNavigation.addItems(bottomNavigationItems);
        }
    }

    /**
     * Return the number of items in the bottom navigation
     */
    public int getBottomNavigationNbItems() {
        return bottomNavigation.getItemsCount();
    }

    public void updateItemColor(boolean isChecked) {
        if (!isChecked) {
            bottomNavigation.setAccentColor(Color.parseColor("#F63D2B"));
            bottomNavigation.setInactiveColor(Color.parseColor("#747474"));
        } else {
            bottomNavigation.setAccentColor(Color.parseColor("#00FFFF"));
            bottomNavigation.setInactiveColor(Color.parseColor("#93c47d"));
        }
    }

    public void forceDisplayTitleText(boolean isChecked) {
        if (isChecked)
            bottomNavigation.setForceTitlesDisplay(true);
        else
            bottomNavigation.setForceTitlesDisplay(false);
    }
}
