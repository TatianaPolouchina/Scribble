package com.example.scribble;

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

import com.example.scribble.ui.theme.ReminderHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NewWorryFragment1 extends TextInputFragment {
    private SharedViewModel sharedViewModel;
    private ReminderHelper reminderHelper;
    private TextInputEditText textInputField;

    public NewWorryFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        this.reminderHelper = new ReminderHelper(requireContext());
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
        textInputField = view.findViewById(R.id.textInputField);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
        sharedViewModel.setWorry(new Worry(reminderHelper));
        addOnClickListeners(view);
        textInputField.requestFocus();
        showKeyboard(textInputField);
    }

    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            if (!Objects.requireNonNull(textInputField.getText()).toString().isEmpty()) {
                sharedViewModel.getWorry().setTitle(Objects.requireNonNull
                        (textInputField.getText()).toString());
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment1_to_newWorryFragment2);
            } else {
                view.findViewById(R.id.warningText).setVisibility(View.VISIBLE);
            }
        });

        view.findViewById(R.id.backButton).setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(v);
            navController.popBackStack();
        });
    }


}