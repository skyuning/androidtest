package com.example.androidtest.widget;

import android.app.Activity;
import android.os.Bundle;

import com.example.androidtest.R;

/**
 * Created by linyun on 14-2-12.
 */
public class MyViewActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view);
//        ArcView arcView = (ArcView) findViewById(R.id.arc_view);
    }
}