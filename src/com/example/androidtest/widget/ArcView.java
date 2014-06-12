//
//  ArcView.java
//  me.chunyu.widgets
//
//  Created by Eden He on 2013-11-7
//  Copyright (c) 2013 Chunyu.mobi
//  All rights reserved
//

package com.example.androidtest.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;

public class ArcView extends FrameLayout {

    private static final int[] gradientColors = {0xffff0000, 0xff00ff00, 0xff0000ff, 0xffff0000};
    private static final float[] gradientPositions = {0, 0.3333f, 0.6666f, 1};
    private float thickness;
    private float innerRadius;
    private float startAngle = 0.0f;
    private float sweepAngle = 0.0f;

    private Paint paint;
    private float cx;
    private float cy;

    public ArcView(Context context) {
        super(context);
        init();
    }

    public ArcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public ArcView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        thickness = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, getContext().getResources().getDisplayMetrics());
        setWillNotDraw(false);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cx = getWidth() / 2.0f;
        cy = getWidth() / 2.0f;
        innerRadius = cx - thickness;
        paint.setShader(new SweepGradient(cx, cy, gradientColors, gradientPositions));
        canvas.drawPath(buildRingPath(), paint);
    }

    private Path buildRingPath() {
        float endAngle = startAngle + sweepAngle;
        float pommelRadius = thickness / 2;
        float midRadius = innerRadius + pommelRadius;
        float startPommelX = cx + (float) Math.cos(Math.toRadians(startAngle)) * midRadius;
        float startPommelY = cy + (float) Math.sin(Math.toRadians(startAngle)) * midRadius;
        float endPommelX = cx + (float) Math.cos(Math.toRadians(endAngle)) * midRadius;
        float endPommelY = cy + (float) Math.sin(Math.toRadians(endAngle)) * midRadius;

        RectF innerBounds = new RectF(cx - innerRadius, cy - innerRadius, cx + innerRadius, cy + innerRadius);
        RectF outerBounds = new RectF(cx - innerRadius - thickness, cy - innerRadius - thickness,
                cx + innerRadius + thickness, cy + innerRadius + thickness);
        RectF startPommelBounds = new RectF(startPommelX - pommelRadius, startPommelY - pommelRadius,
                startPommelX + pommelRadius, startPommelY + pommelRadius);
        RectF endPommelBounds = new RectF(endPommelX - pommelRadius, endPommelY - pommelRadius,
                endPommelX + pommelRadius, endPommelY + pommelRadius);

        Path ringPath = new Path();
        ringPath.moveTo(cx + innerRadius, cy);
        ringPath.arcTo(startPommelBounds, startAngle + 180, 180, false); // start pommel
        ringPath.arcTo(outerBounds, startAngle, sweepAngle, false); // outer arc
        ringPath.arcTo(endPommelBounds, endAngle, 180, false); // end pommel
        ringPath.arcTo(innerBounds, endAngle, -sweepAngle, false); // inner arc
        ringPath.close();
        return ringPath;
    }
}
