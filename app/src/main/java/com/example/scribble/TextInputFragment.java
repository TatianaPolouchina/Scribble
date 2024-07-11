package com.example.scribble;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class TextInputFragment extends Fragment {

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

}
