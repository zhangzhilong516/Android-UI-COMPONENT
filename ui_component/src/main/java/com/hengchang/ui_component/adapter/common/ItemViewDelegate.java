package com.hengchang.ui_component.adapter.common;


/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des: 布局代理
 */
public interface ItemViewDelegate<T> {

    int getItemViewLayoutId();

    boolean isCurrentType(T item, int position);

    void convert(ViewHolder holder, T t, int position);
}
