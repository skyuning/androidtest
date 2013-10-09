package com.example.androidtest;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SensorTestActivity extends Activity implements OnClickListener,
        SensorEventListener {// 这里实现传感器监听
    /** Called when the activity is first created. */
    Button btn_start = null;
    Button btn_stop = null;
    Button btn_close = null;
    Button btn_open = null;

    // /mediaplaer
    MediaPlayer _mediaPlayer = null; // 音乐播放管理器
    AudioManager audioManager = null; // 声音管理器

    SensorManager _sensorManager = null; // 传感器管理器
    Sensor mProximiny = null; // 传感器实例

    float f_proximiny; // 当前传感器距离

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(this);

        btn_close = (Button) findViewById(R.id.btn_close);
        btn_close.setOnClickListener(this);

        btn_open = (Button) findViewById(R.id.btn_open);
        btn_open.setOnClickListener(this);

        _mediaPlayer = new MediaPlayer();

        audioManager = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);

        _sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximiny = _sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册传感器
        _sensorManager.registerListener(this, mProximiny,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 取消注册传感器
        _sensorManager.unregisterListener(this);
    }

    private void playerMusic(String path) {
        // 重置播放器
        _mediaPlayer.reset();
        try {
            // 设置播放路径
            _mediaPlayer.setDataSource(path);
            // 准备播放
            _mediaPlayer.prepare();
            // 开始播放
            _mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopPlayerMusic() {
        // 停止播放
        if (_mediaPlayer.isPlaying()) {
            _mediaPlayer.reset();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.btn_close:
            audioManager.setMode(AudioManager.MODE_NORMAL);
            break;
        case R.id.btn_open:
            audioManager.setMode(AudioManager.MODE_IN_CALL);
            break;
        case R.id.btn_start:// 音乐取自于Sd卡上的音乐
            String path = Environment.getExternalStorageDirectory()
                    + "/MIUI/music/mp3/主旋律_陈奕迅.mp3";
            playerMusic(path);
            break;
        case R.id.btn_stop:
            stopPlayerMusic();
            break;
        }
    }

    /*
     * 实现SensorEventListener需要实现的两个方法。
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        f_proximiny = event.values[0];
        Log.v("tag",
                "-->  " + f_proximiny + "  |  " + mProximiny.getMaximumRange());

        if (f_proximiny < mProximiny.getMaximumRange()) {
            audioManager.setMode(AudioManager.MODE_NORMAL);
        } else {
            audioManager.setMode(AudioManager.MODE_IN_CALL);
        }
    }

    /*
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}