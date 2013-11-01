package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import com.example.androidtest.widget.RadioGroupEx;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RadioGroupExActivity extends Activity {

    @ViewInject(id = R.id.radioGroup1)
    RadioGroupEx radioGroupEx;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group_ex);
        ViewAnnotation.bind(this, this);
        
        radioGroupEx.check(R.id.radio2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.radio_group_ex, menu);
        return true;
    }

}
