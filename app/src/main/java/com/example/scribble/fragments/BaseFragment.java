package com.example.scribble.fragments;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.scribble.SharedViewModel;
import com.example.scribble.Utils;
import com.google.android.material.textfield.TextInputEditText;

/***
 * Fragment class containing a sharedViewModel
 */
public abstract class BaseFragment extends Fragment {
    protected SharedViewModel sharedViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /***
     * Opens the keyboard if tallScreen is true
     * @param view container view
     * @param textInput TextInputEditText where text should appear
     */
    protected void updateKeyboard(@NonNull View view, TextInputEditText textInput) {
        if (Utils.screenUtils.tallScreen(view)) {
            textInput.requestFocus();
            Utils.KeyboardUtils.showKeyboard(requireActivity(), textInput);
        }
    }
}
