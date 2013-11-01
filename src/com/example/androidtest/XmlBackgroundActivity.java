package com.example.androidtest;

import org.xframe.annotation.ViewAnnotation;
import org.xframe.annotation.ViewAnnotation.ViewInject;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.widget.ImageView;

public class XmlBackgroundActivity extends Activity {

    @ViewInject(id = R.id.local)
    private ImageView mImageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_background);
        ViewAnnotation.bind(getWindow().getDecorView(), this);
        
        mImageView.setImageBitmap(getRoundedCornerBitmap());
    }

    Bitmap getRoundedCornerBitmap() {
        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.beauty)
                .copy(Bitmap.Config.ARGB_8888, true);
        Bitmap dst = BitmapFactory.decodeResource(res, R.drawable.circle)
                .copy(Bitmap.Config.ARGB_8888, true);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_OUT));

        canvas.drawBitmap(dst, 0, 0, paint);
        return bmp;
    }
}
