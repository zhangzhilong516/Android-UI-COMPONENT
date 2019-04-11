package com.hengchang.ui_component.widget.pullRefreshLayout;

/**
 * @author zhangzhilong
 * @date 2019/4/11.
 * description：
 */
public abstract class OnPullListener {
    public void onMoveTarget(int offset){}

    public void onMoveRefreshView(int offset){}

    public abstract void onRefresh();
}
