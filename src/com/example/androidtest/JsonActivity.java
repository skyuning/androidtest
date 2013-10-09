package com.example.androidtest;


import org.json.JSONException;
import org.json.JSONObject;
import org.xframe.annotation.JSONUtils;
import org.xframe.annotation.JSONUtils.JSONDict;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class JsonActivity extends Activity {
    
    public static class Base {
        @JSONDict(name = "s", defVal = "")
        public String s;
    }
    
    public static class Test extends Base {
        @JSONDict(name = "a", defVal = "-1")
        public int a;
        
        @JSONDict(name = "b", defVal = "0", type = Float.class)
        public float b;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        
        TextView tView = (TextView) findViewById(R.id.textview);
        
//        JSONTokener jt = new JSONTokener("12342314234231423");
//        try {
//            tView.setText(jt.nextValue().getClass().toString());
//        } catch (JSONException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        
        String j1 = "{a: 123, b: 1.2378, s: '12345'}";
        try {
            Test t = new Test();
            JSONUtils.json2JavaObject(new JSONObject(j1), t);
            tView.setText(String.format("%s\n%d\n", t.s, t.a) + t.b);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.json, menu);
        return true;
    }

}
