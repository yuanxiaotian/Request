package com.cangmaomao.network.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ProgressBar;

import retrofit2.http.PUT;

public class CMMProgressBar extends ProgressBar {

    private String str;
    private Paint mPaint;

    public CMMProgressBar(Context context) {
        super(context);
        initPaint();
    }

    public CMMProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public CMMProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect();
        this.mPaint.getTextBounds(str, 0, str.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(str, x, y, mPaint);
    }


    private void initPaint() {
        this.mPaint = new Paint();
        this.mPaint.setColor(Color.RED);
        this.mPaint.setTextSize(50F);
    }

    public void setTextColor(int color) {
        this.mPaint.setColor(color);
    }


    @Override
    public synchronized void setProgress(int progress) {
        setText();
        super.setProgress(progress);
    }

    private void setText() {
        int p = (getProgress() * 100) / getMax();

        Log.e("p", "p:" + p);
        this.str = p + "%";
    }
}
