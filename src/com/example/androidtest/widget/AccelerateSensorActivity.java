package com.example.androidtest.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidtest.Complex;
import com.example.androidtest.FFT;

import com.example.androidtest.R;

import org.xframe.annotation.ViewAnnotation;

import java.util.LinkedList;

/**
 * Created by linyun on 14-2-26.
 */
public class AccelerateSensorActivity extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerateSensor;
    private LinkedList<Double> waveList;
    private LinkedList<Complex> complexWaveList;
    private long lastTime;


    private double INTERVAL_MS = 10; // ms
    private double INTERVAL = INTERVAL_MS / 1000; // second
    int N = 1024;
    double TOTAL_TIME = N * INTERVAL;
    double DF = 1.0 / TOTAL_TIME; // 0.01Hz

    @ViewAnnotation.ViewInject(id = R.id.ImageView1)
    private ImageView imageView;

    @ViewAnnotation.ViewInject(id = R.id.textview)
    private TextView textView;

    @ViewAnnotation.ViewInject(id = R.id.xyz)
    private TextView xyzView;

    @ViewAnnotation.ViewInject(id = R.id.args)
    private TextView argsView;

    @ViewAnnotation.ViewInject(id = R.id.wave)
    private VisualizerView waveView;

    @ViewAnnotation.ViewInject(id = R.id.freq)
    private VisualizerView freqView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerate_sensor);
        ViewAnnotation.bind(this, this);

//        int freq_min = 73;
//        double freq = freq_min / 60.0;
//        double period = 1 / freq;
//        int dots = (int) (period / INTERVAL);
//
//        freqView.setTextView(textView);
        String args = String.format("采样周期：%sms\n"
                + "采样点数：%s\n"
                + "总时间：%ss\n"
                + "^F：%s\n",
//                + "心跳频率：%s/min, %s/s\n"
//                + "心跳周期：%ss\n",
                INTERVAL_MS,
                N,
                TOTAL_TIME,
                DF
//                freq_min, freq,
//                period
        );
        argsView.setText(args);
//
//        int M = 1024;
//        Double[] waves = new Double[M];
//        for (int i=0; i<M; i++) {
//            Double v = 0.0;
//            v = Math.cos(i * Math.PI * 2 * 10 / M);
//            v = v + Math.random();
//            if (i % dots == 0)
//                v = 1.0;
//            waves[i] = v;
//        }
//        waveView.updateVisualizer(waves);
//
//        Complex[] cWaves = new Complex[M];
//        for (int i=0; i<M; i++)
//            cWaves[i] = new Complex(waves[i], 0);
//        Complex[] cFreqs = FFT.fft(cWaves);
//        Double[] freqs = new Double[M];
//        for (int i=0; i<M/2; i++)
//            freqs[i] = cFreqs[i].abs();
//        for (int i=M/2; i<M; i++)
//            freqs[i] = 0.0;
//        freqs[0] = 0.0;
//        freqView.updateVisualizer(freqs);

        waveList = new LinkedList<Double>();
        complexWaveList = new LinkedList<Complex>();
        for (int i=0; i< N; i++) {
            waveList.add(0.0);
            complexWaveList.add(new Complex(0, 0));
        }

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lastTime = System.currentTimeMillis();
        sensorManager.registerListener(this, accelerateSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long curTime = System.currentTimeMillis();
        if (curTime < lastTime + INTERVAL_MS)
            return;
        lastTime = curTime;

        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        String xyz = String.format("%s, %s, %s", x, y, z);
        xyzView.setText(xyz);
        Double r = Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2);
        r = Math.sqrt(r) - 9.9;
        waveList.remove(0);
        waveList.add(r);
        complexWaveList.remove(0);
        complexWaveList.add(new Complex(r, 0));

        // update wave view
        Double[] waves = waveList.toArray(new Double[0]);
        for (int i=0; i<waves.length; i++)
            waves[i] *= 20.0;
        waveView.updateVisualizer(waves);

        // update freq view
//        Complex[] complexesWaves = complexWaveList.toArray(new Complex[0]);
//        Complex[] complexesFreqs = FFT.fft(complexesWaves);
//        Double[] freqs = new Double[complexesFreqs.length];
//        freqs[0] = 0.0;
//        for (int i=1; i<complexesFreqs.length; i++)
//            if (i < 100.0 / 60 / DF || i > 200.0 / 60 / DF)
//                freqs[i] = 0.0;
//            else
//                freqs[i] = complexesFreqs[i].abs();
//        freqView.updateVisualizer(freqs);
//
//        // show freq num
//        Double maxFreq = 0.0;
//        for (int i=0; i<freqs.length; i++) {
//            if (freqs[i] > maxFreq) {
//                maxFreq = freqs[i];
//            }
//        }
//
//        int index = 0;
//        for (int i=0; i<freqs.length; i++) {
//            if (freqs[i] > maxFreq / 2) {
//                index = i;
//                break;
//            }
//        }
//        textView.setText("" + index);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}


/**
 * A simple class that draws waveform data received from a
 * {@link android.media.audiofx.Visualizer.OnDataCaptureListener#onWaveFormDataCapture }
 */
class VisualizerView extends View {

    private Double[] mValues;
    private float[] mPoints;
    private Rect mRect = new Rect();
    private Paint mForePaint = new Paint();
    private TextView textView;

    public VisualizerView(Context context) {
        super(context);
        init();
    }

    public VisualizerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VisualizerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mValues = null;

        mForePaint.setStrokeWidth(1f);
        mForePaint.setAntiAlias(true);
        mForePaint.setColor(Color.rgb(0, 128, 255));
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void updateVisualizer(Double[] values) {
        mValues = values;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mValues == null) {
            return;
        }

        if (mPoints == null || mPoints.length < mValues.length * 4) {
            mPoints = new float[mValues.length * 4];
        }

        int width = getWidth();
        int height = getHeight();
        mRect.set(0, 0, width, height);

        Double maxValue = 0.0;
        for (int i=0; i<mValues.length; i++)
            if (mValues[i] > maxValue) {
                maxValue = mValues[i];
            }

        for (int i = 0; i < mValues.length - 1; i++) {
            mPoints[i * 4] = mRect.width() * i / (mValues.length - 1);
            mPoints[i * 4 + 1] = (float) (mValues[i] / maxValue * height);
            mPoints[i * 4 + 2] = mRect.width() * (i + 1) / (mValues.length - 1);
            mPoints[i * 4 + 3] = (float) (mValues[i + 1] / maxValue * height);
        }

        canvas.drawLines(mPoints, mForePaint);
    }
}
