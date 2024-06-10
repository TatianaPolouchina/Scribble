package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class NewWorryFragment2 extends Fragment {

    private LinearLayout selectedDistortionsContainer;
    private List<View> distortionCards;

    public NewWorryFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_new_worry2, container, false);
    }

    // TODO refactor method
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton addDistortionButton = view.findViewById(R.id.add_distortion_Button);
        selectedDistortionsContainer = view.findViewById(R.id.selectedDistortionsContainer);
        distortionCards = new ArrayList<>();
        distortionCards.add(view.findViewById(R.id.distortion_1));
        distortionCards.add(view.findViewById(R.id.distortion_2));
        distortionCards.add(view.findViewById(R.id.distortion_3));
        distortionCards.add(view.findViewById(R.id.distortion_4));
        distortionCards.add(view.findViewById(R.id.distortion_5));
        distortionCards.add(view.findViewById(R.id.distortion_6));
        distortionCards.add(view.findViewById(R.id.distortion_7));
        distortionCards.add(view.findViewById(R.id.distortion_8));
        distortionCards.add(view.findViewById(R.id.distortion_9));
        distortionCards.add(view.findViewById(R.id.distortion_10));

        for (View v : distortionCards) {
            if (v != null && v.isSelected()) {
                addToContainer(v);
            }
        }

        addDistortionButton.setOnClickListener(v -> showDistortionsFragment());
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

    // TODO finish adding logos to selectedItemsContainer
    private void addToContainer(View v) {
//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(itemResId);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        imageView.setPadding(8, 8, 8, 8); // Optional: Add padding for better appearance
//        selectedItemsContainer.addView(imageView);
        selectedDistortionsContainer.addView(v);
    }
}