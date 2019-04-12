package com.hengchang.ui_component.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hengchang.ui_component.R;
import com.hengchang.ui_component.utils.UIDisplayHelper;
import com.hengchang.ui_component.utils.UIResHelper;

/**
 * @author zhangzhilong
 * @date 2018/7/28
 * Description:
 */
class AlertController {

    private UIAlertDialog mDialog;
    private Window mWindow;

    private DialogViewHelper mViewHelper;

    public AlertController(UIAlertDialog dialog, Window window) {
        this.mDialog = dialog;
        this.mWindow = window;
    }

    public void setViewHelper(DialogViewHelper viewHelper) {
        this.mViewHelper = viewHelper;
    }

    /**
     * 设置文本
     */
    public void setText(int viewId, CharSequence text) {
        mViewHelper.setText(viewId, text);
    }

    public <T extends View> T getView(int viewId) {
        return mViewHelper.getView(viewId);
    }

    /**
     * 设置点击事件
     */
    public void setOnclickListener(int viewId, final DialogInterface.OnClickListener listener) {
        mViewHelper.setOnclickListener(viewId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(mDialog,v.getId());
            }
        });
    }

    /**
     * 获取Dialog
     */
    public UIAlertDialog getDialog() {
        return mDialog;
    }

    /**
     * 获取Dialog的Window
     *
     * @return
     */
    public Window getWindow() {
        return mWindow;
    }

    public static class AlertParams {
        public Context mContext;
        public int mThemeResId;
        // 点击空白是否能够取消  默认点击阴影可以取消
        public boolean mCancelable = true;
        // dialog Cancel监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        // dialog Dismiss监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        // dialog Key监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        // 布局View
        public View mView;
        // 布局layout id
        public int mViewLayoutResId;

        // 存放字体的修改
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        // 存放点击事件
        public SparseArray<DialogInterface.OnClickListener> mClickArray = new SparseArray<>();
        // 宽度
        public int mWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 动画
        public int mAnimations = 0;
        // 位置
        public int mGravity = Gravity.CENTER;
        // 高度
        public int mHeight = ViewGroup.LayoutParams.WRAP_CONTENT;

        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.mThemeResId = themeResId;
        }

        /**
         * 绑定和设置参数
         */
        public void apply(AlertController mAlert) {

            DialogViewHelper viewHelper = null;

            if (mViewLayoutResId != 0) {
                viewHelper = new DialogViewHelper(mContext, mViewLayoutResId);
            }

            if (mView != null) {
                viewHelper = new DialogViewHelper();
                viewHelper.setContentView(mView);
            }


            if (viewHelper == null) {
                throw new IllegalArgumentException("please setContentView()");
            }
            mAlert.getDialog().setContentView(viewHelper.getContentView());

            mAlert.setViewHelper(viewHelper);


            int textArraySize = mTextArray.size();
            for (int i = 0; i < textArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }


            int clickArraySize = mClickArray.size();
            for (int i = 0; i < clickArraySize; i++) {
                mAlert.setOnclickListener(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            Window window = mAlert.getWindow();
            window.setGravity(mGravity);

            if (mAnimations != 0) {
                window.setWindowAnimations(mAnimations);
            }
            WindowManager.LayoutParams params = window.getAttributes();

            params.width = mWidth;
            params.height = mHeight;
            window.setAttributes(params);
        }
    }
}
