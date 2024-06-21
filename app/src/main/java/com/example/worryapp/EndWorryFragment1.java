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

public class EndWorryFragment1 extends Fragment {

    private Worry worry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_worry1, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                ImageView image = view.findViewById(R.id.worry_character);
                image.setImageResource(worry.getOngoingImageResId());
            }
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", worry);

        view.findViewById(R.id.yesButton).setOnClickListener(v -> {
            worry.setResult(true);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment1_to_endWorryFragment2, bundle);
        });
        view.findViewById(R.id.noButton).setOnClickListener(v -> {
            worry.setResult(false);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment1_to_endWorryFragment2, bundle);
        });

    }
}