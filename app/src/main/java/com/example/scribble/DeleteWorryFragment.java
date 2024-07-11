package com.example.scribble;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class DeleteWorryFragment extends DialogFragment {

    private final boolean finished;
    private SharedViewModel sharedViewModel;
    private final Worry worry;

    public DeleteWorryFragment(Worry worry) {
        this.worry = worry;
        this.finished = worry.isFinished();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        return inflater.inflate(R.layout.fragment_delete_worry, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        updateColours(view);
        addOnClickListeners(view);
    }

    private void addOnClickListeners(@NonNull View view) {
        view.findViewById(R.id.confirmDeleteButton).setOnClickListener(v -> {
            deleteWorry();
            Toast.makeText(getContext(), R.string.worry_deleted_toast_text, Toast.LENGTH_SHORT).show();
            dismiss();
            if (worry.isFinished()) {
                NavHostFragment.findNavController(DeleteWorryFragment.this).navigate
                        (R.id.action_worryFragment_to_finishedWorriesFragment);
            } else {
                NavHostFragment.findNavController(DeleteWorryFragment.this).navigate
                        (R.id.action_worryFragment_to_ongoingWorriesFragment);
            }
        });
        view.findViewById(R.id.cancelButton).setOnClickListener(v -> dismiss());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable
                (new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    /**
     * Updates the layout's colors to correspond with the worry type (finished or ongoing)
     *
     * @param view The view containing the delete worry fragment views
     */
    public void updateColours(View view) {
        if (finished) {
            view.findViewById(R.id.trashcanImage).setSelected(true);
            view.findViewById(R.id.scribble).setVisibility(View.GONE);
            TextView deleteWorryPrompt = view.findViewById(R.id.deleteWorryPrompt);
            deleteWorryPrompt.setTextColor(getResources().getColor(R.color.darkOrange,
                    requireActivity().getTheme()));
        } else {
            view.findViewById(R.id.sunAndStar).setVisibility(View.GONE);
        }
    }

    /**
     * Removes the displayed worry from the list of worries
     */
    private void deleteWorry() {
        if (worry.isFinished()) {
            sharedViewModel.getFinishedWorries().remove(worry);
        } else {
            sharedViewModel.getOngoingWorries().remove(worry);
        }
    }

}