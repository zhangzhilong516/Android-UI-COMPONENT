package com.hengchang.ui_component.adapter.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des: GridView/ListView 公共Adapter
 */
public abstract class UIAdapter<T> extends BaseAdapter {

    protected Context context;

    protected List<T> mData;

    protected int mLayoutId;

    public UIAdapter(Context context, int layoutId, List<T> data) {
        this.context = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        UIViewHolder viewHolder = UIViewHolder.getHolder(context, convertView, mLayoutId);

        convert(viewHolder, mData.get(position), position);

        return viewHolder.getConvertView();
    }

    public void setData(List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        notifyDataSetChanged();
    }


    public void delete(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }


    public void add(T data) {
        mData.add(data);
        notifyDataSetChanged();
    }


    public void add(int position, T data) {
        mData.add(position, data);
        notifyDataSetChanged();
    }


    public void addAll(List<T> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public abstract void convert(UIViewHolder viewHolder, T t, int position);
}
