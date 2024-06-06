package com.example.worryapp;

public class WorryItem {

    private String title;

    private int imageResId;

    public WorryItem(String title, int imageResId) {
        this.title = title;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResId() {
        return imageResId;
    }

}
