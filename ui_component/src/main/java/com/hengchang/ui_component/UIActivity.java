package com.hengchang.ui_component;

import android.content.Intent;
import android.os.Bundle;

import com.hengchang.ui_component.utils.UIResHelper;
import com.hengchang.ui_component.utils.UIStatusBarHelper;
import com.hengchang.ui_component.widget.UITitleBarLayout;
import com.hengchang.ui_component.widget.UIToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author zhangzhilong
 * @date 2019/4/4.
 * description：UIActivity基类
 */
public abstract class UIActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        initTitle();
        initView();
        initData();
    }

    /**
     * 初始化TitleBar和状态栏
     */
    private void initTitle() {
        UITitleBarLayout titleBarLayout = findViewById(R.id.ui_title_bar);
        if (titleBarLayout != null) {
            setStatusBarColor(titleBarLayout.getTitleBarColor());
            initTitle(titleBarLayout);
        } else {
            setStatusBarColor(UIResHelper.getAttrColor(this, R.attr.ui_status_bar_color));
        }
    }


    public abstract void setContentView();

    public abstract void initTitle(UITitleBarLayout titleBarLayout);

    public abstract void initView();

    public abstract void initData();


    public void startActivity(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    public void startActivity(Bundle bundle, Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIToast.setToastNull();
    }

    /**
     * 设置沉浸式状态栏
     */
    public void setStatusBarColor(int color) {
        UIStatusBarHelper.setImmersionStatusBar(this, color);
    }

    /**
     * 设置状态栏黑色字体图标
     */
    public void setStatusBarLightMode() {
        UIStatusBarHelper.setStatusBarLightMode(this);
    }

    /**
     * 设置状态栏白色字体图标
     */
    public void setStatusBarDarkMode() {
        UIStatusBarHelper.setStatusBarDarkMode(this);
    }

    /**
     * 设置Activity全屏
     */
    public void setActivityTranslucent() {
        UIStatusBarHelper.setActivityTranslucent(this);
    }
}
