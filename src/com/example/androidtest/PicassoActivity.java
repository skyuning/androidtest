package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.widget.ImageView;
import android.app.Activity;

public class PicassoActivity extends Activity {

    @ViewInject(id = R.id.picasso)
    private ImageView mPicassovView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ViewAnnotation.bind(this, this);

        mPicassovView.setBackgroundColor(0x000);
        Picasso.with(this)
                .load("https://www.google.com.hk/images/nav_logo166_hr.png")
                .resize(200, 200)
                .centerCrop()
                .into(mPicassovView);
    }
}