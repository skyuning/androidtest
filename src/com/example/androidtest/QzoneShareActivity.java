package com.example.androidtest;

<<<<<<< HEAD
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class QzoneShareActivity extends Activity {

    private static final String APP_ID = "100929591";
    private static final String QQ_SCOPE = "get_user_info,get_info,add_t,add_pic_t,add_share";

    private TextView textView;
    private Button loginBtn;
    private Button shareBtn;
    private Tencent tencent;

=======
import com.tencent.tauth.Tencent;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class QzoneShareActivity extends Activity {

>>>>>>> c27d250ff2dd8a8c4eb7650df12c64850d5f6183
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qzone_share);
<<<<<<< HEAD

        tencent = Tencent.createInstance(APP_ID, getApplicationContext());

        textView = (TextView) findViewById(R.id.text);
        loginBtn = (Button) findViewById(R.id.btn_login);
        shareBtn = (Button) findViewById(R.id.btn_share);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToQzone();
            }
        });
    }

    private void login() {
        tencent.login(this, QQ_SCOPE, new BaseUiListener());
    }

    private void shareToQzone() {
        //分享类型
        Bundle params = new Bundle();
        params.putString("title", "test title");
        params.putString("targetUrl", "http://www.chunyuyisheng.com");
        tencent.shareToQzone(this, params, new BaseUiListener());
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            //V2.0版本，参数类型由JSONObject 改成了Object,具体类型参考api文档
            textView.setText("onComplete:" + response.toString());
        }

//        @Override
        public void onComplete(JSONObject jsonObject) {
            textView.setText("onComplete:" + jsonObject.toString());
        }


        @Override
        public void onError(UiError e) {
            textView.setText("onError:" + "code:" + e.errorCode + ", msg:"
                    + e.errorMessage + ", detail:" + e.errorDetail);
        }

        @Override
        public void onCancel() {
            textView.setText("onCancel" + "");
        }
    }
=======
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.qzone_share, menu);
        return true;
    }

>>>>>>> c27d250ff2dd8a8c4eb7650df12c64850d5f6183
}
