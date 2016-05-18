package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.github.rubensousa.floatingtoolbar.FloatingToolbar;
import com.orhanobut.logger.Logger;
import com.tellh.materialdesignlibrarydemo.R;

/**
 * Created by tlh on 2016/4/10.
 */

/**
 * SearchView的用法
 * {@link http://my.oschina.net/summerpxy/blog/175061?p=1}
 * {@link http://www.cnblogs.com/a0000/p/4487608.html}
 * 夜间模式的用法
 * {@link http://kingideayou.github.io/2016/03/07/appcompat_23.2_day_night/}
 */
public class CircularRevealActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    private FloatingToolbar mFloatingToolbar;
    private FloatingActionButton fab;
    private CoordinatorLayout mLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular_reveal);
        initView();
        setSupportActionBar(toolbar);

        updateDataNightUiMode();

        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPagerAdapter.addFragment("Cat", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Dog", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Mouse", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Bird", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Chicken", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Tiger", PagerFragment.newInstance());
        mViewPagerAdapter.addFragment("Elephant", PagerFragment.newInstance());

        viewPager.setAdapter(mViewPagerAdapter);
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    private void updateDataNightUiMode() {
        //        获取应用当前的主题
        int uiMode = getResources().getConfiguration().uiMode;
        int dayNightUiMode = uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (dayNightUiMode == Configuration.UI_MODE_NIGHT_NO) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_NO;
        } else if (dayNightUiMode == Configuration.UI_MODE_NIGHT_YES) {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mLayout = (CoordinatorLayout) findViewById(R.id.container);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mFloatingToolbar = (FloatingToolbar) findViewById(R.id.floatingToolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mFloatingToolbar.attachFab(fab);
        mFloatingToolbar.setClickListener(new FloatingToolbar.ItemClickListener() {
            @Override
            public void onItemClick(MenuItem item) {
            }
            @Override
            public void onItemLongClick(MenuItem item) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_day_mode:
                if (mDayNightMode == AppCompatDelegate.MODE_NIGHT_NO) return true;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                return true;
            case R.id.action_night_mode:
                if (mDayNightMode == AppCompatDelegate.MODE_NIGHT_YES) return true;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mFloatingToolbar!=null&&mFloatingToolbar.isShowing()){
            mFloatingToolbar.hide();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        //根据当前模式设置菜单图标
        MenuItem shareItem = menu.findItem(R.id.action_share);
        if (mDayNightMode == AppCompatDelegate.MODE_NIGHT_NO) {
            shareItem.setIcon(R.drawable.ic_share_black_24dp);
        } else if (mDayNightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            shareItem.setIcon(R.drawable.ic_share_white_24dp);
        }
        return true;
    }
}