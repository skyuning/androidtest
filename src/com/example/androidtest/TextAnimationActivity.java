package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class TextAnimationActivity extends Activity {
    
    @ViewInject(id = R.id.text)
    private View mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_animation);
        ViewAnnotation.bind(this, this);
        
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bubble_comment_star);
        mText.startAnimation(animation);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.text_animation, menu);
        return true;
    }

}
