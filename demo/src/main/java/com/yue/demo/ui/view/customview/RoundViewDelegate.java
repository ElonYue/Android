package com.yue.demo.ui.view.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yue.demo.R;

/**
 * @author chengyue
 * @description
 * @date 2021/10/19 16:17
 * @modify by chengyue on 2021/10/19 16:17
 */
public class RoundViewDelegate {

    /**
     * 圆形
     */
    public static final int CIRCLE = -1;
    private RectF roundRect = new RectF();

    private Paint maskPaint = new Paint();
    private Paint zonePaint = new Paint();
    private Paint strokePaint = new Paint();

    private float radius;
    private float mStrokeWidth;
    private int strokeColor = Color.WHITE;

    private View view;
    private Context context;

    public RoundViewDelegate(View view, Context context, AttributeSet attrs) {
        this.view = view;
        this.context = context;
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundLayoutAttr);
            radius = ta.getDimensionPixelSize(R.styleable.RoundLayoutAttr_round_corner, RoundViewDelegate.CIRCLE);
            strokeColor = ta.getColor(R.styleable.RoundLayoutAttr_stroke_color, Color.WHITE);
            mStrokeWidth = ta.getDimensionPixelSize(R.styleable.RoundLayoutAttr_stroke_width, 0);
            ta.recycle();
        }
        init();
    }

    private void init() {
        maskPaint.setAntiAlias(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        maskPaint.setStyle(Paint.Style.FILL);

        zonePaint.setAntiAlias(true);
        zonePaint.setColor(Color.WHITE);
        view.setBackgroundColor(Color.TRANSPARENT);

        strokePaint.setAntiAlias(true);
        strokePaint.setColor(strokeColor);
        strokePaint.setStrokeWidth(mStrokeWidth);
        strokePaint.setStyle(Paint.Style.STROKE);
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public void setStrokeWidth(float mStrokeWidth) {
        this.mStrokeWidth = mStrokeWidth;
        strokePaint.setStrokeWidth(mStrokeWidth);
    }

    public void setStrokeColor(int color) {
        this.strokePaint.setColor(color);
    }

    public void roundRectSet(int width, int height) {
        if (radius == RoundViewDelegate.CIRCLE) {
            this.radius = width / 2.0f;
        }
        roundRect.set(0, 0, width, height);
    }

    public void canvasSetLayer(Canvas canvas) {
        canvas.saveLayer(roundRect, zonePaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawRoundRect(roundRect, radius, radius, zonePaint);
        canvas.saveLayer(roundRect, maskPaint, Canvas.ALL_SAVE_FLAG);
    }

    public void canvasStrokeWidth(Canvas canvas) {
        if (mStrokeWidth > 0) {
            RectF strokeRect = new RectF();
            strokeRect.set(roundRect.left + mStrokeWidth / 2, roundRect.top + mStrokeWidth / 2,
                    roundRect.right - mStrokeWidth / 2, roundRect.bottom - mStrokeWidth / 2);
            canvas.drawRoundRect(strokeRect, radius - mStrokeWidth / 2, radius - mStrokeWidth / 2, strokePaint);
        }
    }
}
