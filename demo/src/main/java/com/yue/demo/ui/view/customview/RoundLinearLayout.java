package com.yue.demo.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @WYU-WIN
 * @date 2020/8/11 0011.
 * description：
 */
public class RoundLinearLayout extends LinearLayout {
    private RoundViewDelegate delegate;

    public RoundLinearLayout(Context context) {
        this(context, null);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (delegate == null) {
            delegate = new RoundViewDelegate(this, context, attrs);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        int width = getWidth();
        int height = getHeight();
        delegate.roundRectSet(width, height);
    }

    @Override
    public void draw(Canvas canvas) {
        if (delegate.getRadius() > 0) {
            delegate.canvasSetLayer(canvas);
            super.draw(canvas);
            canvas.restore();
            delegate.canvasStrokeWidth(canvas);
        } else {
            super.draw(canvas);
        }
    }

    public void setRadius(float radius) {
        delegate.setRadius(radius);
        invalidate();
    }
}
