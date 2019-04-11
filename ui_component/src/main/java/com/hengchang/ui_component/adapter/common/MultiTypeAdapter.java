package com.hengchang.ui_component.adapter.common;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des: 多布局Adapter
 */
public class MultiTypeAdapter<T> extends UIAdapter<T> {

    private SparseArray<ItemViewDelegate> mItemTypes = new SparseArray<>();

    public MultiTypeAdapter(Context context, List data) {
        super(context, 0, data);
    }

    @Override
    public int getViewTypeCount() {
        return mItemTypes.size();
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < mItemTypes.size(); i++) {
            ItemViewDelegate<T> itemViewDelegate = mItemTypes.valueAt(i);
            if (itemViewDelegate.isCurrentType(getItem(position), position)) {
                mLayoutId = itemViewDelegate.getItemViewLayoutId();
                return mItemTypes.keyAt(i);
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        mLayoutId = getCurrentDelegate(position).getItemViewLayoutId();
        return super.getView(position, convertView, parent);
    }

    /**
     * 获取当前类型的类型代理
     *
     * @param position
     * @return
     */
    public ItemViewDelegate<T> getCurrentDelegate(int position) {
        for (int i = 0; i < mItemTypes.size(); i++) {
            ItemViewDelegate<T> itemViewDelegate = mItemTypes.valueAt(i);
            if (itemViewDelegate.isCurrentType(getItem(position), position)) {
                return itemViewDelegate;
            }
        }
        throw new IllegalArgumentException(
                "No ItemViewDelegate added that matches position=" + position + " in data source");
    }


    public void addItemViewDelegate(ItemViewDelegate<T> itemViewDelegate) {
        mItemTypes.put(mItemTypes.size(), itemViewDelegate);
    }


    @Override
    public void convert(UIViewHolder viewHolder, T t, int position) {
        getCurrentDelegate(position).convert(viewHolder, t, position);
    }
}
