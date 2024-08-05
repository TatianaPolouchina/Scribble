package com.tatiana.scribble;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/***
 * General utility class for keyboard and screen size operations
 */
public class Utils {

    /**
     * Utility class with methods for opening and closing the keyboard
     */
    public static class KeyboardUtils {

        /**
         * Closes the user keyboard
         *
         * @param activity the current activity where the keyboard is open
         */
        public static void hideKeyboard(Activity activity) {
            View view = activity.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }

        /**
         * Opens the user keyboard
         *
         * @param editText desired text input field
         */
        public static void showKeyboard(Activity activity, EditText editText) {
            editText.post(() -> {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                }
            });
        }

    }

    /**
     * Utility class with methods for getting the screen size
     */
    public static class screenUtils {

        /***
         * Returns the screen height in pixels
         * @return Screen height in pixels
         */
        public static int getScreenHeight(View view) {
            Rect r = new Rect();
            view.getWindowVisibleDisplayFrame(r);
            return view.getRootView().getHeight();
        }

        /***
         * Returns the screen height in pixels
         * @return Screen height in pixels
         */
        public static int getScreenWidth(View view) {
            Rect r = new Rect();
            view.getWindowVisibleDisplayFrame(r);
            return view.getRootView().getWidth();
        }

        public static boolean wideScreen(View view) {
            return getScreenWidth(view) >= 1080;
        }

        public static boolean tallScreen(View view) {
            return getScreenHeight(view) >= 2300;
        }
    }
}
