package com.example.androidtest.widget;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.GradientDrawable;

/**
 * Created by linyun on 14-2-14.
 */
public class RingDrawable extends GradientDrawable {

    private float width = 500.0f;
    private float height = 500.0f;
    private float innerRadius = 200.0f;
    private float thickness = 50.0f;
    private float startAngle = 0.0f;
    private float sweepAngle = 320.0f;

    private Paint paint;
    private Shader shader;
    private float cx;
    private float cy;

    public RingDrawable() {
        cx = getIntrinsicWidth() / 2.0f;
        cy = getIntrinsicHeight() / 2.0f;
        paint = new Paint();
        shader = new SweepGradient(cx, cy, new int[]{0xffff0000, 0xff00ff00, 0xff0000ff, 0xffff0000}, new float[]{0, 0.3333f, 0.6666f, 1});
        paint.setShader(shader);
        paint.setAntiAlias(true);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
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

    public void startAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 360.0f, 120.0f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float curSweepAngle = (Float) animation.getAnimatedValue();
                sweepAngle = curSweepAngle;
                invalidateSelf();
            }
        });
        animator.setDuration(1000);
        animator.start();
    }

    @Override
    public int getIntrinsicWidth() {
        return (int) width;
    }

    @Override
    public int getIntrinsicHeight() {
        return (int) height;
    }
}
