package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class EndWorryFragment2 extends Fragment {

    private Worry worry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_worry2, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                ImageView image = view.findViewById(R.id.worryCharacter);
                image.setImageResource(worry.getOngoingImageResId());
            }
        }

        view.findViewById(R.id.skipButton).setOnClickListener(v -> {
            worry.setHowItEnded("");
            nextFragment();
        });

        view.findViewById(R.id.finishWorryButton).setOnClickListener(v -> {
            // Update how the worry ended
            TextInputEditText textInputField = view.findViewById(R.id.textInputField);
            worry.setHowItEnded(Objects.requireNonNull
                    (textInputField.getText()).toString());
            nextFragment();
        });
    }

    /**
     * Replaces the current fragment with the next fragment, passing the finished worry in a bundle
     */
    private void nextFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", worry);
        NavHostFragment.findNavController(this).navigate
                (R.id.action_endWorryFragment2_to_endWorryFragment3, bundle);
    }
}