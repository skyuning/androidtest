package com.example.androidtest;

import java.util.List;

import org.xframe.utils.ViewUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GetChildViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RelativeLayout root = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.activity_get_child_view, null);
        setContentView(root);

        List<View> children = ViewUtils.getAllChildren((ViewGroup) getWindow().getDecorView());

        ListView lv = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<View> adapter = new ArrayAdapter<View>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                children) {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                int id = ((View) getItem(position)).getId();
                tv.setText(String.valueOf(id));
                return view;
            }
        };
        lv.setAdapter(adapter);

    }
}
