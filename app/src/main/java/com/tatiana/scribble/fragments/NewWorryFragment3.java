package com.tatiana.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tatiana.scribble.MainActivity;
import com.tatiana.scribble.R;
import com.tatiana.scribble.Utils;
import com.tatiana.scribble.Worry;

/***
 * 3rd fragment for creating a new worry, asks user to assign cognitive distortions to the worry
 */
public class NewWorryFragment3 extends BaseFragment {
    private GridLayout selectedDistortionsContainer;
    private TextView addDistortionLabel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectedDistortionsContainer = view.findViewById(R.id.distortionsContainer);
        addDistortionLabel = view.findViewById(R.id.add_distortion_button_label);
        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());
        addSelectedDistortions();
        addOnClickListeners(view);
        setColumnCount(view);
    }

    /***
     * Sets the number of columns of selected distortions depending on screen width and density
     * @param view container View
     */
    private void setColumnCount(@NonNull View view) {
        int columnWidthDp = 200;
        float scale = getResources().getDisplayMetrics().density;
        int columnWidthPx = (int) (columnWidthDp * scale + 0.5f); // Convert dp to pixels

        int screenWidth = Utils.screenUtils.getScreenWidth(view);
        int numColumns = screenWidth / columnWidthPx;
        if (!Utils.screenUtils.wideScreen(view)) {
            selectedDistortionsContainer.setColumnCount(numColumns);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
    }

    /***
     * Adds all on click listeners to Views
     *
     * @param view container View
     */
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.addDistortionLayout).setOnClickListener(v ->
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
            addCDtoLayout(R.drawable.cd_circles);
        }
        if (worry.isMindReading()) {
            addCDtoLayout(R.drawable.cd_brain);
        }
        if (worry.isFortuneTelling()) {
            addCDtoLayout(R.drawable.cd_crystal_ball);
        }
        if (worry.isCatastrophizing()) {
            addCDtoLayout(R.drawable.cd_explosion);
        }
        if (worry.isAllOrNothing()) {
            addCDtoLayout(R.drawable.vector_cd_cross_out_circle);
        }
        if (worry.isNegMentalFilter()) {
            addCDtoLayout(R.drawable.vector_cd_magnifying_glass);
        }
        if (worry.isDisqualifyPositive()) {
            addCDtoLayout(R.drawable.cd_crossed_out_sun);
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

    /***
     * Adds the cognitive distortion image with ID id to the selectedDistortionsContainer
     *
     * @param id ID of image to be added
     */
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