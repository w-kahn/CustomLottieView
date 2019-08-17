package com.wang.kahn.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.LottieListener;

import org.jetbrains.annotations.NotNull;

public class CustomLottieView extends View {

    private LottieDrawable mLottieDrawable;

    private ColorDrawable mColorDrawable;

    private Rect mBounds = new Rect(200, 200, 600, 600);

    private Paint mPaint = new Paint();

    public CustomLottieView(Context context) {
        super(context);
        mColorDrawable = new ColorDrawable(Color.GREEN);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
    }

    public CustomLottieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mColorDrawable = new ColorDrawable(Color.GREEN);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

    }

    public CustomLottieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mColorDrawable = new ColorDrawable(Color.GREEN);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

    }

    private void init(final Drawable.Callback callback) {
        LottieCompositionFactory.fromAsset(getContext(),"B.json").addListener(new LottieListener<LottieComposition>() {
            @Override
            public void onResult(LottieComposition result) {
                mLottieDrawable = new LottieDrawable();

                mLottieDrawable.setComposition(result);
                mLottieDrawable.setCallback(callback);
                mLottieDrawable.setRepeatCount(LottieDrawable.INFINITE);
                mLottieDrawable.setScale(0.7f);
                mLottieDrawable.start();
            }
        });
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


        mColorDrawable.setBounds(mBounds);
        mColorDrawable.draw(canvas);


        if (mLottieDrawable != null) {
            canvas.save();

            int lottieWidth = mLottieDrawable.getBounds().width();
            int lottieHeight = mLottieDrawable.getBounds().height();

            int offsetX = mBounds.left + (mBounds.width() - lottieWidth) / 2;
            int offsetY = mBounds.top + (mBounds.height() - lottieHeight) / 2;

            canvas.translate(offsetX, offsetY);
            mLottieDrawable.draw(canvas);

            canvas.drawRect(mLottieDrawable.getBounds(), mPaint);
            canvas.restore();
        }

    }

    public void loadAnim() {
        init(this);
    }

    @Override
    protected boolean verifyDrawable(@NotNull Drawable who) {
        return who == mLottieDrawable || super.verifyDrawable(who);
    }
}
