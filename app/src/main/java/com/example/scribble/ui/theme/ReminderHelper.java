package com.example.scribble.ui.theme;

import android.content.Context;

import com.example.scribble.R;

import java.util.Random;

// Manages the reminders from strings.xml
public class ReminderHelper {
    private final String[] reminders;

    public ReminderHelper(Context context) {
        reminders = context.getResources().getStringArray(R.array.reminders);
    }

    /**
     * Returns a random reminder string from strings.xml
     */
    public String getRandomReminder() {
        Random random = new Random();
        int randomIndex = random.nextInt(reminders.length);
        return reminders[randomIndex];
    }

}
