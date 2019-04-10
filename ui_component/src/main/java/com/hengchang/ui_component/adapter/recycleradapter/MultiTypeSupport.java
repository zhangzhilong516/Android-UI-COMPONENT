package com.hengchang.ui_component.adapter.recycleradapter;

/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des:  多布局支持接口
 */
public interface MultiTypeSupport<T> {
    // 根据当前位置或者条目数据返回布局
    int getLayoutId(T item, int position);
}
