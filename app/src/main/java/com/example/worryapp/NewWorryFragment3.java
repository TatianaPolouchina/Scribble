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
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class NewWorryFragment3 extends Fragment {

    private SharedViewModel sharedViewModel;
    private GridLayout selectedDistortionsContainer;

    public NewWorryFragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry3, container, false);
    }

    // TODO refactor method
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton addDistortionButton = view.findViewById(R.id.add_distortion_Button);
        selectedDistortionsContainer = view.findViewById(R.id.distortionsContainer);

        addSelectedDistortions();

        addDistortionButton.setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment3_to_distortionsFragment));

        view.findViewById(R.id.next_button).setOnClickListener(v ->
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment3_to_newWorryFinishedFragment));
    }

    /**
     * Adds the logos associated to the selected distortions to the linear layout
     */
    private void addSelectedDistortions() {
        Worry worry = sharedViewModel.getWorry();
        if (worry.isOvergeneralizing()) {
            addPhotoToGridLayout(R.drawable.vector_cd_circles);
        }
        if (worry.isMindReading()) {
            addPhotoToGridLayout(R.drawable.vector_cd_brain);
        }
        if (worry.isFortuneTelling()) {
            addPhotoToGridLayout(R.drawable.vector_cd_crystal_ball);
        }
        if (worry.isCatastrophizing()) {
            addPhotoToGridLayout(R.drawable.vector_cd_explosion);
        }
        if (worry.isAllOrNothing()) {
            addPhotoToGridLayout(R.drawable.vector_cd_cross_out_circle);
        }
        if (worry.isNegMentalFilter()) {
            addPhotoToGridLayout(R.drawable.vector_cd_magnifying_glass);
        }
        if (worry.isDisqualifyPositive()) {
            addPhotoToGridLayout(R.drawable.vector_cd_crossed_out_sun);
        }
        if (worry.isPersonalization()) {
            addPhotoToGridLayout(R.drawable.vector_cd_mirror);
        }
        if (worry.isEmotionalReasoning()) {
            addPhotoToGridLayout(R.drawable.vector_cd_heart);
        }
        if (worry.isLabelling()) {
            addPhotoToGridLayout(R.drawable.vector_cd_label);
        }
    }


    // TODO: comment
    private void addPhotoToGridLayout(int id) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(id);
        imageView.setPadding(8, 8, 8, 8);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(190, 190);
        imageView.setLayoutParams(params);

        selectedDistortionsContainer.addView(imageView);
    }
}