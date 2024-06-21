package com.example.worryapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Viewmodel to share new Worry data among fragments when creating a new Worry
public class SharedViewModel extends ViewModel {

    private MutableLiveData<Worry> worry = new MutableLiveData<>();

    private final MutableLiveData<List<Worry>> ongoingWorries = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<List<Worry>> finishedWorries = new MutableLiveData<>(new ArrayList<>());


    public void setWorry(Worry worry) {
        this.worry.setValue(worry);
    }

    /**
     * Allows direct access to the Worry object.
     *
     * @return The current Worry object or null if no value is set.
     */
    public Worry getWorry() {
        return worry.getValue();
    }

    // Saves the current worry object to the list of Worries
    public void saveWorry() {
        Objects.requireNonNull(this.ongoingWorries.getValue()).add(worry.getValue());
    }

    public List<Worry> getOngoingWorries() {
        return ongoingWorries.getValue();
    }

    public List<Worry> getFinishedWorries() {
        return finishedWorries.getValue();
    }

    /**
     * Removes the worry from ongoingWorries and adds it to finishedWorries
     *
     * @param worry the worry to be set as finished
     */
    public void finishWorry(Worry worry) {
        Objects.requireNonNull(ongoingWorries.getValue()).remove(worry);
        if (!Objects.requireNonNull(finishedWorries.getValue()).contains(worry)) {
            finishedWorries.getValue().add(worry);
        }
        worry.setFinished();
    }

}
