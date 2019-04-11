package com.hengchang.ui_component.widget.banner;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * @author zhangzhilong
 * @date 2019/4/11.
 * description：改变ViewPager切换的速率
 */
public class UIBannerScroller extends Scroller{

    private int mScrollerDuration = 950;

    public void setScrollerDuration(int scrollerDuration){
        this.mScrollerDuration = scrollerDuration;
    }

    public UIBannerScroller(Context context) {
        super(context);
    }

    public UIBannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public UIBannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy,int duration) {
        super.startScroll(startX, startY, dx, dy,mScrollerDuration);
    }
}
