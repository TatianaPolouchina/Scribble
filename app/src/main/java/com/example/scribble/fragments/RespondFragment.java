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
import com.example.scribble.Worry;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

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
        loadWorry(view);
        setUpSubmitButton(view);
        TextInputEditText textInput = view.findViewById(R.id.textInputField);
        updateKeyboard(view, textInput);
    }

    /***
     * Loads in worry data from arguments
     *
     * @param view container View
     */
    private void loadWorry(@NonNull View view) {
        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                ImageView image = view.findViewById(R.id.worryCharacter);
                image.setImageResource(worry.getOngoingImageResId());
            }
        }
    }

    /***
     * Adds the on click listener functionality to the submit button
     *
     * @param view container View
     */
    private void setUpSubmitButton(@NonNull View view) {
        view.findViewById(R.id.submitButton).setOnClickListener(v -> {
            saveResponse(view);
            navigateToNextFragment();
            showDelayedToast();
        });
    }

    /***
     * Saves the entered response to the worry and to the data file
     *
     * @param view container View
     */
    private void saveResponse(@NonNull View view) {
        TextInputEditText textInputField = view.findViewById(R.id.textInputField);
        worry.addResponse(Objects.requireNonNull
                (textInputField.getText()).toString());
        sharedViewModel.saveData(getContext());
    }

    /***
     * Navigates to the worry fragment
     */
    private void navigateToNextFragment() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", worry);
        NavHostFragment.findNavController(this).navigate
                (R.id.action_respondFragment_to_worryFragment, bundle);
    }

    /***
     * Shows a delayed toast showing the worry is saved
     */
    private void showDelayedToast() {
        new Handler().postDelayed(() -> {
            if (getContext() != null) {
                Toast.makeText(getContext(), R.string.response_saved_text, Toast.LENGTH_SHORT).show();
            }
        }, 200);
    }
}