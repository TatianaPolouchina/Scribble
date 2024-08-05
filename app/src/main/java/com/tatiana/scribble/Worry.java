package com.tatiana.scribble;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/***
 * Represents a Worry with a title, description, image, cognitive distortions, and other information
 */
public class Worry implements Serializable {
    private String title;
    private String worryImageKey;
    private WorryImage worryImage;
    private String description;
    private boolean overgeneralizing;
    private boolean mindReading;
    private boolean fortuneTelling;
    private boolean catastrophizing;
    private boolean allOrNothing;
    private boolean negMentalFilter;
    private boolean disqualifyPositive;
    private boolean personalization;
    private boolean emotionalReasoning;
    private boolean labelling;
    private boolean betterThanExpected; // true if the worry ended up being better than expected
    private String howItEnded; // description of how the worry ended
    public boolean finished;
    private List<String> responses;

    public Worry(WorryImageHelper worryImageHelper) {
        this.title = "";
        this.howItEnded = "";
        this.worryImageKey = worryImageHelper.getRandomKey();
        this.worryImage = worryImageHelper.getWorryImage(worryImageKey);
        this.responses = new ArrayList<>();
        addResponse(StringHelper.getRandomReminder());
    }

    /***
     * Constructor for loading in new worries (not for creating new ones)
     */
    public Worry() {
    }

    /***
     * Parses the Worry into a JSON object
     * @return this as a JSON object
     */
    public JSONObject toJSON() throws JSONException {
        JSONObject worryJSON = new JSONObject();
        worryJSON.put("title", title);
        worryJSON.put("worryImageKey", worryImageKey);
        worryJSON.put("description", description);
        worryJSON.put("overgeneralizing", overgeneralizing);
        worryJSON.put("mindReading", mindReading);
        worryJSON.put("fortuneTelling", fortuneTelling);
        worryJSON.put("catastrophizing", catastrophizing);
        worryJSON.put("allOrNothing", allOrNothing);
        worryJSON.put("negMentalFilter", negMentalFilter);
        worryJSON.put("disqualifyPositive", disqualifyPositive);
        worryJSON.put("personalization", personalization);
        worryJSON.put("emotionalReasoning", emotionalReasoning);
        worryJSON.put("labelling", labelling);
        worryJSON.put("betterThanExpected", betterThanExpected);
        worryJSON.put("howItEnded", howItEnded);
        worryJSON.put("finished", finished);
        worryJSON.put("responses", responsesToJson());
        return worryJSON;
    }

    /***
     * Parses the Worry responses into a JSON object
     *
     * @return worry responses as a JSONArray object
     */
    private JSONArray responsesToJson() {
        JSONArray json = new JSONArray();
        for (String response : responses) {
            json.put(response);
        }
        return json;
    }

    /**
     * Adds a response to the front of a list of responses/reminders
     *
     * @param response the response to be added
     **/
    public void addResponse(String response) {
        this.responses.add(0, response);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setWorryImageKey(String worryImageKey) {
        this.worryImageKey = worryImageKey;
    }

    public void setWorryImage(WorryImage image) {
        this.worryImage = image;
    }

    public int getOngoingImageResId() {
        return worryImage.getOngoingImageResId();
    }

    public int getFinishedImageResId() {
        return worryImage.getFinishedImageResId();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setOvergeneralizing(boolean overgeneralizing) {
        this.overgeneralizing = overgeneralizing;
    }

    public void setMindReading(boolean mindReading) {
        this.mindReading = mindReading;
    }

    public void setFortuneTelling(boolean fortuneTelling) {
        this.fortuneTelling = fortuneTelling;
    }

    public void setCatastrophizing(boolean catastrophizing) {
        this.catastrophizing = catastrophizing;
    }

    public void setAllOrNothing(boolean allOrNothing) {
        this.allOrNothing = allOrNothing;
    }

    public void setNegMentalFilter(boolean negMentalFilter) {
        this.negMentalFilter = negMentalFilter;
    }

    public void setDisqualifyPositive(boolean disqualifyPositive) {
        this.disqualifyPositive = disqualifyPositive;
    }

    public void setPersonalization(boolean personalization) {
        this.personalization = personalization;
    }

    public void setEmotionalReasoning(boolean emotionalReasoning) {
        this.emotionalReasoning = emotionalReasoning;
    }

    public void setLabelling(boolean labelling) {
        this.labelling = labelling;
    }

    public boolean isOvergeneralizing() {
        return overgeneralizing;
    }

    public boolean isMindReading() {
        return mindReading;
    }

    public boolean isFortuneTelling() {
        return fortuneTelling;
    }

    public boolean isCatastrophizing() {
        return catastrophizing;
    }

    public boolean isAllOrNothing() {
        return allOrNothing;
    }

    public boolean isNegMentalFilter() {
        return negMentalFilter;
    }

    public boolean isDisqualifyPositive() {
        return disqualifyPositive;
    }

    public boolean isPersonalization() {
        return personalization;
    }

    public boolean isEmotionalReasoning() {
        return emotionalReasoning;
    }

    public boolean isLabelling() {
        return labelling;
    }

    public boolean isBetterThanExpected() {
        return betterThanExpected;
    }

    public void setBetterThanExpected(boolean betterThanExpected) {
        this.betterThanExpected = betterThanExpected;
    }

    public String getHowItEnded() {
        return howItEnded;
    }

    public void setHowItEnded(String howItEnded) {
        this.howItEnded = howItEnded;
    }

    public void finish() {
        this.finished = true;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }


    public boolean isFinished() {
        return finished;
    }

    public List<String> getResponses() {
        return responses;
    }

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }
}
