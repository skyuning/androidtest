package com.example.androidtest.widget;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.androidtest.R;

/**
 * Created by linyun on 14-3-21.
 */
public class DialogAnimActivity extends FragmentActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                _DialogFragment dialogFragment = new _DialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "");
            }
        }, 2000);
    }
}

class _DialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setWindowAnimations(R.style.dialogAnim);

        TextView textView = new TextView(getActivity());
        textView.setText("test");

        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(), android.R.anim.slide_in_left));
        return textView;
    }
}