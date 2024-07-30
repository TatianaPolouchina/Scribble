package com.example.scribble.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.scribble.MainActivity;
import com.example.scribble.R;
import com.example.scribble.Utils;
import com.example.scribble.Worry;
import com.example.scribble.StringHelper;
import com.example.scribble.WorryImageHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NewWorryFragment1 extends BaseFragment {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputField = view.findViewById(R.id.textInputField);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
        setupWorry(view);
        addOnClickListeners(view);
        if (Utils.screenUtils.tallScreen(view)) {
            displayKeyboard();
        }
    }

    private void setupWorry(@NonNull View view) {
        sharedViewModel.setWorry(new Worry(stringHelper, worryImageHelper));
        ImageView worryImage = view.findViewById(R.id.worryCharacter);
        worryImage.setImageResource(sharedViewModel.getWorry().getOngoingImageResId());
    }

    // TODO: comment
    private void displayKeyboard() {
        textInputField.requestFocus();
        Utils.KeyboardUtils.showKeyboard(requireActivity(), textInputField);
        //showKeyboard(textInputField);
        textInputField.setHint(stringHelper.getRandomHint());
    }

    // TODO: comment
    @SuppressLint("ShowToast")
    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            if (!Objects.requireNonNull(textInputField.getText()).toString().isEmpty()) {
                sharedViewModel.getWorry().setTitle(Objects.requireNonNull
                        (textInputField.getText()).toString());
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment1_to_newWorryFragment2);
            } else {
                Snackbar snackbar = Snackbar.make(view, R.string.no_name_entered_warning_text, 1500);
                snackbar.setBackgroundTint(requireContext().getColor(R.color.lightBlue));
                snackbar.show();
            }
        });

        view.findViewById(R.id.backButton).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.popBackStack();
        });
    }


}