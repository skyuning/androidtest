package com.example.androidtest;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import java.util.List;

/**
 * Created by linyun on 14-5-5.
 */
public class UrlSchemeTestActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        WebView wv = new WebView(this);
//        wv.loadData("<a href='myapp://myactivity'>link</a>", "text/html", "utf-8");
//        setContentView(wv);

        PackageManager packageManager = getPackageManager();

        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        Uri uri = Uri.parse("cy://me.chunyu.ChunyuDoctor/webapp/become_vip/use_phone");
        intent.setData(uri);

//        Uri uri = Uri.parse("http://www.baidu.com");
//        Uri uri = Uri.parse("myapp://myactivity");
//        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}