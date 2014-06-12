package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import com.squareup.picasso.Picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.app.Activity;
import android.widget.TextView;

public class PicassoActivity extends Activity {

    @ViewInject(id = R.id.textView1)
    private TextView textView;

    @ViewInject(id = R.id.picasso)
    private ImageView mPicassovView;

    @ViewInject(id = R.id.local)
    private ImageView mLocalView;

    @ViewInject(id = R.id.thumb)
    private ImageView mThumbView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ViewAnnotation.bind(this, this);



        BitmapDrawable bitmapDrawable = (BitmapDrawable) getResources().getDrawable(R.drawable.psb);
        Bitmap bitmap = bitmapDrawable.getBitmap();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int w = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, metrics);
        int h = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, metrics);
        Bitmap thumb = Bitmap.createScaledBitmap(bitmap, w, h, true);
        mThumbView.setImageBitmap(thumb);
//
//        String imgUrl = "http://pic3.nipic.com/20090715/533469_150722031_2.jpg";
//
//        Picasso.with(this)
//        .load(imgUrl)
//        .placeholder(R.drawable.loading_circle)
//        .error(android.R.drawable.alert_dark_frame)
//        .into(mPicassovView);
//
//        Picasso.with(this)
//        .load(R.drawable.loading_flower)
//        .into(mLocalView);
//
//        Picasso.with(this).setDebugging(true);
    }
}