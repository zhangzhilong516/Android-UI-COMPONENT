package com.hengchang.android_ui_component;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengchang.ui_component.UIActivity;
import com.hengchang.ui_component.widget.UITitleBarLayout;
import com.hengchang.ui_component.widget.dialog.UIAlertDialog;
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

    public void showTipDialog(View v) {
        new UITipDialog.Builder(this)
                .setTipWord("加载中。。。")
                .setIconType(ICON_TYPE_LOADING)
                .create()
                .show();
    }

    public void showDialog(View v) {
        TextView textView = new TextView(this);
        textView.setText("我是一个Dialog");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundResource(R.drawable.ui_dialog_bg);
        new UIAlertDialog.MessageBuilder(this)
                .setTitle("我是Title")
                .setMessage("我是Content芭拉芭布拉吧")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();

        new AlertDialog.Builder(this)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setTitle("我是Title")
                .setMessage("我是系统Dialog")
                .create()
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
