package com.example.androidtest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EditFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("EidtFragment--->onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        System.out.println("EidtFragment--->onCreateView");
        return inflater.inflate(R.layout.editfragment, container, false);
    }

    @Override
    public void onStop() {
        System.out.println("EidtFragment--->onStop");
        super.onStop();
    }
}