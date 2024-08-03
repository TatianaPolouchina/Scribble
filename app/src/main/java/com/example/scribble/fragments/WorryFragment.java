package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scribble.MainActivity;
import com.example.scribble.R;
import com.example.scribble.Worry;

import java.util.List;
import java.util.Objects;

/***
 * Fragment displaying all of a Worry's information and options
 */
public class WorryFragment extends Fragment {
    private Worry worry;
    private LinearLayout distortionsContainer;
    private TextView worryTitle;
    private TextView reminderText;
    private int reminderIndex;
    private FrameLayout leftButton;
    private FrameLayout rightButton;
    private DeleteWorryFragment deleteWorryFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_worry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        worryTitle = view.findViewById(R.id.worryNameTitle);
        distortionsContainer = view.findViewById(R.id.distortionsContainer);
        reminderText = view.findViewById(R.id.reminderTextView);
        leftButton = view.findViewById(R.id.leftReminderButton);
        rightButton = view.findViewById(R.id.rightReminderButton);
        addOnClickListeners(view);
        handleBackPress();
        populateLayout(view);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
    }

    /***
     * Enables back press only to return to ongoingWorriesFragment or finishedWorriesFragment
     */
    public void handleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = NavHostFragment.findNavController
                        (WorryFragment.this);

                int previousFragmentId = Objects.requireNonNull
                        (navController.getPreviousBackStackEntry()).getDestination().getId();

                if (previousFragmentId == R.id.ongoingWorriesFragment ||
                        previousFragmentId == R.id.finishedWorriesFragment) {
                    navController.popBackStack();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    /**
     * Replaces all titles and information with the corresponding data from the Worry
     *
     * @param view The view containing the worry fragment views
     */
    private void populateLayout(@NonNull View view) {
        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                loadWorryData(view);
            }
        }
    }

    /***
     * Loads data from the worry to populate the layout
     *
     * @param view container View
     */
    private void loadWorryData(@NonNull View view) {
        TextView worryDescription = view.findViewById(R.id.worryDescription);
        worryTitle.setText(worry.getTitle());
        worryDescription.setText(worry.getDescription());
        addSelectedDistortions();
        setVisibility(view, worryDescription);
        updateReminders();
    }

    /***
     * Loads the Worry's reminders and updates the reminders box
     */
    private void updateReminders() {
        reminderIndex = 0;
        reminderText.setText(worry.getResponses().get(reminderIndex));
        updateLeftButton();
        updateRightButton();
    }

    /**
     * Adds on click listeners to all buttons in the fragment
     *
     * @param view The view containing the worry fragment views
     */
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.deleteButton).setOnClickListener(v -> {
            deleteWorryFragment = new DeleteWorryFragment(worry);
            deleteWorryFragment.show(getParentFragmentManager(), "overlay_dialog_fragment");
        });

        view.findViewById(R.id.finishWorryButton).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedWorry", worry);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_ongoingWorryFragment_to_endWorryFragment1, bundle);
        });

        view.findViewById(R.id.respondButton).setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedWorry", worry);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_worryFragment_to_respondFragment, bundle);
        });

        view.findViewById(R.id.backButton).setOnClickListener(v -> {
            if (worry.isFinished()) {
                NavHostFragment.findNavController(WorryFragment.this).navigate
                        (R.id.action_worryFragment_to_finishedWorriesFragment);
            } else {
                NavHostFragment.findNavController(WorryFragment.this).navigate
                        (R.id.action_worryFragment_to_ongoingWorriesFragment);
            }
        });

        leftButton.setOnClickListener(v -> previousReminder());
        rightButton.setOnClickListener(v -> nextReminder());
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
            view.findViewById(R.id.descriptionTitle).setVisibility(View.GONE);
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

    /**
     * Updates the layout to show only content specific to finished worries
     *
     * @param view The view containing the worry fragment views
     */
    public void updateLayoutFinished(View view) {
        updateImagesToFinished(view);
        removeActions(view);
        updateTextViewsToFinished(view);
        if (!worry.isBetterThanExpected()) {
            view.findViewById(R.id.betterThanExpectedBadge).setVisibility(View.GONE);
        }
    }

    /***
     * Updates the images to reflect the finished worry
     *
     * @param view container View
     */
    private void updateImagesToFinished(View view) {
        ImageView background = view.findViewById(R.id.worryImageBackground);
        ImageView scribble = view.findViewById(R.id.scribble);
        ImageView worryCharacter = view.findViewById(R.id.worryCharacter);
        background.setImageResource(R.drawable.rectangle_rounded_10dp_beige);
        scribble.setVisibility(View.GONE);
        worryCharacter.setImageResource(worry.getFinishedImageResId());
    }

    /***
     * Removes the actions label and buttons
     *
     * @param view containerView
     */
    private void removeActions(View view) {
        TextView actionsTextView = view.findViewById(R.id.actionsTextView);
        Button respondButton = view.findViewById(R.id.respondButton);
        Button finishWorryButton = view.findViewById(R.id.finishWorryButton);
        actionsTextView.setVisibility(View.GONE);
        respondButton.setVisibility(View.GONE);
        finishWorryButton.setVisibility(View.GONE);
    }

    /***
     * Updates the text, visibility, and colours of the title, description, and
     * how it ended description/title to reflect the finished worry
     *
     * @param view container View
     */
    private void updateTextViewsToFinished(View view) {
        TextView descriptionTextView = view.findViewById(R.id.worryDescription);
        TextView howItEndedTitle = view.findViewById(R.id.howItEndedTitle);
        TextView howItEndedDescription = view.findViewById(R.id.howItEndedDescription);

        descriptionTextView.setTextColor(getResources().getColor(R.color.darkGrey, requireActivity().getTheme()));
        worryTitle.setTextColor(getResources().getColor(R.color.mediumOrange,
                requireActivity().getTheme()));
        if (worry.getHowItEnded().isEmpty()) {
            howItEndedTitle.setVisibility(View.GONE);
            howItEndedDescription.setVisibility(View.GONE);
        } else {
            howItEndedDescription.setText(worry.getHowItEnded());
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

    /**
     * Adds the logos associated to the selected distortions to the linear layout
     */
    private void addSelectedDistortions() {
        if (worry.isOvergeneralizing()) {
            addCDtoLayout(R.drawable.cd_circles, R.string.Distortion1Name);
        }
        if (worry.isMindReading()) {
            addCDtoLayout(R.drawable.cd_brain, R.string.Distortion2Name);
        }
        if (worry.isFortuneTelling()) {
            addCDtoLayout(R.drawable.cd_crystal_ball, R.string.Distortion3Name);
        }
        if (worry.isCatastrophizing()) {
            addCDtoLayout(R.drawable.cd_explosion, R.string.Distortion4Name);
        }
        if (worry.isAllOrNothing()) {
            addCDtoLayout(R.drawable.vector_cd_cross_out_circle, R.string.Distortion5NameFormatted);
        }
        if (worry.isNegMentalFilter()) {
            addCDtoLayout(R.drawable.vector_cd_magnifying_glass, R.string.Distortion6NameFormatted);
        }
        if (worry.isDisqualifyPositive()) {
            addCDtoLayout(R.drawable.cd_crossed_out_sun, R.string.Distortion7NameFormatted);
        }
        if (worry.isPersonalization()) {
            addCDtoLayout(R.drawable.vector_cd_mirror, R.string.Distortion8Name);
        }
        if (worry.isEmotionalReasoning()) {
            addCDtoLayout(R.drawable.vector_cd_heart, R.string.Distortion9NameFormatted);
        }
        if (worry.isLabelling()) {
            addCDtoLayout(R.drawable.vector_cd_label, R.string.Distortion10Name);
        }
    }

    /***
     * Formats and adds the drawable to the distortionsContainer
     *
     * @param imageId the id of the distortion's drawable
     *
     * @param titleId the id of the distortion's name
     */
    private void addCDtoLayout(int imageId, int titleId) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout cdLayout = (LinearLayout) inflater.inflate(R.layout.cognitive_distortion, distortionsContainer, false);
        ImageView imageView = cdLayout.findViewById(R.id.cdImageSelector);
        TextView textView = cdLayout.findViewById(R.id.cdLabel);
        imageView.setImageResource(imageId);
        textView.setText(titleId);
        distortionsContainer.addView(cdLayout);
    }

    /**
     * Displays the next reminder if one exists, updating the left/right buttons' visibility
     */
    public void nextReminder() {
        List<String> responses = worry.getResponses();

        if (responses.size() > (reminderIndex + 1)) {
            reminderIndex++;
            reminderText.setText(responses.get(reminderIndex));
            updateLeftButton();
            updateRightButton();
        }
    }

    /**
     * Displays the previous reminder if one exists, updating the left/right buttons' visibility
     */
    public void previousReminder() {
        if (reminderIndex > 0) {
            reminderIndex--;
            reminderText.setText(worry.getResponses().get(reminderIndex));
            updateLeftButton();
            updateRightButton();
        }
    }

    /**
     * Sets the left button invisible if this is the first reminder, visible otherwise
     */
    private void updateLeftButton() {
        if (worry.getResponses().size() == 1) {
            leftButton.setVisibility(View.GONE);
        } else if (reminderIndex == 0) {
            leftButton.setVisibility(View.INVISIBLE);
        } else {
            leftButton.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Sets the right button invisible if there are no more reminders to be shown, visible otherwise
     */
    private void updateRightButton() {
        if (worry.getResponses().size() == 1) {
            rightButton.setVisibility(View.GONE);
        } else if ((reminderIndex + 1) >= worry.getResponses().size()) {
            rightButton.setVisibility(View.INVISIBLE);
        } else {
            rightButton.setVisibility(View.VISIBLE);
        }
    }

}