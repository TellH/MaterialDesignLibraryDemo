package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.content.Context;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;

import com.tellh.materialdesignlibrarydemo.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
public class ViewPagerActivity extends AppCompatActivity {
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager viewPager;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private int mDayNightMode = AppCompatDelegate.MODE_NIGHT_AUTO;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gsd_view_pager);
        initView();
        setSupportActionBar(toolbar);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_day_mode:
                if (mDayNightMode==AppCompatDelegate.MODE_NIGHT_NO) return true;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                recreate();
                return true;
            case R.id.action_night_mode:
                if (mDayNightMode==AppCompatDelegate.MODE_NIGHT_YES) return true;
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                recreate();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("search");
//        searchView.setInputType();
        //增加提交按钮
        searchView.setSubmitButtonEnabled(true);
        //通过反射改变提交按钮的icon
        try {
            Field field = searchView.getClass().getDeclaredField("mGoButton");
            field.setAccessible(true);
            ImageView iv = (ImageView) field.get(searchView);
            iv.setImageDrawable(this.getResources().getDrawable(
                    R.drawable.ic_done_all_black_24dp));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        Cursor cursor = getTestCursor();
//        @SuppressWarnings("deprecation")
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
//                R.layout.item, cursor, new String[]{"name"},
//                new int[]{R.id.text});
//        searchView.setSuggestionsAdapter(adapter);

        final List<String> items = new ArrayList<>();
        items.add("Abby");
        items.add("Abel");
        items.add("Baby");
        items.add("Barbie");
        items.add("Barnaby");
        items.add("Cady");
        items.add("Caesar");
        final SearchView.SearchAutoComplete searchSrcTextView = (SearchView.SearchAutoComplete) searchView.findViewById(R.id.search_src_text);
        searchSrcTextView.setThreshold(1);
        final List<String> filterItems = new ArrayList<>();
        searchSrcTextView.setAdapter(new SuggestionAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items, filterItems));
        searchSrcTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchSrcTextView.setText(filterItems.get(position));
                searchSrcTextView.setSelection(filterItems.get(position).length());
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });

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


    //添加suggestion需要的数据
    public Cursor getTestCursor() {
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
                this.getFilesDir() + "/my.db3", null);
        Cursor cursor = null;
        String insertSql = "insert into tb_test (name,age) values (?,?)";
        try {
            db.execSQL(insertSql, new Object[]{"Abby", 1});
            db.execSQL(insertSql, new Object[]{"Abel", 2});
            db.execSQL(insertSql, new Object[]{"Baby", 3});
            db.execSQL(insertSql, new Object[]{"Barbie", 4});
            db.execSQL(insertSql, new Object[]{"Barnaby", 5});
            db.execSQL(insertSql, new Object[]{"Cady", 5});
            db.execSQL(insertSql, new Object[]{"Caesar", 5});
            String querySql = "select * from tb_test";
            cursor = db.rawQuery(querySql, null);
        } catch (Exception e) {
            String sql = "create table tb_test (_id integer primary key autoincrement,name varchar(20),age integer)";
            db.execSQL(sql);
            db.execSQL(insertSql, new Object[]{"Abby", 1});
            db.execSQL(insertSql, new Object[]{"Abel", 2});
            db.execSQL(insertSql, new Object[]{"Baby", 3});
            db.execSQL(insertSql, new Object[]{"Barbie", 4});
            db.execSQL(insertSql, new Object[]{"Barnaby", 5});
            db.execSQL(insertSql, new Object[]{"Cady", 5});
            db.execSQL(insertSql, new Object[]{"Caesar", 5});
            String querySql = "select * from tb_test";
            cursor = db.rawQuery(querySql, null);
        }
        return cursor;
    }

    class SuggestionAdapter<T> extends ArrayAdapter<T> {
        private List<T> items;
        private List<T> filteredItems;
        private ArrayFilter mFilter;

        public SuggestionAdapter(Context context, @LayoutRes int resource, @NonNull List<T> list, List<T> filterItems) {
            super(context, resource, list);
            this.items = list;
            this.filteredItems = filterItems;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public T getItem(int position) {
            if (filteredItems == null)
                filteredItems = new ArrayList<>();
            return filteredItems.get(position);
        }

        @Override
        public Filter getFilter() {
            if (mFilter == null) {
                mFilter = new ArrayFilter();
            }
            return mFilter;
        }

        public int getCount() {
            if (filteredItems == null)
                filteredItems = new ArrayList<>();
            return filteredItems.size();
        }

        private class ArrayFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence prefix) {
                FilterResults results = new FilterResults();
                //custom-filtering of results
                List<String> resultList = new ArrayList<>();
                if (items.get(0) instanceof String) {
                    for (T item : items) {
                        String txt = (String) item;
                        if (txt.contains(prefix))
                            resultList.add(txt);
                    }
                }
                results.values = resultList;
                results.count = resultList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
//                filteredItems = (List<T>) results.values;
                List<T> result = (List<T>) results.values;
                if (result!=null){
                    filteredItems.clear();
                    for (T t : result) {
                        filteredItems.add(t);
                    }
                }
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        }
    }
}