package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DistortionsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_distortions_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.KeyboardUtils.hideKeyboard(requireActivity());
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);

        addOnClickListeners(view);
    }

    /**
     * Adds on click listeners to the cognitive distortions cards and back button
     *
     * @param view View containing cognitive distortions and back button
     */
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.return_button).setOnClickListener(v ->
                exitFragment());
        view.findViewById(R.id.distortion_1).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_2).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_3).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_4).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_5).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_6).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_7).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_8).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_9).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
        view.findViewById(R.id.distortion_10).setOnClickListener(v ->
                v.setSelected(!v.isSelected()));
    }

    // TODO replace with usage of a nav controller
    /*
    Replaces the current fragment with NewWorryFragment2
    */
    public void exitFragment() {
        NewWorryFragment2 newWorryFragment2 = new NewWorryFragment2();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main, newWorryFragment2);
        transaction.commit();
    }
}