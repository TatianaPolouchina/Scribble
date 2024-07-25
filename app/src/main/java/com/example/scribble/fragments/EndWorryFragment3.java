package com.example.scribble.fragments;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import com.example.scribble.R;
import com.example.scribble.SharedViewModel;
import com.example.scribble.Worry;

public class EndWorryFragment3 extends Fragment {
    private SharedViewModel sharedViewModel;
    private Worry worry;
    private ImageView ongoingWorryImage;
    private ImageView finishedWorryImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
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

        fadeToFinishedWorry(view);

        view.findViewById(R.id.closeButton).setOnClickListener(v -> {
            sharedViewModel.finishWorry(worry);
            NavHostFragment.findNavController(this).navigate
                    (R.id.action_endWorryFragment3_to_finishedWorriesFragment);
        });
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