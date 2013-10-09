package com.example.androidtest;

import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.TextView;

public class AliWapPayActivity extends Activity {
    
    @ViewInject(id = R.id.req)
    private TextView mReqView;

    @ViewInject(id = R.id.resp)
    private TextView mRespView;

    @ViewInject(id = R.id.webView1)
    private WebView mWebView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ali_wap_pay);
        ViewAnnotation.bind(this, this);
        
        final AliWapPayer payer = new AliWapPayer(this, "subject", "123", "99",
                "springheal@163.com", TestData.PARTNER_ID, TestData.SELLER_ID,
                TestData.RSA_PRIVATE);
        
        AsyncTask<Void, Void, Object> task = new AsyncTask<Void, Void, Object>() {
            @Override
            protected Object doInBackground(Void... params) {
                return payer.createDirectTrade();
            }

            @Override
            protected void onPostExecute(Object result) {
                super.onPostExecute(result);
                Map<String, String> map = (Map<String, String>) result;
                String resData = map.get("res_data");
                String decodedResData = map.get("decoded_res_data");
                mWebView.loadDataWithBaseURL(null, decodedResData, "text/plain", "utf-8", null);
            }
        };
        task.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ali_wap_pay, menu);
        return true;
    }

}
