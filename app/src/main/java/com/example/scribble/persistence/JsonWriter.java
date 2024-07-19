package com.example.scribble.persistence;

import com.example.scribble.Worry;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonWriter {
    private static final String FILE_NAME = "user_data.json";
    private PrintWriter writer;
    private static final int TAB = 4;


    /***
     * opens writer; throws FileNotFoundException if destination file cannot be opened for writing
     *
     * @throws FileNotFoundException if the file cannot be accessed
     */
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(FILE_NAME);
    }

    /***
     * writes all worries to the save file
     *
     * @param worries all Worry objects to be saved
     * @throws JSONException
     */
    public void write(ArrayList<Worry> worries) throws JSONException {
        JSONArray worryJSONs = new JSONArray();
        for (Worry worry : worries) {
            worryJSONs.put(worry.toJSON());
        }

        saveToFile(worryJSONs.toString(TAB));
    }

    /***
     * Closes the writer
     */
    public void close() {
        writer.close();
    }

    /***
     * writes json to the file
     *
     * @param json JSON string to be saved to the file
     */
    private void saveToFile(String json) {
        writer.print(json);
    }

}
