package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.tellh.materialdesignlibrarydemo.R;

/**
 * Created by tlh on 2016/4/10.
 */
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsd_view_pager);
        initView();
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

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }
}
