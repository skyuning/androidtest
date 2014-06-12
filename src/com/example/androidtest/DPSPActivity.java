package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation.ViewInject;

import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class DPSPActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpsp);
    }
}