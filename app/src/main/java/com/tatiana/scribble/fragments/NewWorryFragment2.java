package com.tatiana.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tatiana.scribble.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

/***
 * 2nd fragment for creating a new worry, asks for a description
 */
public class NewWorryFragment2 extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextInputEditText textInputField = view.findViewById(R.id.textInputField);
        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());
        updateKeyboard(view, textInputField);
        addOnClickListeners(view, textInputField);
    }

    /***
     * Adds all on click listeners to Views
     *
     * @param view container View
     */
    private void addOnClickListeners(@NonNull View view, TextInputEditText textInputField) {
        view.findViewById(R.id.skipButton).setOnClickListener(v -> {
            sharedViewModel.getWorry().setDescription("");
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_newWorryFragment2_to_newWorryFragment3);
        });
        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            sharedViewModel.getWorry().setDescription(Objects.requireNonNull
                    (textInputField.getText()).toString());
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_newWorryFragment2_to_newWorryFragment3);
        });
    }
}