package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WorryFragment extends Fragment {

    private Worry worry;
    private LinearLayout distortionsContainer;
    private TextView worryTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worry, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        worryTitle = view.findViewById(R.id.worryNameTitle);
        TextView worryDescription = view.findViewById(R.id.worryDescription);
        distortionsContainer = view.findViewById(R.id.distortionsContainer);

        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                worryTitle.setText(worry.getTitle());
                worryDescription.setText(worry.getDescription());
                addSelectedDistortions();

                setVisibility(view, worryDescription);
            }
        }
        addOnClickListeners(view);
    }

    /**
     * Adds on click listeners to all buttons in the fragment
     *
     * @param view The view containing the worry fragment views
     */
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.finishWorryButton).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedWorry", worry);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_ongoingWorryFragment_to_endWorryFragment1, bundle);
        });

        view.findViewById(R.id.backButton).setOnClickListener(v ->
                NavHostFragment.findNavController(WorryFragment.this).navigateUp());
    }

    /**
     * Updates the layout to show only content specific to the type of worry (finished or ongoing)
     * and hide empty content
     *
     * @param view The view containing the worry fragment views
     */
    private void setVisibility(@NonNull View view, TextView worryDescription) {
        if (worry.getDescription().isEmpty()) {
            worryDescription.setVisibility(View.GONE);
        }
        if (distortionsContainer.getChildCount() == 0) {
            view.findViewById(R.id.cognitiveDistortionsLabel).setVisibility(View.GONE);
            distortionsContainer.setVisibility(View.GONE);
        }
        if (worry.isFinished()) {
            updateLayoutFinished(view);
        } else {
            updateLayoutOngoing(view);
        }
    }

    // TODO: too long? refactor?
    /**
     * Updates the layout to show only content specific to finished worries
     *
     * @param view The view containing the worry fragment views
     */
    public void updateLayoutFinished(View view) {
        ImageView background = view.findViewById(R.id.worryImageBackground);
        ImageView scribble = view.findViewById(R.id.scribble);
        ImageView worryCharacter = view.findViewById(R.id.worryCharacter);
        TextView actionsTextView = view.findViewById(R.id.actionsTextView);
        Button respondButton = view.findViewById(R.id.respondButton);
        Button finishWorryButton = view.findViewById(R.id.finishWorryButton);
        TextView howItEndedTitle = view.findViewById(R.id.howItEndedTitle);
        TextView howItEndedDescription = view.findViewById(R.id.howItEndedDescription);

        worryTitle.setTextColor(getResources().getColor(R.color.lightOrangeMain,
                requireActivity().getTheme()));
        background.setImageResource(R.drawable.rectangle_rounded_beige);
        scribble.setVisibility(View.GONE);
        worryCharacter.setImageResource(worry.getFinishedImageResId());
        actionsTextView.setVisibility(View.GONE);
        respondButton.setVisibility(View.GONE);
        finishWorryButton.setVisibility(View.GONE);

        if (worry.getHowItEnded().isEmpty()) {
            howItEndedTitle.setVisibility(View.GONE);
            howItEndedDescription.setVisibility(View.GONE);
        } else {
            howItEndedDescription.setText(worry.getHowItEnded());
        }

        if (!worry.isBetterThanExpected()) {
            view.findViewById(R.id.betterThanExpectedBadge).setVisibility(View.GONE);
        }
    }

    /**
     * Updates the layout to show only content specific to ongoing worries
     *
     * @param view The view containing the worry fragment views
     */
    public void updateLayoutOngoing(View view) {
        ImageView worryCharacter = view.findViewById(R.id.worryCharacter);

        view.findViewById(R.id.sunAndStars).setVisibility(View.GONE);
        worryCharacter.setImageResource(worry.getOngoingImageResId());
        view.findViewById(R.id.howItEndedTitle).setVisibility(View.GONE);
        view.findViewById(R.id.howItEndedDescription).setVisibility(View.GONE);
        view.findViewById(R.id.betterThanExpectedBadge).setVisibility(View.GONE);
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