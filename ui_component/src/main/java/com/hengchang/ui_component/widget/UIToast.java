package com.hengchang.ui_component.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * @author zhangzhilong
 * @date 2019/4/9.
 * description：
 */
public class UIToast {

    private static UIToast sToast;

    public static UIToast with(Context context) {
        if (sToast == null) {
            sToast = new UIToast(context);
        }
        return sToast;
    }

    public static void setToastNull() {
        sToast = null;
    }

    private Toast mToast;

    public UIToast(Context context) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
    }

    public void show(int resId) {
        mToast.setText(resId);
        mToast.show();
    }

    public void show(CharSequence charSequence) {
        mToast.setText(charSequence);
        mToast.show();
    }
}
