package com.example.scribble.persistence;

import android.content.Context;

import com.example.scribble.Worry;
import com.example.scribble.WorryImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO: fix comments and comment all
public class JSONReader {
    private final String source;
    private final Context context;
    private final JSONObject jsonData;

    // EFFECTS: constructs reader to read from source file
    public JSONReader(String source, Context context) throws JSONException {
        this.source = source;
        this.context = context;
        String jsonString = readFromFile();
        this.jsonData = new JSONObject(jsonString);
    }


    public String readFromFile() {
        File file = new File(context.getFilesDir(), source);
        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text.toString();
    }

    //TODO: comment
    public List<Worry> readOngoingWorries() throws JSONException {
        JSONArray jsonOngoingWorries = jsonData.getJSONArray("ongoingWorries");
        return parseWorries(jsonOngoingWorries);
    }

    public List<Worry> readFinishedWorries() throws JSONException {
        JSONArray jsonfinishedWorries = jsonData.getJSONArray("finishedWorries");
        return parseWorries(jsonfinishedWorries);
    }

    //TODO: comment
    public List<Worry> parseWorries(JSONArray worryListJSON) throws JSONException {
        List<Worry> worries = new ArrayList<>();

        for (int i = 0; i < worryListJSON.length(); i++) {
            worries.add(parseWorry(worryListJSON.getJSONObject(i)));
        }
        return worries;
    }

    //TODO: comment
    public Worry parseWorry(JSONObject json) throws JSONException {
        Worry worry = new Worry();
        worry.setTitle(json.getString("title"));
        int ongoingImageID = json.getInt("ongoingImageID");
        int finishedImageID = json.getInt("finishedImageID");
        worry.setWorryImage(new WorryImage(ongoingImageID, finishedImageID));
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

    private List<String> getResponses(JSONObject json) throws JSONException {
        JSONArray jsonArray = json.getJSONArray("responses");
        List<String> responses = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            responses.add(jsonArray.getString(i));
        }
        return responses;
    }
}
