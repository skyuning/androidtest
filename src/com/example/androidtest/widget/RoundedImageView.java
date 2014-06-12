package com.example.androidtest.widget;

/**
 * Created by linyun on 14-2-13.
 */

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.androidtest.R;

public class RoundedImageView extends ImageView {

    public RoundedImageView(Context context) {
        super(context);
    }

    public RoundedImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        Drawable drawable = getResources().getDrawable(R.drawable.loading_flower);
        Drawable mDrawable = RoundedDrawable.fromDrawable(drawable);
        setImageDrawable(mDrawable);
    }

}
