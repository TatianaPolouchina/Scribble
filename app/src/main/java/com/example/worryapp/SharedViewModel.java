package com.example.worryapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

// Viewmodel to share new Worry data among fragments when creating a new Worry
public class SharedViewModel extends ViewModel {

    private MutableLiveData<Worry> worry = new MutableLiveData<>();

    private final MutableLiveData<List<Worry>> ongoingWorries = new MutableLiveData<>(new ArrayList<>());

    public void setWorry(Worry worry) {
        this.worry.setValue(worry);
    }

    /**
     * Returns the LiveData object holding the Worry.
     *
     * @return LiveData object holding a Worry.
     */
    public LiveData<Worry> getLiveData() {
        return worry;
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
        this.ongoingWorries.getValue().add(worry.getValue());
    }

    public List<Worry> getOngoingWorries() {
        return ongoingWorries.getValue();
    }

}
