package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.worryapp.ui.theme.ReminderHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NewWorryFragment1 extends Fragment {
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

        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            //check if named
           if (!Objects.requireNonNull(textInputField.getText()).toString().isEmpty()) {
               //update worry title
               sharedViewModel.getWorry().setTitle(Objects.requireNonNull
                       (textInputField.getText()).toString());

               NavHostFragment.findNavController(this).navigate
                       (R.id.action_newWorryFragment1_to_newWorryFragment2);
           } else {
               view.findViewById(R.id.warningText).setVisibility(View.VISIBLE);
           }

        });

    }
}