package com.example.androidtest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by linyun on 14-1-2.
 */

public class ScrollerLayout extends LinearLayout {

    private Scroller scroller;
    int i = 0;

    public void startScr() {
        scroller.startScroll(0, 0, 1000, 1000, 5000);
    }

    public ScrollerLayout(Context context) {
        super(context);
        scroller = new Scroller(getContext());
    }

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(getContext());
    }

    public ScrollerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        scroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(0, scroller.getCurrY() + 1);
        }
    }
}