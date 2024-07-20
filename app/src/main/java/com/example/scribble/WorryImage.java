package com.example.scribble;

public class WorryImage {
    private final int ongoingImageResId;
    private final int finishedImageResId;

    public WorryImage(int ongoingImageResId, int finishedImageResId) {
        this.ongoingImageResId = ongoingImageResId;
        this.finishedImageResId = finishedImageResId;
    }


    public int getOngoingImageResId() {
        return ongoingImageResId;
    }

    public int getFinishedImageResId() {
        return finishedImageResId;
    }
}
