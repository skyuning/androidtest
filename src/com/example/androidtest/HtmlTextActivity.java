package com.example.androidtest;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.view.Menu;
import android.widget.TextView;

public class HtmlTextActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_html_text);
        
        TextView tv = (TextView) findViewById(R.id.textview);
        tv.setText(
            Html.fromHtml(
                "<b>text3:</b>  Text with a " +
                "<font style='color:red'>keywords</font>" +
                "<font color='red'>keywords</font>" +
                "<a href=\"http://www.google.com\">link</a> " +
                "created in the Java source code using HTML."));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.html_text, menu);
        return true;
    }

}
