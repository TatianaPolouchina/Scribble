package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scribble.R;
import com.example.scribble.Worry;

public class NewWorryFragment3 extends WorryActionFragment {

    private GridLayout selectedDistortionsContainer;
    private TextView addDistortionLabel;

    public NewWorryFragment3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        selectedDistortionsContainer = view.findViewById(R.id.distortionsContainer);
        addDistortionLabel = view.findViewById(R.id.add_distortion_button_label);

        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());

        addSelectedDistortions();
        addOnClickListeners(view);
    }

    // TODO: comment
    private void addOnClickListeners(@NonNull View view) {
        ConstraintLayout addDistortionLayout = view.findViewById(R.id.addDistortionLayout);

        addDistortionLayout.setOnClickListener(v ->
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
            addCDtoLayout(R.drawable.vector_cd_circles);
        }
        if (worry.isMindReading()) {
            addCDtoLayout(R.drawable.vector_cd_brain);
        }
        if (worry.isFortuneTelling()) {
            addCDtoLayout(R.drawable.vector_cd_crystal_ball);
        }
        if (worry.isCatastrophizing()) {
            addCDtoLayout(R.drawable.vector_cd_explosion);
        }
        if (worry.isAllOrNothing()) {
            addCDtoLayout(R.drawable.vector_cd_cross_out_circle);
        }
        if (worry.isNegMentalFilter()) {
            addCDtoLayout(R.drawable.vector_cd_magnifying_glass);
        }
        if (worry.isDisqualifyPositive()) {
            addCDtoLayout(R.drawable.vector_cd_crossed_out_sun);
        }
        if (worry.isPersonalization()) {
            addCDtoLayout(R.drawable.vector_cd_mirror);
        }
        if (worry.isEmotionalReasoning()) {
            addCDtoLayout(R.drawable.vector_cd_heart);
        }
        if (worry.isLabelling()) {
            addCDtoLayout(R.drawable.vector_cd_label);
        }
    }

    private void addCDtoLayout(int id) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(id);
        imageView.setPadding(8, 8, 8, 8);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(190, 190);
        imageView.setLayoutParams(params);

        selectedDistortionsContainer.addView(imageView);
        addDistortionLabel.setVisibility(View.GONE);

    }
}