package com.example.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class TabHostActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_host);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab_host, menu);
        return true;
    }

    private TabHost tabHost;
    
    private void InitUI() {

        // get Resource R.string
        // set Message Number

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();

        tabHost.addTab(tabHost.newTabSpec("main_home").setIndicator("main_home")
                .setContent(R.id.HomeFragment));

        tabHost.setCurrentTab(0);

        InitClickListener();
    }

    private void InitClickListener() {

        RadioGroup radioGroup = (RadioGroup) this.findViewById(R.id.main_tab);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                case R.id.main_tab_home:
                    tabHost.setCurrentTabByTag("main_home");
                    break;
                default:
                    tabHost.setCurrentTabByTag("main_home");
                }
            }
        });
    }
}
