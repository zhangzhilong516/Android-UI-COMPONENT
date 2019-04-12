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

    public UIAdapter(Context context, List<T> data) {
        this.context = context;
        this.mData = data;
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

        UIViewHolder viewHolder = UIViewHolder.getHolder(context, convertView, getItemViewType(position));

        convert(viewHolder, mData.get(position), position);

        return viewHolder.getConvertView();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutId(mData.get(position),position);
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
    protected abstract int getLayoutId(T item,int position);
}
