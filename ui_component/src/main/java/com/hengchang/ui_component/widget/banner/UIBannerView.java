package com.hengchang.ui_component.widget.banner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hengchang.ui_component.R;

import androidx.viewpager.widget.ViewPager;

/**
 * @author zhangzhilong
 * @date 2019/4/11.
 * description：BannerView
 */
public class UIBannerView extends RelativeLayout{
    private static final String TAG = "BannerView";

    private UIBannerViewPager mBannerVp;
    private TextView mBannerDescTv;
    private LinearLayout mDotContainerView;
    private UIBannerAdapter mAdapter;

    private Context mContext;

    private Drawable mIndicatorFocusDrawable;
    private Drawable mIndicatorNormalDrawable;

    private int mCurrentPosition = 0;

    private int mDotGravity = 1;
    private int mDotSize = 8;
    private int mDotDistance = 8;
    private View mBannerBv;
    private int mBottomColor = Color.TRANSPARENT;
    private float mWidthProportion,mHeightProportion;

    public UIBannerView(Context context) {
        this(context, null);
    }

    public UIBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UIBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        inflate(context, R.layout.ui_banner_layout, this);

        initAttribute(attrs);

        initView();
    }

    /**
     * 初始化自定义属性
     */
    private void initAttribute(AttributeSet attrs) {
        TypedArray array = mContext.obtainStyledAttributes(attrs, R.styleable.UIBannerView);

        mDotGravity = array.getInt(R.styleable.UIBannerView_ui_dotGravity, mDotGravity);
        mIndicatorFocusDrawable = array.getDrawable(R.styleable.UIBannerView_ui_dotIndicatorFocus);
        if(mIndicatorFocusDrawable == null){
            mIndicatorFocusDrawable = new ColorDrawable(Color.RED);
        }
        mIndicatorNormalDrawable = array.getDrawable(R.styleable.UIBannerView_ui_dotIndicatorNormal);
        if(mIndicatorNormalDrawable == null){
            mIndicatorNormalDrawable = new ColorDrawable(Color.WHITE);
        }
        mDotSize = (int) array.getDimension(R.styleable.UIBannerView_ui_dotSize,dip2px(mDotSize));
        mDotDistance = (int) array.getDimension(R.styleable.UIBannerView_ui_dotDistance,dip2px(mDotDistance));

        mBottomColor = array.getColor(R.styleable.UIBannerView_ui_bottomColor,mBottomColor);

        mWidthProportion = array.getFloat(R.styleable.UIBannerView_ui_withProportion,mWidthProportion);
        mHeightProportion = array.getFloat(R.styleable.UIBannerView_ui_heightProportion,mHeightProportion);
        array.recycle();
    }

    /**
     * 初始化View
     */
    private void initView() {
        mBannerVp = findViewById(R.id.banner_vp);
        mBannerDescTv =  findViewById(R.id.banner_desc_tv);
        mDotContainerView =  findViewById(R.id.dot_container);
        mBannerBv = findViewById(R.id.banner_bottom_view);
        mBannerBv.setBackgroundColor(mBottomColor);
    }

    /**
     * 4.设置适配器
     */
    public void setAdapter(UIBannerAdapter adapter){
        mAdapter = adapter;
        mBannerVp.setAdapter(adapter);
        initDotIndicator();

        mBannerVp.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                pageSelect(position);
            }
        });

        String firstDesc = mAdapter.getBannerDesc(0);
        mBannerDescTv.setText(firstDesc);

        if(mHeightProportion == 0 || mWidthProportion == 0){
            return;
        }
        post(new Runnable() {
            @Override
            public void run() {
                int width = getMeasuredWidth();
                int height = (int) (width * mHeightProportion / mWidthProportion);
                getLayoutParams().height = height;
                mBannerVp.getLayoutParams().height = height;
            }
        });
    }

    /**
     * 页面切换的回调
     */
    private void pageSelect(int position) {
        UIDotIndicatorView oldIndicatorView = (UIDotIndicatorView)
                mDotContainerView.getChildAt(mCurrentPosition);
        oldIndicatorView.setDrawable(mIndicatorNormalDrawable);


        mCurrentPosition = position%mAdapter.getCount();
        UIDotIndicatorView currentIndicatorView = (UIDotIndicatorView)
                mDotContainerView.getChildAt(mCurrentPosition);
        currentIndicatorView.setDrawable(mIndicatorFocusDrawable);

        String bannerDesc = mAdapter.getBannerDesc(mCurrentPosition);
        mBannerDescTv.setText(bannerDesc);
    }

    /**
     * 初始化点的指示器
     */
    private void initDotIndicator() {
        int count = mAdapter.getCount();

        mDotContainerView.setGravity(getDotGravity());

        for (int i = 0;i<count;i++){
            UIDotIndicatorView indicatorView = new UIDotIndicatorView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mDotSize,mDotSize);
            params.leftMargin = mDotDistance;
            indicatorView.setLayoutParams(params);

            if(i == 0) {
                indicatorView.setDrawable(mIndicatorFocusDrawable);
            }else{
                indicatorView.setDrawable(mIndicatorNormalDrawable);
            }
            mDotContainerView.addView(indicatorView);
        }
    }

    /**
     * 把dip转成px
     */
    private int dip2px(int dip) {
        return (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dip,getResources().getDisplayMetrics());
    }

    /**
     * 开始滚动
     */
    public void startRoll() {
        mBannerVp.startRoll();
    }

    /**
     * 获取点的位置
     */
    public int getDotGravity() {
        switch (mDotGravity){
            case 0:
                return Gravity.CENTER;
            case -1:
                return Gravity.LEFT;
            case 1:
                return Gravity.RIGHT;
        }
        return Gravity.LEFT;
    }

    /**
     * 设置点击回调监听
     */
    public void setOnBannerItemClickListener(UIBannerViewPager.BannerItemClickListener listener){
        mBannerVp.setOnBannerItemClickListener(listener);
    }
}
