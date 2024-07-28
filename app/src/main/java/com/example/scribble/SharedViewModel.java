package com.example.scribble;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.scribble.persistence.JSONWriter;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Viewmodel to share new Worry data among fragments when creating a new Worry
public class SharedViewModel extends ViewModel {

    private final MutableLiveData<Worry> worry = new MutableLiveData<>();
    private MutableLiveData<List<Worry>> ongoingWorries;
    private MutableLiveData<List<Worry>> finishedWorries;
    private static final String JSON_STORE = "data.json";
    private JSONWriter jsonWriter;
    private WorryImageHelper worryImageHelper;

    public SharedViewModel() {
        super();
        this.ongoingWorries = new MutableLiveData<>();
        this.ongoingWorries.setValue(new ArrayList<>());
        this.finishedWorries = new MutableLiveData<>();
        this.finishedWorries.setValue(new ArrayList<>());
    }

    //TODO: comment and fix
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

    // Saves the current worry object to the beginning of the list of Worries and to internal
    // storage
    public void saveWorry(Context context) {
        if (this.jsonWriter == null) {
            jsonWriter = new JSONWriter(JSON_STORE, context);
        }

        if (this.ongoingWorries == null) {
            ongoingWorries = new MutableLiveData<>();
            ongoingWorries.setValue(new ArrayList<>());
        }
        Objects.requireNonNull(this.ongoingWorries.getValue()).add(0, worry.getValue());
        saveData(context);
    }

    // TODO: comment
    public void saveData(Context context) {
        if (this.jsonWriter == null) {
            jsonWriter = new JSONWriter(JSON_STORE, context);
        }
        try {
            jsonWriter.open();
            jsonWriter.write(ongoingWorries.getValue(), finishedWorries.getValue(), worryImageHelper);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            // nothing happens
        } catch (JSONException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Worry> getOngoingWorries() {
        return this.ongoingWorries.getValue();
    }

    public MutableLiveData<List<Worry>> getOngoingWorriesLiveData() {
        return this.ongoingWorries;
    }

    public void setOngoingWorries(List<Worry> ongoingWorries) {
        this.ongoingWorries.setValue(ongoingWorries);
    }

    public List<Worry> getFinishedWorries() {
        return this.finishedWorries.getValue();
    }

    public MutableLiveData<List<Worry>> getFinishedWorriesLiveData() {
        return this.finishedWorries;
    }

    public void setFinishedWorries(List<Worry> finishedWorries) {
        this.finishedWorries.setValue(finishedWorries);
    }

    /**
     * Removes the worry from ongoingWorries and adds it to the beginning of finishedWorries
     *
     * @param worry the worry to be set as finished
     */
    public void finishWorry(Worry worry, Context context) {
        Objects.requireNonNull(ongoingWorries.getValue()).remove(worry);
        if (!Objects.requireNonNull(finishedWorries.getValue()).contains(worry)) {
            finishedWorries.getValue().add(0, worry);
        }
        worry.finish();
        saveData(context);
    }

    public void setWorryImageHelper(WorryImageHelper worryImageHelper) {
        this.worryImageHelper = worryImageHelper;
    }

    public WorryImageHelper getWorryImageHelper() {
        if (this.worryImageHelper == null) {
            this.worryImageHelper = new WorryImageHelper();
        }
        return worryImageHelper;
    }
}
