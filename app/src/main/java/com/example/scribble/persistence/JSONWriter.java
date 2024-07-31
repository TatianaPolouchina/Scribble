package com.example.scribble.persistence;

import android.content.Context;

import com.example.scribble.Worry;
import com.example.scribble.WorryImageHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//TODO: fix comments
/***
 * Writer that writes the worries and worry image helper to the source file
 */
public class JSONWriter {
    private PrintWriter writer;
    private final File file;


    // EFFECTS: constructs writer to write to destination file
    public JSONWriter(String destination, Context context) {
        file = new File(context.getFilesDir(), destination);
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws IOException {
        writer = new PrintWriter(new FileWriter(file));
    }

    /***
     * Writes the ongoing worries and finished worries to the file
     *
     * @throws JSONException
     */
    public void write(List<Worry> ongoingWorries, List<Worry> finishedWorries,
                      WorryImageHelper imageHelper) throws JSONException {
        JSONObject json = new JSONObject();
        JSONArray ongoingWorriesJSON = worryListToJSON(ongoingWorries);
        JSONArray finishedWorriesJSON = worryListToJSON(finishedWorries);
        JSONArray worryImageIndexList = imageHelperToJSON(imageHelper);
        json.put("ongoingWorries", ongoingWorriesJSON);
        json.put("finishedWorries", finishedWorriesJSON);
        json.put("worryImageIndexList", worryImageIndexList);
        saveToFile(json.toString());
    }


    //TODO: comment
    public JSONArray worryListToJSON(List<Worry> ongoingWorries) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (Worry worry : ongoingWorries) {
            jsonArray.put(worry.toJSON());
        }
        return jsonArray;
    }

    protected JSONArray imageHelperToJSON(WorryImageHelper imageHelper) {
        JSONArray indexListJSON = new JSONArray();
        for (Integer element: imageHelper.getIndexList()) {
            indexListJSON.put(element);
        }
        return indexListJSON;
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

}
