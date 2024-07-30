package com.example.scribble.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.navigation.fragment.NavHostFragment;

import com.example.scribble.R;

public class NewWorryFinishedFragment extends WorryActionFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry_finished, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());

        view.findViewById(R.id.finish_button).setOnClickListener(v -> {
            sharedViewModel.saveWorry(getContext());
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_newWorryFinishedFragment_to_ongoingWorriesFragment);
        });
    }
}
