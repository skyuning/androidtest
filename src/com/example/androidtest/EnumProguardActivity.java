package com.example.androidtest;

import org.xframe.test.TestEnum.Xtype;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EnumProguardActivity extends Activity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enum_proguard);
        setTitle(String.valueOf(Xtype.A));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.enum_proguard, menu);
        return true;
    }

}
