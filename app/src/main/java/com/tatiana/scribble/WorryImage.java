package com.tatiana.scribble;

/***
 * Represents a pair of corresponding Worry images
 */
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
