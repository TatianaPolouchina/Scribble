package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.scribble.MainActivity;
import com.example.scribble.R;
import com.example.scribble.Utils;
import com.example.scribble.Worry;

public class DistortionsFragment extends BaseFragment {

    private DistortionsInfoFragment infoFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_distortions_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Utils.KeyboardUtils.hideKeyboard(requireActivity());
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);

        loadData(view);

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
        view.findViewById(R.id.infoButton).setOnClickListener(v -> {
            if (infoFragment == null) {
                infoFragment = new DistortionsInfoFragment();
            }
            infoFragment.show(getParentFragmentManager(), "overlay_dialog_fragment");
        });
    }

    /**
     * Sets the view of each cognitive distortion to selected
     * if the associated boolean in the worry is true.
     *
     * @param view View containing cognitive distortion Views
     */
    public void loadData(View view) {
        Worry worry = sharedViewModel.getWorry();
        view.findViewById(R.id.overgeneralizing).setSelected(worry.isOvergeneralizing());
        view.findViewById(R.id.mindReading).setSelected(worry.isMindReading());
        view.findViewById(R.id.fortuneTelling).setSelected(worry.isFortuneTelling());
        view.findViewById(R.id.catastrophizing).setSelected(worry.isCatastrophizing());
        view.findViewById(R.id.allOrNothing).setSelected(worry.isAllOrNothing());
        view.findViewById(R.id.negMentalFilter).setSelected(worry.isNegMentalFilter());
        view.findViewById(R.id.disqualifyPositive).setSelected(worry.isDisqualifyPositive());
        view.findViewById(R.id.personalization).setSelected(worry.isPersonalization());
        view.findViewById(R.id.emotionalReasoning).setSelected(worry.isEmotionalReasoning());
        view.findViewById(R.id.labelling).setSelected(worry.isLabelling());
    }
}