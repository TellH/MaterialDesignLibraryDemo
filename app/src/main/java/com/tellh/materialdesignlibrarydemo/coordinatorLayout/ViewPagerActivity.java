package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.tellh.materialdesignlibrarydemo.R;

import java.lang.reflect.Field;

/**
 * Created by tlh on 2016/4/10.
 */

/**
 * SearchView的用法
 * {@link http://my.oschina.net/summerpxy/blog/175061?p=1}
 * {@link http://www.cnblogs.com/a0000/p/4487608.html}
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
        setSupportActionBar(toolbar);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("search");
        //增加提交按钮
        searchView.setSubmitButtonEnabled(true);
        //通过反射增加改变提交按钮的icon
        try {
            Field field = searchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView iv = (ImageView) field.get(searchView);
            iv.setImageDrawable(this.getResources().getDrawable(
                    R.drawable.ic_done_all_black_24dp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            /**
             * 当用户按下键盘的提交按钮时回调
             * @param query
             * @return flase表明不消耗该提交事件交由SeachView来处理, 软键盘自动退出;true表明消耗事件，不交给SearchView来处理
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(searchView, query, Snackbar.LENGTH_SHORT).show();
                return false;
            }

            /**
             * 当搜索字符串改变时回调
             * @param newText
             * @return
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

}
