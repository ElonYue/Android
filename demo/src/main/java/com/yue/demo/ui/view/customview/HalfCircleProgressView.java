package com.yue.demo.ui.view.customview;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author chengyue
 * @description
 * @date 2021/10/15 21:54
 * @modify by chengyue on 2021/10/15 21:54
 */
public class HalfCircleProgressView extends View {

    Paint mPaint;
    Paint paintDegree;
    //颜色以及宽度
    private int width;
    private int height;
    private int radius;
    /**
     * 每档刻度线数目
     */
    private final int SCALE_COUNT = 4;
    private final int STROKE_WIDTH = 60;
    private final int STROKE_DEGREE_WIDTH = 10;
    /**
     * 最大档位
     */
    private int mMaxGear;
    /**
     * 当前档位最大刻度数
     */
    private int mMaxScale;
    /**
     * 当前刻度
     */
    private int currentScale;
    private boolean isRepeat;
    private ValueAnimator animator;

    public HalfCircleProgressView(Context context) {
        this(context, null);
    }

    public HalfCircleProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HalfCircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);//绘图为描边模式
        mPaint.setStrokeWidth(STROKE_WIDTH);//画笔宽度
        mPaint.setAntiAlias(true);//抗锯齿
        mPaint.setColor(Color.parseColor("#FFFFFF"));

        paintDegree = new Paint();
        paintDegree.setStyle(Paint.Style.STROKE);//绘图为描边模式
        paintDegree.setStrokeWidth(STROKE_DEGREE_WIDTH);//画笔宽度
        paintDegree.setAntiAlias(true);//抗锯齿
        paintDegree.setColor(Color.parseColor("#0000FF"));
        Shader mShader = new LinearGradient(0, 0, 0, STROKE_WIDTH, new int[]{0xFFFF0000, 0xFF00FF00}, null,
                Shader.TileMode.REPEAT);
        paintDegree.setShader(mShader);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (animator != null) {
            animator.start();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator != null) {
            animator.cancel();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        width = getWidth();
        height = getHeight();
        radius = (width - STROKE_WIDTH) / 2;

        // 画圆盘
        canvas.drawCircle(width / 2, height / 2, radius, mPaint);
        drawDegree(canvas);
    }

    /**
     * 画刻度
     */
    protected void drawDegree(Canvas canvas) {
        for (int i = 0; i < currentScale; i++) {
            float startX = width / 2.0f;
            canvas.save();
            float degress = (240.0f / (mMaxGear * SCALE_COUNT));
            canvas.rotate(240 + degress / 2 + degress * i, width / 2.0f, height / 2.0f);
            canvas.drawLine(startX, STROKE_WIDTH * 1.0f, startX, 0, paintDegree);
            canvas.restore();
        }
    }

    private void startAnimator(int max) {
        int start = currentScale;
        int end = max;
        if (animator == null) {
            animator = ValueAnimator.ofInt(start, end);
            animator.setInterpolator(new LinearInterpolator());
            animator.setRepeatMode(ValueAnimator.RESTART);
            animator.addUpdateListener(animation -> {
                Log.d("+++++", (int) animation.getAnimatedValue() + "");
                currentScale = (int) animation.getAnimatedValue();
                postInvalidate();
            });

            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("+++++", "end");
                    postInvalidate();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            animator.setIntValues(start, end);
        }
        if (isRepeat) {
            animator.setRepeatCount(ValueAnimator.INFINITE);
        }
        animator.setDuration(Math.abs((end - start)) * 100);
        animator.start();
    }

    /**
     * 最大档位
     *
     * @param max
     */
    public void setMax(int max) {
        mMaxGear = Math.max(1, max);
    }

    /**
     * 初始设置档位
     *
     * @param gear
     */
    public void setGear(int gear) {
        mMaxScale = gear * SCALE_COUNT;
//        if (gear == 0) {
//            currentProgress = 0;
//        } else {
//            currentProgress = 1;
//        }
        startAnimator(mMaxScale);
    }

    public void setValue(int value) {
        postInvalidate();
    }

    public void setRepeat(boolean isRepeat) {
        this.isRepeat = isRepeat;
    }
}
