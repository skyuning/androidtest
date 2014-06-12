package com.example.androidtest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidtest.widget.ScrollerLayout;

/**
 * Created by linyun on 14-1-2.
 */
public class ScrollerActivity extends Activity {

    private Button btn1;
    private ScrollerLayout contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        contentView = (ScrollerLayout) findViewById(R.id.scroller_content);
        btn1 = (Button) findViewById(R.id.scroller_btn_1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contentView.startScr();
            }
        });
    }
}
