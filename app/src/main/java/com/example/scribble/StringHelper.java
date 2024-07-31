package com.example.scribble;

import android.content.Context;

import java.util.Random;

/***
 * Manages the reminders and hints from strings.xml
 */
public class StringHelper {
    private final String[] reminders;
    private final String[] hints;


    public StringHelper(Context context) {
        reminders = context.getResources().getStringArray(R.array.reminders);
        hints = context.getResources().getStringArray(R.array.new_worry_instruction_1_hints);
    }

    /**
     * Returns a random reminder string from strings.xml
     */
    public String getRandomReminder() {
        Random random = new Random();
        int randomIndex = random.nextInt(reminders.length);
        return reminders[randomIndex];
    }

    /**
     * Returns a random hint string from strings.xml
     */
    public String getRandomHint() {
        Random random = new Random();
        int randomIndex = random.nextInt(hints.length);
        return hints[randomIndex];
    }

}
