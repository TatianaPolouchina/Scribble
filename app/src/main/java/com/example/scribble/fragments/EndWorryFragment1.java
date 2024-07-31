package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.scribble.R;
import com.example.scribble.Worry;

/***
 * First fragment for ending a worry, asks the user if the worry was as bad as expected
 */
public class EndWorryFragment1 extends BaseFragment {

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

        addOnClickListeners(view);

    }

    private void addOnClickListeners(@NonNull View view) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", worry);

        view.findViewById(R.id.yesButton).setOnClickListener(v -> {
            worry.setBetterThanExpected(false);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment1_to_endWorryFragment2, bundle);
        });
        view.findViewById(R.id.noButton).setOnClickListener(v -> {
            worry.setBetterThanExpected(true);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment1_to_endWorryFragment2, bundle);
        });
    }
}