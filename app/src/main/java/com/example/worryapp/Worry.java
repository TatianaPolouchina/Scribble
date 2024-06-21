package com.example.worryapp;

import java.io.Serializable;

public class Worry implements Serializable {

    private String title;
    private final int ongoingImageResId;
    private final int finishedImageResId;
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
    private boolean result; // true if the worry ended up being as bad as expected
    private String howItEnded; // description of how the worry ended
    public boolean finished;

    public Worry(String title, int ongoingImageResId, int finishedImageResId) {
        this.title = title;
        this.ongoingImageResId = ongoingImageResId;
        this.finishedImageResId = finishedImageResId;
    }

    public Worry() {
        this.title = "";
        this.ongoingImageResId = R.drawable.worry_1;
        this.finishedImageResId = R.drawable.worry_1_finished_ombre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getOngoingImageResId() {
        return ongoingImageResId;
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

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getHowItEnded() {
        return howItEnded;
    }

    public void setHowItEnded(String howItEnded) {
        this.howItEnded = howItEnded;
    }

    public int getFinishedImageResId() {
        return finishedImageResId;
    }

    public void setFinished() {
        this.finished = true;
    }

    public boolean isFinished() {
        return finished;
    }
}
