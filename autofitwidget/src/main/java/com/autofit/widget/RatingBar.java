package com.autofit.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;

public class RatingBar extends android.widget.RatingBar implements IAutoFit {

    private boolean mEnableAutoFit = true;

    public RatingBar(Context context) {
        super(context);
    }

    public RatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAutoView(context, attrs);
    }

    public RatingBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAutoView(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RatingBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAutoView(context, attrs);
    }

    private void initAutoView(Context context, AttributeSet attrs) {
        mEnableAutoFit = ScreenParameter.getEnableAutoFit(context, attrs);
        setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }


    @Override
    public boolean getEnabledAutoFit() {
        return mEnableAutoFit;
    }

    @Override
    public void setEnabledAutoFit(boolean autofit) {
        this.mEnableAutoFit = autofit;
    }

    private int padingCount = 0;

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (padingCount == 0) {
            super.setPadding(ScreenParameter.getFitSize(this, left), ScreenParameter.getFitHeight(this, top),
                    ScreenParameter.getFitSize(this, right), ScreenParameter.getFitHeight(this, bottom));
            padingCount++;
        } else {
            super.setPadding(left, top, right, bottom);
        }
    }

    private int layoutCount = 0;

    @Override
    public void setLayoutParams(android.view.ViewGroup.LayoutParams params) {
        if (layoutCount == 0) {
            super.setLayoutParams(ScreenParameter.getRealLayoutParams(this, params));
            layoutCount++;
        } else {
            super.setLayoutParams(params);
        }
    }

    @Override
    public void setMinimumHeight(int minHeight) {
        super.setMinimumHeight(ScreenParameter.getFitHeight(this, minHeight));
    }

    @Override
    public void setMinimumWidth(int minWidth) {
        super.setMinimumWidth(ScreenParameter.getFitSize(this, minWidth));
    }

}
