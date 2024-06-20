package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OngoingWorryFragment extends Fragment {

    private Worry worry;
    private LinearLayout distortionsContainer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ongoing_worry, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView worryTitle = view.findViewById(R.id.worryNameTitle);
        TextView worryDescription = view.findViewById(R.id.worryDescription);
        distortionsContainer = view.findViewById(R.id.distortionsContainer);

        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                worryTitle.setText(worry.getTitle());
                worryDescription.setText(worry.getDescription());
                addSelectedDistortions();

                //Set missing info invisible
                if (worry.getDescription().isEmpty()) {
                    worryDescription.setVisibility(View.GONE);
                }
                if (distortionsContainer.getChildCount() == 0) {
                    view.findViewById(R.id.cognitiveDistortionsLabel).setVisibility(View.GONE);
                    distortionsContainer.setVisibility(View.GONE);
                }
            }
        }
    }

    // TODO: repeated code from NewWorryFragment3 (refactor)
    /**
     * Adds the logos associated to the selected distortions to the linear layout
     */
    private void addSelectedDistortions() {
        if (worry.isOvergeneralizing()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_circles);
        }
        if (worry.isMindReading()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_brain);
        }
        if (worry.isFortuneTelling()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_crystal_ball);
        }
        if (worry.isCatastrophizing()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_explosion);
        }
        if (worry.isAllOrNothing()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_cross_out_circle);
        }
        if (worry.isNegMentalFilter()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_magnifying_glass);
        }
        if (worry.isDisqualifyPositive()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_crossed_out_sun);
        }
        if (worry.isPersonalization()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_mirror);
        }
        if (worry.isEmotionalReasoning()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_heart);
        }
        if (worry.isLabelling()) {
            addPhotoToLinearLayout(R.drawable.vector_cd_label);
        }
    }

    // TODO: comment
    private void addPhotoToLinearLayout(int id) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(id);
        imageView.setPadding(15,10,15,10);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(220, 220);
        imageView.setLayoutParams(params);

        distortionsContainer.addView(imageView);
    }
}