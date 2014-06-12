package com.example.androidtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by linyun on 14-1-6.
 */
public class AddContentViewActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content_view);

        View view2 = getLayoutInflater().inflate(R.layout.activity_add_content_view2, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view2, params);
    }
}
