package com.example.androidtest.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RadioGroupEx extends RadioGroup {

    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;

    public RadioGroupEx(Context context) {
        super(context);
        init();
    }

    public RadioGroupEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    private void init() {
        try {
            Field field = RadioGroup.class.getDeclaredField("mChildOnCheckedChangeListener");
            field.setAccessible(true);
            mChildOnCheckedChangeListener = (CompoundButton.OnCheckedChangeListener) field.get(this);
            
            setOnHierarchyChangeListener(new _OnHierarchyChangeListener());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    private class OnClick2CheckRadioListener implements View.OnClickListener {
        
        private RadioButton mRadioButton;
        
        public OnClick2CheckRadioListener(RadioButton radioButton) {
            mRadioButton = radioButton;
        }

        @Override
        public void onClick(View v) {
            mRadioButton.setChecked(true);
        }
    }
    
    private class _OnHierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
        
        public void onChildViewAdded(View parent, View child) {
            if (child instanceof ViewGroup) {
                final RadioButton radioButton = findRadioButton((ViewGroup) child);
                if (radioButton != null) {
                    radioButton.setOnCheckedChangeListener(mChildOnCheckedChangeListener);
                    child.setOnClickListener(new OnClick2CheckRadioListener(radioButton));
                    // set an OnClickListener only for the purpose of the sound on click
                    radioButton.setOnClickListener(new OnClick2CheckRadioListener(radioButton));
                }
            }
            // set an OnClickListener only for the purpose of the sound on click
            else if (child instanceof RadioButton)
                child.setOnClickListener(new OnClick2CheckRadioListener((RadioButton) child));
        }

        @Override
        public void onChildViewRemoved(View parent, View child) {
            if (child instanceof ViewGroup) {
                final RadioButton radioButton = findRadioButton((ViewGroup) child);
                if (radioButton != null) {
                    radioButton.setOnCheckedChangeListener(null);
                    child.setOnClickListener(null);
                }
            }
        }
    }

    public RadioButton findRadioButton(ViewGroup group) {
        RadioButton resBtn = null;
        int len = group.getChildCount();
        for (int i = 0; i < len; i++) {
            if (group.getChildAt(i) instanceof RadioButton) {
                resBtn = (RadioButton) group.getChildAt(i);
            } else if (group.getChildAt(i) instanceof ViewGroup) {
                findRadioButton((ViewGroup) group.getChildAt(i));
            }
        }
        return resBtn;
    }
}
