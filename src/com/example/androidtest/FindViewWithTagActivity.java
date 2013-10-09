package com.example.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class FindViewWithTagActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_view_with_tag);
        
        View v = findViewById(android.R.id.content);
        TextView tv = (TextView) v.findViewById(R.id.tag_test);
        String tag = tv.getTag().toString();
        tv.setText("找到了 " + tag);
    }
}
