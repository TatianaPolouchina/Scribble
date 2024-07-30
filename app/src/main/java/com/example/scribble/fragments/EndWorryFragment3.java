package com.example.scribble.fragments;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.scribble.R;
import com.example.scribble.Worry;

import java.util.Random;

public class EndWorryFragment3 extends WorryActionFragment {
    private Worry worry;
    private ImageView ongoingWorryImage;
    private ImageView finishedWorryImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_end_worry3, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ongoingWorryImage = view.findViewById(R.id.worryCharacter);
        finishedWorryImage = view.findViewById(R.id.finishedWorryCharacter);

        //update image
        if (getArguments() != null) {
            worry = (Worry) getArguments().getSerializable("selectedWorry");
            if (worry != null) {
                ongoingWorryImage.setImageResource(worry.getOngoingImageResId());
                finishedWorryImage.setImageResource(worry.getFinishedImageResId());
            }
        }

        randomizeMessage(view);

        fadeToFinishedWorry(view);

        view.findViewById(R.id.closeButton).setOnClickListener(v -> {
            sharedViewModel.finishWorry(worry, getContext());
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment3_to_finishedWorriesFragment);
        });

        handleBackPress();
    }

    /***
     * Randomly assigns the finished worry one of two messages. message 2 can only be assigned if
     * the worry had betterThanExpected set to true
     * @param view view containing the messages
     */
    public void randomizeMessage(View view) {
        TextView worryMessage = view.findViewById(R.id.worryFinishedMessage1);
        TextView worrySubtitle = view.findViewById(R.id.worryFinishedMessage2);

        Random random = new Random();
        if (random.nextBoolean() && worry.isBetterThanExpected()) {
            worryMessage.setText(R.string.worry_ended_message_2);
            worrySubtitle.setText(R.string.worry_ended_subtitle_2);
        } else {
            worryMessage.setText(R.string.worry_ended_message_1);
            worrySubtitle.setText(R.string.worry_ended_subtitle_1);
        }
    }

    /***
     * Disables back pressing
     */
    public void handleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    /***
     * Plays the animated transition from ongoing to finished worry
     *
     * @param view The view containing the worry images
     */
    public void fadeToFinishedWorry(View view) {
        ObjectAnimator fadeOutOngoingWorry = ObjectAnimator.ofFloat(ongoingWorryImage, "alpha", 1f, 0f);
        ObjectAnimator fadeInFinishedWorry = ObjectAnimator.ofFloat(finishedWorryImage, "alpha", 0f, 1f);
        fadeOutOngoingWorry.setDuration(1800);
        fadeInFinishedWorry.setDuration(1500);

        fadeOutOngoingWorry.start();
        fadeInFinishedWorry.start();

        VideoView videoView = view.findViewById(R.id.animation);
        Uri videoUri = Uri.parse("android.resource://" + requireContext().getPackageName() + "/" + R.raw.scribble_to_sun_animation);
        videoView.setVideoURI(videoUri);
        videoView.setVisibility(View.VISIBLE);

        videoView.setOnPreparedListener(mp -> {
            videoView.start();
            mp.setOnInfoListener((mp1, what, extra) -> {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    view.findViewById(R.id.placeholder).setVisibility(View.GONE);
                    return true;
                }
                return false;
            });
        });

    }
}