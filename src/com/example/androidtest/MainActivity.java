package com.example.androidtest;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.entity.mime.MultipartEntity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    
    private List<String> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(
                    getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        
        mItemList = new ArrayList<String>(); 
        ActivityInfo[] activitieInfos = packageInfo.activities;
        for (ActivityInfo info: activitieInfos) {
            mItemList.add(0, info.name);
        }

        ListView lv = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, mItemList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                enter(position);
            }
        });

        MultipartEntity entity = new MultipartEntity();
        setTitle(String.valueOf(entity.isRepeatable()));
        
//        enter(0);
    }
    
    private void enter(int position) {
        Class<?> activityClazz = null;
        try {
            activityClazz = Class.forName(mItemList.get(position));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Intent intent = new Intent(MainActivity.this, activityClazz);
        startActivity(intent);
    }
}