package com.example.androidtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by linyun on 14-3-21.
 */
public class BubbleTextView extends TextView {

    private int rectangelDimen = 30;
    private int strokeColor = Color.GREEN;
    private int backgroundColor = Color.TRANSPARENT;
    private Paint paint = new Paint();

    public BubbleTextView(Context context) {
        super(context);
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();
        setPadding(paddingLeft, paddingTop + rectangelDimen, paddingRight, paddingBottom);

        Drawable background = getBackground();
        if (background == null || !(background instanceof ColorDrawable))
            return;
        backgroundColor = ((ColorDrawable) background).getColor();
        setBackground(null);
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth();
        float height = getHeight();
        RectF rectF = new RectF(0, rectangelDimen - 2, width, height);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(strokeColor);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        canvas.drawPath(buildPath(), paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(backgroundColor);
        canvas.drawRoundRect(rectF, 10, 10, paint);
        canvas.drawPath(buildPath(), paint);
    }

    private Path buildPath() {
        Path path = new Path();
        float cx = getWidth() / 2;
        path.moveTo(cx, 0);
        path.lineTo(cx + rectangelDimen, rectangelDimen);
        path.lineTo(cx - rectangelDimen, rectangelDimen);
        path.close();
        return path;
    }
}

