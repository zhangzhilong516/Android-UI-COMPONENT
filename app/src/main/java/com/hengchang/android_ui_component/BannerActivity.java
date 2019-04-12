package com.hengchang.android_ui_component;

import android.view.View;
import android.widget.ImageView;

import com.hengchang.ui_component.UIActivity;
import com.hengchang.ui_component.adapter.recycleradapter.UIRecyclerAdapter;
import com.hengchang.ui_component.adapter.recycleradapter.UIRecyclerViewHolder;
import com.hengchang.ui_component.layout.UIButton;
import com.hengchang.ui_component.utils.UIDisplayHelper;
import com.hengchang.ui_component.widget.UITitleBarLayout;
import com.hengchang.ui_component.widget.banner.UIBannerAdapter;
import com.hengchang.ui_component.widget.banner.UIBannerView;
import com.hengchang.ui_component.widget.recyclerview.DefaultLoadCreator;
import com.hengchang.ui_component.widget.recyclerview.DefaultRefreshCreator;
import com.hengchang.ui_component.widget.recyclerview.UILoadRefreshRecyclerView;
import com.hengchang.ui_component.widget.recyclerview.UIRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BannerActivity extends UIActivity {
    private UILoadRefreshRecyclerView mLoadRefreshRecyclerView;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_banner);
    }

    @Override
    public void initTitle(UITitleBarLayout titleBarLayout) {
        titleBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBarLayout.setTitle("BannerView");
    }

    @Override
    public void initView() {
        mLoadRefreshRecyclerView = findViewById(R.id.ui_load_refresh_recycler_view);
        mLoadRefreshRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void initData() {

        List<String> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add("这是第" + i + "条数据");
        }
//        mLoadRefreshRecyclerView.setAdapter(new UIRecyclerAdapter<String>(this, data, R.layout.recycler_item) {
//            @Override
//            public void convert(UIRecyclerViewHolder holder, String item) {
//                holder.setText(R.id.text_view, item);
//            }
//
//        });
        mLoadRefreshRecyclerView.setAdapter(new UIRecyclerAdapter<String>(this,data) {
            @Override
            public void convert(UIRecyclerViewHolder holder, String item, int position) {
                if(getItemViewType(position) == R.layout.recycler_item){
                    holder.setText(R.id.text_view,item);
                }
            }

            @Override
            public int getLayoutId(String item, int position) {
                if(position % 2 == 0){
                    return R.layout.activity_main;
                }
                return R.layout.recycler_item;
            }
        });
        UIBannerView bannerView = new UIBannerView(this);
        RecyclerView.LayoutParams bannerViewParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                UIDisplayHelper.dp2px(200));
        bannerView.setLayoutParams(bannerViewParams);
        bannerView.setAdapter(new UIBannerAdapter() {
            @Override
            public View getView(int position, View convertView) {
                ImageView imageView = new ImageView(BannerActivity.this);
                imageView.setImageResource(R.drawable.ic_launcher_background);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                return imageView;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        mLoadRefreshRecyclerView.addRefreshViewCreator(new DefaultRefreshCreator());
        mLoadRefreshRecyclerView.setOnRefreshListener(new UIRefreshRecyclerView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadRefreshRecyclerView.onStopRefresh();
                    }
                }, 2000);

            }
        });

        UIButton imageButton = new UIButton(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        imageButton.setLayoutParams(layoutParams);
        imageButton.setBackgroundResource(R.color.ui_color_10_pure_black);
        imageButton.setText("我是测试头部");
        mLoadRefreshRecyclerView.addHeaderView(bannerView);

        imageButton.setText("我是测试底部");
        mLoadRefreshRecyclerView.addFooterView(imageButton);

        mLoadRefreshRecyclerView.addLoadViewCreator(new DefaultLoadCreator());
        mLoadRefreshRecyclerView.setOnLoadMoreListener(new UILoadRefreshRecyclerView.OnLoadMoreListener() {
            @Override
            public void onLoad() {
                mLoadRefreshRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLoadRefreshRecyclerView.onStopLoad();
                    }
                }, 2000);
            }
        });
    }
}
