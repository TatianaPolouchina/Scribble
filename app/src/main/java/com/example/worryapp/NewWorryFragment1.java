package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

    public NewWorryFragment1() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_worry, container, false);

        instruction = view.findViewById(R.id.instruction);
        textInputField = view.findViewById(R.id.textInputField);
        backButton = view.findViewById(R.id.back_button);

        view.findViewById(R.id.next_button).setOnClickListener(v -> {
            instruction.setText(R.string.new_worry_instruction_2);
            textInputField.setText("");
            backButton.setVisibility(view.GONE);
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);

    }

    //!!!
    private void openNewFragment() {
        NewWorryFragment2 newWorryFragment = new NewWorryFragment2();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.main, newWorryFragment);
        transaction.commit();
    }
}