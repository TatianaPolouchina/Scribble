package com.tatiana.scribble;

import android.content.Context;

import java.util.Random;

/***
 * Manages the reminders and hints from strings.xml
 */
public class StringHelper {

    private static String[] reminders;
    private static String[] hints;


    /***
     * Imports the reminders and hints from the context's Strings.xml file
     *
     * @param context Context of strings.xml containing the reminders and hints
     */
    public static void setupStringHelper(Context context) {
        StringHelper.reminders = context.getResources().getStringArray(R.array.reminders);
        StringHelper.hints = context.getResources().getStringArray(R.array.new_worry_instruction_1_hints);
    }

    /**
     * Returns a random reminder string from strings.xml
     */
    public static String getRandomReminder() {
        Random random = new Random();
        int randomIndex = random.nextInt(reminders.length);
        return reminders[randomIndex];
    }

    /**
     * Returns a random hint string from strings.xml
     */
    public static String getRandomHint() {
        Random random = new Random();
        int randomIndex = random.nextInt(hints.length);
        return hints[randomIndex];
    }

}
