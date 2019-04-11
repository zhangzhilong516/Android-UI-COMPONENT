package com.hengchang.ui_component.adapter.common;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des: 公共ViewHolder
 */
public class UIViewHolder {

    private View convertView;

    private SparseArray<View> mViews = new SparseArray<>();

    public UIViewHolder(Context context, View convertView, int layoutId) {

        convertView = LayoutInflater.from(context).inflate(layoutId, null);

        convertView.setTag(this);

        this.convertView = convertView;

    }

    public static UIViewHolder getHolder(Context context, View convertView, int layoutId) {
        if (convertView == null) {
            return new UIViewHolder(context, convertView, layoutId);
        } else {
            return (UIViewHolder) convertView.getTag();
        }
    }

    public View getConvertView() {
        return convertView;
    }

    public <T extends View> T getView(int viewId) {

        View view = (View) mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置文本
     *
     * @param viewId 根布局里面的Id
     * @param item   给该TextView设置的内容
     * @return ViewHolder  为了给调用者   可以用链式调用
     */
    public UIViewHolder setText(int viewId, String item) {
        // 减少findViewById的次数
        TextView tv = getView(viewId);
        tv.setText(item);
        return this;
    }


    /**
     * 设置资源图片
     *
     * @param viewId     根布局里面的Id
     * @param resourceId 给该ImageView设置的资源
     */
    public UIViewHolder setImageResource(int viewId, int resourceId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resourceId);
        return this;
    }


    /**
     * 设置网络图片
     *
     * @param viewId 根布局里面的Id
     * @param url    网络图片路径
     */
    public UIViewHolder setImageUrl(int viewId, String url) {
        ImageView iv = getView(viewId);
        // 加载网络图片
//        ImageLoader.loadImage(iv.getContext(),url,iv);
        return this;
    }

}
