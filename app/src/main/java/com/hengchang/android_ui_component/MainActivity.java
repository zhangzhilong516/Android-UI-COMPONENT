package com.hengchang.android_ui_component;

import android.app.ListActivity;
import android.view.View;

import com.hengchang.ui_component.UIActivity;
import com.hengchang.ui_component.widget.UITitleBarLayout;


public class MainActivity extends UIActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initTitle(UITitleBarLayout titleBarLayout) {
        titleBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBarLayout.setTitle("UI组件化");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }


    public void UIAdapter(View v){
        startActivity(AdapterActivity.class);
    }
    public void Dialog(View v){
        startActivity(DialogActivity.class);
    }
    public void BannerView(View v){
        startActivity(BannerActivity.class);
    }
}
