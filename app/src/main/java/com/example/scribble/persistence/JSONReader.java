package com.example.scribble.persistence;

import com.example.scribble.Worry;
import com.example.scribble.WorryImageHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/***
 * Reader that reads the worries and worry image helper from the source file
 */
public class JSONReader {
    private final File source;
    private final JSONObject jsonData;

    /***
     * constructs reader to read from source file
     *
     * @param source file to be read from
     * @throws JSONException if there is an error parsing the JSON data
     */
    public JSONReader(File source) throws JSONException {
        this.source = source;
        String jsonString = readFromFile();
        this.jsonData = new JSONObject(jsonString);
    }

    /***
     * Returns the source file's contents as a String
     *
     * @return file contents as a String
     */
    public String readFromFile() {
        StringBuilder text = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException ignored) {
        }
        return text.toString();
    }

    /***
     * Reads and returns the WorryImageHelper
     *
     * @return WorryImageHelper from the file
     */
    public WorryImageHelper readWorryImageHelper() {
        WorryImageHelper worryImageHelper = new WorryImageHelper();
        try {
            worryImageHelper.setUnchosenKeys(readUnchosenKeys());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return worryImageHelper;
    }

    /***
     * Reads and returns the list of unchosenKeys from the JSONArray
     *
     * @return the saved unchosenKeys as a list of Integers
     * @throws JSONException if the JSON array cannot be accessed
     */
    public List<String> readUnchosenKeys() throws JSONException {
        List<String> unchosenKeys = new ArrayList<>();
        JSONArray unchosenKeysJSON = jsonData.getJSONArray("worryImageUnchosenKeys");
        for (int i = 0; i < unchosenKeysJSON.length(); i++) {
            unchosenKeys.add(unchosenKeysJSON.getString(i));
        }
        return unchosenKeys;
    }

    /***
     * Returns the list of ongoing worries from the source file
     *
     * @return list of all ongoing worries
     */
    public List<Worry> readOngoingWorries() {
        JSONArray jsonOngoingWorries;
        try {
            jsonOngoingWorries = jsonData.getJSONArray("ongoingWorries");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return parseWorries(jsonOngoingWorries);
    }

    /***
     * Returns the list of finished worries from the source file
     *
     * @return list of all finished worries
     */
    public List<Worry> readFinishedWorries() {
        JSONArray JSONFinishedWorries;
        try {
            JSONFinishedWorries = jsonData.getJSONArray("finishedWorries");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return parseWorries(JSONFinishedWorries);
    }

    /***
     * Converts and returns the array of JSONObjects into an array of type Worry
     *
     * @param worryListJSON JSONArray of worries
     * @return ArrayList of worries
     */
    public List<Worry> parseWorries(JSONArray worryListJSON) {
        List<Worry> worries = new ArrayList<>();
        for (int i = 0; i < worryListJSON.length(); i++) {
            try {
                worries.add(parseWorry(worryListJSON.getJSONObject(i)));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        return worries;
    }

    /***
     * Returns the Worry contained in the JSONObject
     *
     * @param json JSONObject containing the Worry
     * @return Worry object if there is an error parsing the JSON data
     * @throws JSONException if the JSON cannot be created
     */
    public Worry parseWorry(JSONObject json) throws JSONException {
        Worry worry = new Worry();
        worry.setTitle(json.getString("title"));
        String worryImageKey = json.getString("worryImageKey");
        worry.setWorryImageKey(worryImageKey);
        worry.setWorryImage(new WorryImageHelper().getWorryImage(worryImageKey));
        worry.setDescription(json.getString("description"));
        worry.setOvergeneralizing(json.getBoolean("overgeneralizing"));
        worry.setMindReading(json.getBoolean("mindReading"));
        worry.setFortuneTelling(json.getBoolean("fortuneTelling"));
        worry.setCatastrophizing(json.getBoolean("catastrophizing"));
        worry.setAllOrNothing(json.getBoolean("allOrNothing"));
        worry.setNegMentalFilter(json.getBoolean("negMentalFilter"));
        worry.setDisqualifyPositive(json.getBoolean("disqualifyPositive"));
        worry.setPersonalization(json.getBoolean("personalization"));
        worry.setEmotionalReasoning(json.getBoolean("emotionalReasoning"));
        worry.setLabelling(json.getBoolean("labelling"));
        worry.setBetterThanExpected(json.getBoolean("betterThanExpected"));
        worry.setHowItEnded(json.getString("howItEnded"));
        worry.setFinished(json.getBoolean("finished"));
        worry.setResponses(getResponses(json));
        return worry;
    }

    /***
     * Converts and returns the array of JSONObjects into an array of type String
     *
     * @param json JSONArray of worries
     * @return ArrayList of responses as Strings
     * @throws JSONException if there is an error parsing the JSON data
     */
    private List<String> getResponses(JSONObject json) throws JSONException {
        JSONArray jsonArray = json.getJSONArray("responses");
        List<String> responses = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            responses.add(jsonArray.getString(i));
        }
        return responses;
    }
}
