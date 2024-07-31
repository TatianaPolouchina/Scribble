package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.scribble.R;
import com.example.scribble.Utils;
import com.example.scribble.Worry;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

//TODO: add comments to all

/***
 * Fragment allowing user to input a response to the worry
 */
public class RespondFragment extends BaseFragment {
    private Worry worry;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_respond, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupWorry(view);
        setUpSubmitButton(view);
        setupKeyboard(view);
    }

    private void setupWorry(@NonNull View view) {
        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                ImageView image = view.findViewById(R.id.worryCharacter);
                image.setImageResource(worry.getOngoingImageResId());
            }
        }
    }

    private void setupKeyboard(@NonNull View view) {
        TextInputEditText textInput = view.findViewById(R.id.textInputField);
        if (Utils.screenUtils.tallScreen(view)) {
            textInput.requestFocus();
            Utils.KeyboardUtils.showKeyboard(requireActivity(), textInput);
        }
    }

    private void setUpSubmitButton(@NonNull View view) {
        view.findViewById(R.id.submitButton).setOnClickListener(v -> {
            TextInputEditText textInputField = view.findViewById(R.id.textInputField);
            worry.addResponse(Objects.requireNonNull
                    (textInputField.getText()).toString());
            sharedViewModel.saveData(getContext());

            Bundle bundle = new Bundle();
            bundle.putSerializable("selectedWorry", worry);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_respondFragment_to_worryFragment, bundle);

            new Handler().postDelayed(() -> {
                if (getContext() != null) {
                    Toast.makeText(getContext(), R.string.response_saved_text, Toast.LENGTH_SHORT).show();
                }
            }, 200);

        });
    }
}