package com.example.scribble.fragments;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.scribble.R;
import com.example.scribble.SharedViewModel;

// For fragments that create, respond to, or delete worries. These fragments have a worry image
// with ID "worryImageLayout" and instruction with ID "instruction", and a ScrollView with ID
// scrollView
public abstract class WorryActionFragment extends Fragment {
    protected SharedViewModel sharedViewModel;
    protected ScrollView scrollView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollView = view.findViewById(R.id.scrollView);
    }

    /**
     * Opens the user keyboard
     *
     * @param editText desired text input field
     */
    protected void showKeyboard(EditText editText) {
        editText.post(() -> {
            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    /***
     * Returns the screen height in pixels
     * @return Screen height in pixels
     */
    protected int getScreenHeight() {
        Rect r = new Rect();
        requireView().getWindowVisibleDisplayFrame(r);
        return requireView().getRootView().getHeight();
    }

    /***
     * Returns the screen height in pixels
     * @return Screen height in pixels
     */
    protected int getScreenWidth() {
        Rect r = new Rect();
        requireView().getWindowVisibleDisplayFrame(r);
        return requireView().getRootView().getWidth();
    }

    protected boolean wideScreen() {
        return getScreenWidth() >= 1080;
    }

    protected boolean tallScreen() {
        return getScreenHeight() >= 2300;
    }
}
