package com.example.androidtest;

import com.tencent.tauth.Tencent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class QzoneShareActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qzone_share);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qzone_share, menu);
        return true;
    }

}
