package com.example.worryapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class NewWorryFragment2 extends Fragment {

    public NewWorryFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_worry2, container, false);

        ImageButton addDistortionButton = view.findViewById(R.id.add_distortion_Button);

        addDistortionButton.setOnClickListener(v -> showDistortionsFragment());

        return view;
    }

    /*
    Replaces the current fragment with DistortionsFragment
     */
    public void showDistortionsFragment() {
        DistortionsFragment distortionsFragment = new DistortionsFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main, distortionsFragment);
        transaction.commit();
    }
}