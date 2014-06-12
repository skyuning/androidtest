package com.example.androidtest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.ViewAnimator;

/**
 * Created by linyun on 14-5-5.
 */
public class ReflectOn23xActivity extends FragmentActivity {

    private TextView mTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reflect_on_23x);

        mTextView = (TextView) findViewById(R.id.text);

        int a = getIntent().getIntExtra("abc", 0);
        if (a > 0)
            showAnimation();
        getClass().getDeclaredMethods();
    }

    private void showAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 10);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            }
        });
    }
}

