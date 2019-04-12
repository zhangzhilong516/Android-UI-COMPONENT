package com.hengchang.android_ui_component;

import android.view.View;


import com.hengchang.ui_component.UIActivity;
import com.hengchang.ui_component.adapter.recycleradapter.UIRecyclerAdapter;
import com.hengchang.ui_component.adapter.recycleradapter.UIRecyclerViewHolder;
import com.hengchang.ui_component.widget.UITitleBarLayout;
import com.hengchang.ui_component.widget.pullRefreshLayout.OnPullListener;
import com.hengchang.ui_component.widget.pullRefreshLayout.UIPullRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterActivity extends UIActivity {

    private RecyclerView recyclerView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_adapter);
    }

    @Override
    public void initTitle(UITitleBarLayout titleBarLayout) {
        titleBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBarLayout.setTitle("UIAdapter");
    }

    @Override
    public void initView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final UIPullRefreshLayout pullRefreshLayout = findViewById(R.id.pull_refresh_layout);
        pullRefreshLayout.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("这是第" + i + "条数据");
        }
        recyclerView.setAdapter(new UIRecyclerAdapter<String>(this,data) {
            @Override
            public void convert(UIRecyclerViewHolder holder, String item, int position) {
                holder.setText(R.id.text_view,item);
            }

            @Override
            public int getLayoutId(String item, int position) {
                return R.layout.recycler_item;
            }

        });
    }
}
