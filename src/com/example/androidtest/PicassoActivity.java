package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.app.Activity;

public class PicassoActivity extends Activity {

    @ViewInject(id = R.id.picasso)
    private ImageView mPicassovView;

    @ViewInject(id = R.id.local)
    private ImageView mLocalView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picasso);
        ViewAnnotation.bind(this, this);
        
        String imgUrl = "http://pic3.nipic.com/20090715/533469_150722031_2.jpg";
        
        Picasso.with(this)
        .load(imgUrl)
        .placeholder(R.drawable.loading_circle)
        .error(android.R.drawable.alert_dark_frame)
        .into(mPicassovView);
        
        Picasso.with(this)
        .load(R.drawable.loading_flower)
        .into(mLocalView);
        
        Picasso.with(this).setDebugging(true);
    }
}