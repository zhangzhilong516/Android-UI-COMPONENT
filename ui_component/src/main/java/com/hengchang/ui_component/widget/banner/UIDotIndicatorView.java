package com.hengchang.ui_component.widget.banner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author zhangzhilong
 * @date 2019/4/11.
 * description：圆点指示器
 */
public class UIDotIndicatorView extends View {

    private Drawable drawable;

    public UIDotIndicatorView(Context context) {
        this(context, null);
    }

    public UIDotIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIDotIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(drawable != null){

            Bitmap bitmap = drawableToBitmap(drawable);

            Bitmap circleBitmap = getCircleBitmap(bitmap);

            canvas.drawBitmap(circleBitmap,0,0,null);
        }
    }

    /**
     * 获取圆形bitmap
     */
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap circleBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(circleBitmap);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);

        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, getMeasuredWidth() / 2, paint);

        // 取圆和Bitmap矩形的交集
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        canvas.drawBitmap(bitmap,0,0,paint);

        bitmap.recycle();

        return circleBitmap;
    }

    /**
     * 从drawable中得到Bitmap
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if(drawable instanceof BitmapDrawable){
            return((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap outBitmap = Bitmap.createBitmap(getMeasuredWidth(),getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);

        drawable.setBounds(0,0,getMeasuredWidth(),getMeasuredHeight());
        drawable.draw(canvas);

        return outBitmap;
    }

    /**
     * 设置Drawable
     */
    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
        invalidate();
    }
}
