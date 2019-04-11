/*
 * Tencent is pleased to support the open source community by making QMUI_Android available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the MIT License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * http://opensource.org/licenses/MIT
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hengchang.ui_component.widget.round;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.hengchang.ui_component.R;
import com.hengchang.ui_component.utils.UIViewHelper;

/**
 * 见 {@link UIRoundButton} 与 {@link UIRoundButtonDrawable}
 */
public class UIRoundLinearLayout extends LinearLayout {

    public UIRoundLinearLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public UIRoundLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, R.attr.UIRoundButtonStyle);
    }

    public UIRoundLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        UIRoundButtonDrawable bg = UIRoundButtonDrawable.fromAttributeSet(context, attrs,R.styleable.UIRoundLinearLayout, 0);
        UIViewHelper.setBackgroundKeepingPadding(this, bg);
    }
}
