package com.hengchang.ui_component.widget.banner;

import android.view.View;


/**
 * @author zhangzhilong
 * @date 2019/4/11.
 * description：Banner适配器
 */
public abstract class UIBannerAdapter {

    public abstract View getView(int position,View convertView);


    public abstract int getCount();


    public String getBannerDesc(int position){
        return "";
    }
}
