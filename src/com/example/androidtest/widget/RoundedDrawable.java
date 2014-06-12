package com.example.androidtest.widget;

/**
 * Created by linyun on 14-2-13.
 */

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class RoundedDrawable extends Drawable {

    public static final int DEFAULT_BORDER_COLOR = Color.BLACK;

    private final RectF mBounds = new RectF();
    private final RectF mDrawableRect = new RectF();

    private final RectF mBitmapRect = new RectF();
    private final BitmapShader mBitmapShader;
    private final Paint mBitmapPaint;
    private final int mBitmapWidth;
    private final int mBitmapHeight;

    private final RectF mBorderRect = new RectF();
    private final Paint mBorderPaint;
    private final Matrix mShaderMatrix = new Matrix();


    private float mCornerRadius = 0;
    private float mBorderWidth = 10;

    public RoundedDrawable(Bitmap bitmap) {

        mBitmapWidth = bitmap.getWidth();
        mBitmapHeight = bitmap.getHeight();
        mBitmapRect.set(0, 0, mBitmapWidth, mBitmapHeight);

        mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        mBitmapShader.setLocalMatrix(mShaderMatrix);

        mBitmapPaint = new Paint();
        mBitmapPaint.setStyle(Paint.Style.FILL);
        mBitmapPaint.setAntiAlias(true);
        mBitmapPaint.setShader(mBitmapShader);

        mBorderPaint = new Paint();
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStrokeWidth(mBorderWidth);
    }

    public static Drawable fromBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            return new RoundedDrawable(bitmap);
        } else {
            return null;
        }
    }

    public static Drawable fromDrawable(Drawable drawable) {
        if (drawable != null) {
            if (drawable instanceof RoundedDrawable) {
                // just return if it's already a RoundedDrawable
                return drawable;
            } else if (drawable instanceof ColorDrawable) {
                // FIXME we don't support ColorDrawables yet
                return drawable;
            } else if (drawable instanceof LayerDrawable) {
                LayerDrawable ld = (LayerDrawable) drawable;
                int num = ld.getNumberOfLayers();

                // loop through layers to and change to RoundedDrawables if possible
                for (int i = 0; i < num; i++) {
                    Drawable d = ld.getDrawable(i);
                    ld.setDrawableByLayerId(ld.getId(i), fromDrawable(d));
                }
                return ld;
            }

            // try to get a bitmap from the drawable and
            Bitmap bm = drawableToBitmap(drawable);
            if (bm != null) {
                return new RoundedDrawable(bm);
            } else {
            }
        }
        return drawable;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap;
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        if (width > 0 && height > 0) {
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        } else {
            bitmap = null;
        }

        return bitmap;
    }

    private void updateShaderMatrix() {
        mBorderRect.set(mBounds);
        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);

        mBorderRect.set(mBounds);
        mDrawableRect.set(mBorderWidth, mBorderWidth, mBorderRect.width() - mBorderWidth, mBorderRect.height() - mBorderWidth);

        mShaderMatrix.set(null);
        mShaderMatrix.setTranslate((int) ((mDrawableRect.width() - mBitmapWidth) * 0.5f + 0.5f), (int) ((mDrawableRect.height() - mBitmapHeight) * 0.5f + 0.5f));

        mBorderRect.inset(mBorderWidth / 2, mBorderWidth / 2);

        mBitmapShader.setLocalMatrix(mShaderMatrix);
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);

        mBounds.set(bounds);

        updateShaderMatrix();
    }

    @Override
    public void draw(Canvas canvas) {
        if (mBorderWidth > 0) {
            canvas.drawRoundRect(mBorderRect, mCornerRadius, mCornerRadius, mBorderPaint);
            canvas.drawRoundRect(mDrawableRect, Math.max(mCornerRadius - mBorderWidth, 0), Math.max(mCornerRadius - mBorderWidth, 0), mBitmapPaint);
        } else {
            canvas.drawRoundRect(mDrawableRect, mCornerRadius, mCornerRadius, mBitmapPaint);
        }
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void setAlpha(int alpha) {
        mBitmapPaint.setAlpha(alpha);
        invalidateSelf();
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mBitmapPaint.setColorFilter(cf);
        invalidateSelf();
    }

    @Override
    public int getIntrinsicWidth() {
        return mBitmapWidth;
    }

    @Override
    public int getIntrinsicHeight() {
        return mBitmapHeight;
    }
}
