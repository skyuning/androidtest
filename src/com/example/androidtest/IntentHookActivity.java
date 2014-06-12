package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by linyun on 14-6-3.
 */

public class IntentHookActivity extends Activity {

    private TextView mTextView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_hook);
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText(getIntent().getAction());
    }
}