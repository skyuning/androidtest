package com.example.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class ShortLinkActivity extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_link);
        mTextView = (TextView) findViewById(R.id.text);
        String uri = getIntent().getData().toString();
        mTextView.setText(uri);
    }
}

