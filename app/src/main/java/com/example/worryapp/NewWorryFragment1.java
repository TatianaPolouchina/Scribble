package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;


public class NewWorryFragment1 extends Fragment {

    private TextView instruction;
    private TextInputEditText textInputField;
    private ImageButton backButton;
    private boolean secondFrame;

    public NewWorryFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    // TODO: refactor
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_worry, container, false);

        instruction = view.findViewById(R.id.instruction);
        textInputField = view.findViewById(R.id.textInputField);
        backButton = view.findViewById(R.id.back_button);
        secondFrame = false;

        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            if (!secondFrame) {
                instruction.setText(R.string.new_worry_instruction_2);
                textInputField.setText("");
                backButton.setVisibility(view.GONE);
                secondFrame = true;
            } else {
                NavHostFragment.findNavController(this).navigate
                        (R.id.newWorryFragment2);
            }

        });

        view.findViewById(R.id.back_button).setOnClickListener(v -> NavHostFragment.
                findNavController(this).navigate(R.id.ongoingWorriesFragment));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);
    }
}