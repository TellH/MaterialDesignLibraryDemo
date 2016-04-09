package com.tellh.materialdesignlibrarydemo.coordinatorLayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tellh.materialdesignlibrarydemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2016/4/9.
 */
public class ScrollActivity extends AppCompatActivity {
    private RecyclerView rv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_scroll);
        final List<String> list=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(String.valueOf(i));
        }
        Log.d("TAG", list.size()+"");
        initView();
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setAdapter(new BaseRecyclerAdapter<String>(this,list) {
            @Override
            public int getItemLayoutId(int viewType) {
                return R.layout.item;
            }

            @Override
            public void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.text,list.get(position));
            }
        });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(android.R.id.list);
    }
}
