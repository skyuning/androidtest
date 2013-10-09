package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class IntentDemoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Uri uri = Uri.parse("http://www.baidu.com");
        Uri uri = Uri.parse("https://dev.chunyu.mobi/reviews/static/rb/images/logo.png");
//        Uri uri = Uri.parse("tel:123");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
