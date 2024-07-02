package com.example.scribble;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class NewWorryFragment2 extends Fragment {

    private SharedViewModel sharedViewModel;

    public NewWorryFragment2() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_worry2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.skipButton).setOnClickListener(v -> {
                sharedViewModel.getWorry().setDescription("");
                NavHostFragment.findNavController(this).navigate
                        (R.id.action_newWorryFragment2_to_newWorryFragment3);
        });

        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            TextInputEditText textInputField = view.findViewById(R.id.textInputField);
            sharedViewModel.getWorry().setDescription(Objects.requireNonNull
                    (textInputField.getText()).toString());

            NavHostFragment.findNavController(this).navigate
                    (R.id.action_newWorryFragment2_to_newWorryFragment3);
        });
    }
}