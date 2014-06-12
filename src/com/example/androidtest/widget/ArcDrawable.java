package com.example.androidtest.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;

import com.example.androidtest.R;

/**
 * Created by linyun on 14-2-13.
 */
public class ArcDrawable extends ShapeDrawable {

    private Bitmap bitmap;
    private BitmapShader shader;
    private Paint paint;
    private int width;
    private int height;

    public ArcDrawable(Context context) {
        super();
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.loading_circle);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        paint = new Paint();
        paint.setAntiAlias(true);

        paint.setShader(shader);
        RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        int startAngle = 50;
        int sweepAngle = 180;
        canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);

        float centerX = width / 2;
        float centerY = (float) (height / 2 - 0.5);
        float radius = width / 2 - 45;
        double startRadians = Math.toRadians(startAngle);
        double endRadians = Math.toRadians(startAngle + sweepAngle);
        float startX = centerX + (float) Math.cos(startRadians) * radius;
        float startY = centerY + (float) Math.sin(startRadians) * radius;
        float endX = centerX + (float) Math.cos(endRadians) * radius;
        float endY = centerY + (float) Math.sin(endRadians) * radius;
        canvas.drawCircle(startX, startY, (float) 5.5, paint);
        canvas.drawCircle(endX, endY, (float) 5.5, paint);
    }

    @Override
    public int getIntrinsicWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return bitmap.getHeight();
    }
}
