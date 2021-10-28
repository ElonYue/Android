package com.yue.demo.ui.view.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author chengyue
 * @description
 * @date 2021/10/19 16:17
 * @modify by chengyue on 2021/10/19 16:17
 */
public class RoundRelativeLayout extends RelativeLayout {

    RoundViewDelegate delegate;

    public RoundRelativeLayout(Context context) {
        this(context, null);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (delegate == null) {
            delegate = new RoundViewDelegate(this, context, attrs);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
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
