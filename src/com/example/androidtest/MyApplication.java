package com.example.androidtest;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.apkplug.service.ApkplugCloudAgent;

import org.apkplug.Bundle.BundleControl;
import org.apkplug.Bundle.installCallback;
import org.apkplug.app.FrameworkFactory;
import org.apkplug.app.FrameworkInstance;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceReference;

/**
 * Created by linyun on 14-6-4.
 */
public class MyApplication extends Application {

    private FrameworkInstance mFramework;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            initApkPlug();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initApkPlug() throws Exception {
        mFramework = FrameworkFactory.getInstance().start(null, this);
        BundleContext bundleContext = mFramework.getSystemBundleContext();
        ApkplugCloudAgent.init(bundleContext);
        ServiceReference serviceReference = bundleContext.getServiceReference(BundleControl.class.getName());
        if (serviceReference != null) {
            BundleControl bundleControl = (BundleControl) bundleContext.getService(serviceReference);
            bundleControl.install(bundleContext, "file:/data/data/com.example.androidtest/files/test.apk", new installCallback() {
                @Override
                public void callback(int status, Bundle bundle) {
                    String text;
                    if (status == installCallback.stutas5 || status == installCallback.stutas7) {
                        text = String.format("插件安装 %s：%d", bundle.getName(), status);
                        onSuccess(bundle);
                        Log.d("install callback", text);
                        Toast.makeText(MyApplication.this, text, Toast.LENGTH_SHORT).show();
                    } else {
                        text = String.format("插件安装失败：%s", bundle.getName());
                        Log.d("install callback", text);
                        Toast.makeText(MyApplication.this, text, Toast.LENGTH_SHORT).show();

                        try {
                            bundle.uninstall();
                            Toast.makeText(MyApplication.this, "uninstall success", Toast.LENGTH_SHORT).show();
                        } catch (BundleException e) {
                            e.printStackTrace();
                        }
                    }
                }

                private void onSuccess(Bundle bundle) {
                    if (bundle.getState() != bundle.ACTIVE) {
                        try {
                            bundle.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    if (bundle.getBundleActivity() != null) {
                        Intent i = new Intent();
                        i.setClassName(MyApplication.this, bundle.getBundleActivity().split(",")[0]);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        MyApplication.this.startActivity(i);
                    } else {
                        Toast.makeText(MyApplication.this, "该插件没有配置BundleActivity",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

