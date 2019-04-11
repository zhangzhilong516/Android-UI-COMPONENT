package com.hengchang.android_ui_component;

import android.view.View;

import com.hengchang.ui_component.UIActivity;
import com.hengchang.ui_component.widget.UITitleBarLayout;
import com.hengchang.ui_component.widget.dialog.UITipDialog;

import static com.hengchang.ui_component.widget.dialog.UITipDialog.Builder.ICON_TYPE_LOADING;

public class DialogActivity extends UIActivity {

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_dialog);
    }

    @Override
    public void initTitle(UITitleBarLayout titleBarLayout) {
        titleBarLayout.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        titleBarLayout.setTitle("DialogActivity");
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    public void showTipDialog(View v){

        new UITipDialog.Builder(this)
                .setTipWord("加载中。。。")
                .setIconType(ICON_TYPE_LOADING)
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
