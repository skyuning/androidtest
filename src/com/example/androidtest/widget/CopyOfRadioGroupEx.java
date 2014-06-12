//package com.example.androidtest.widget;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CompoundButton;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//
//public class CopyOfRadioGroupEx extends RadioGroup {
//
//    private CompoundButton.OnCheckedChangeListener mChildOnCheckedChangeListener;
//    private Method mSetOnCheckedChangeWidgetListener;
//
//    public CopyOfRadioGroupEx(Context context) {
//        super(context);
//        init();
//    }
//
//    public CopyOfRadioGroupEx(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//    
//    private void init() {
//        try {
//            _init();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void _init() throws NoSuchFieldException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException {
//        Field field = RadioGroup.class.getDeclaredField("mChildOnCheckedChangeListener");
//        field.setAccessible(true);
//        mChildOnCheckedChangeListener = (CompoundButton.OnCheckedChangeListener) field.get(this);
//
//        mSetOnCheckedChangeWidgetListener = CompoundButton.class
//                .getDeclaredMethod("setOnCheckedChangeWidgetListener",
//                        CompoundButton.OnCheckedChangeListener.class);
//        mSetOnCheckedChangeWidgetListener.setAccessible(true);
//        
//        setOnHierarchyChangeListener(new _OnHierarchyChangeListener());
//    }
//
//    private class _OnHierarchyChangeListener implements
//            ViewGroup.OnHierarchyChangeListener {
//        public void onChildViewAdded(View parent, View child) {
//            if (!(child instanceof ViewGroup))
//                return;
//
//            RadioButton radioButton = findRadioButton((ViewGroup) child);
//
//            try {
//                mSetOnCheckedChangeWidgetListener.invoke(radioButton, mChildOnCheckedChangeListener);
//            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onChildViewRemoved(View parent, View child) {
//            // TODO Auto-generated method stub
//
//        }
//    }
//
//    /** 查找radioButton控件 */
//    public RadioButton findRadioButton(ViewGroup group) {
//        RadioButton resBtn = null;
//        int len = group.getChildCount();
//        for (int i = 0; i < len; i++) {
//            if (group.getChildAt(i) instanceof RadioButton) {
//                resBtn = (RadioButton) group.getChildAt(i);
//            } else if (group.getChildAt(i) instanceof ViewGroup) {
//                findRadioButton((ViewGroup) group.getChildAt(i));
//            }
//        }
//        return resBtn;
//    }
//}
