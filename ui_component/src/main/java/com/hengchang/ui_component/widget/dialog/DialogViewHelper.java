package com.hengchang.ui_component.widget.dialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengchang.ui_component.R;
import com.hengchang.ui_component.utils.UIResHelper;

import java.lang.ref.WeakReference;

/**
 * @author zhangzhilong
 * @date 2018/7/28
 * Description: Dialog View的辅助处理类
 */
class DialogViewHelper {

    private View mContentView = null;
    // 防止霸气侧漏
    private SparseArray<WeakReference<View>> mViews;

    public DialogViewHelper(Context context, int layoutResId) {
        this();
        mContentView = LayoutInflater.from(context).inflate(layoutResId, null);
        mContentView.setMinimumWidth(UIResHelper.getAttrDimen(context, R.attr.ui_dialog_min_width));

    }


    public DialogViewHelper() {
        mViews = new SparseArray<>();
    }

    /**
     * 设置布局View
     *
     * @param contentView
     */
    public void setContentView(View contentView) {
        this.mContentView = contentView;
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        // 每次都 findViewById   减少findViewById的次数
        TextView tv = getView(viewId);
        if (tv != null) {
            tv.setText(text);
        }
    }

    public <T extends View> T getView(int viewId) {
        WeakReference<View> viewReference = mViews.get(viewId);
        // 侧漏的问题  到时候到这个系统优化的时候再去介绍
        View view = null;
        if (viewReference != null) {
            view = viewReference.get();
        }

        if (view == null) {
            view = mContentView.findViewById(viewId);
            if (view != null) {
                mViews.put(viewId, new WeakReference<>(view));
            }
        }
        return (T) view;
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnclickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 获取ContentView
     */
    public View getContentView() {
        return mContentView;
    }
}
