package com.example.scribble.fragments;

import android.graphics.Rect;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.scribble.MainActivity;
import com.example.scribble.R;
import com.example.scribble.SharedViewModel;
import com.example.scribble.Worry;
import com.example.scribble.StringHelper;
import com.example.scribble.WorryImageHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NewWorryFragment1 extends WorryActionFragment {
    private StringHelper stringHelper;
    private WorryImageHelper worryImageHelper;
    private TextInputEditText textInputField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: should these be initialized here? (ensure they are only created once)
        this.stringHelper = new StringHelper(requireContext());
        this.worryImageHelper = sharedViewModel.getWorryImageHelper();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry, container, false);
    }

    @Override
    // TODO: refactor
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
        sharedViewModel.setWorry(new Worry(stringHelper, worryImageHelper));

        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());

        addOnClickListeners(view);
        displayKeyboard(view);
    }

    // TODO: comment
    private void displayKeyboard(@NonNull View view) {
        textInputField = view.findViewById(R.id.textInputField);
        textInputField.requestFocus();
        showKeyboard(textInputField);
        textInputField.setHint(stringHelper.getRandomHint());
    }

    // TODO: comment
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            if (!Objects.requireNonNull(textInputField.getText()).toString().isEmpty()) {
                sharedViewModel.getWorry().setTitle(Objects.requireNonNull
                        (textInputField.getText()).toString());
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment1_to_newWorryFragment2);
            } else {
                //view.findViewById(R.id.warningText).setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.backButton).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.popBackStack();
        });
    }


}