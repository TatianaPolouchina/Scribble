package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DistortionsFragment extends Fragment {

    private SharedViewModel sharedViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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
                NavHostFragment.findNavController(this).navigate
                        (R.id.newWorryFragment3));
        view.findViewById(R.id.overgeneralizing).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setOvergeneralizing(v.isSelected());
        });
        view.findViewById(R.id.mindReading).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setMindReading(v.isSelected());
        });
        view.findViewById(R.id.fortuneTelling).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setFortuneTelling(v.isSelected());
        });
        view.findViewById(R.id.catastrophizing).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setCatastrophizing(v.isSelected());
        });
        view.findViewById(R.id.allOrNothing).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setAllOrNothing(v.isSelected());
        });
        view.findViewById(R.id.negMentalFilter).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setNegMentalFilter(v.isSelected());
        });
        view.findViewById(R.id.disqualifyPositive).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setDisqualifyPositive(v.isSelected());
        });
        view.findViewById(R.id.personalization).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setPersonalization(v.isSelected());
        });
        view.findViewById(R.id.emotionalReasoning).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setEmotionalReasoning(v.isSelected());
        });
        view.findViewById(R.id.labelling).setOnClickListener(v -> {
            v.setSelected(!v.isSelected());
            sharedViewModel.getWorry().setLabelling(v.isSelected());
        });
    }
}