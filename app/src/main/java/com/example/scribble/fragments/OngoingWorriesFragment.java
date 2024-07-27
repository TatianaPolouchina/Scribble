package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.scribble.MainActivity;
import com.example.scribble.OnItemClickListener;
import com.example.scribble.R;
import com.example.scribble.SharedViewModel;
import com.example.scribble.Worry;
import com.example.scribble.WorryCardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class OngoingWorriesFragment extends Fragment implements OnItemClickListener {

    private SharedViewModel sharedViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ongoing_worries, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WorryCardAdapter adapter = new WorryCardAdapter(sharedViewModel.getOngoingWorries(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        TextView worryNumberText = view.findViewById(R.id.tvSecond);
        int numWorries = sharedViewModel.getOngoingWorries().size();
        if (numWorries == 1) {
            worryNumberText.setText(getString(R.string.ongoing_page_subtitle_1_worry));
        } else {
            worryNumberText.setText(getString(R.string.ongoing_page_subtitle,
                    Integer.toString(numWorries)));
        }

        int numFinishedWorries = sharedViewModel.getFinishedWorries().size();
        TextView noWorriesText = view.findViewById(R.id.noWorriesText);
        ImageView downArrowImage = view.findViewById(R.id.downArrow);
        if (numWorries == 0 && numFinishedWorries == 0) {
            noWorriesText.setVisibility(View.VISIBLE);
            downArrowImage.setVisibility(View.VISIBLE);
        } else if (numWorries == 0){
            noWorriesText.setText(R.string.no_ongoing_worries_text);
            noWorriesText.setVisibility(View.VISIBLE);
        }

        selectMenuItem();
        handleBackPress();
    }

    /***
     * Enables back press only to return to finishedWorriesFragment
     */
    public void handleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = NavHostFragment.findNavController
                        (OngoingWorriesFragment.this);
                int previousFragmentId = Objects.requireNonNull
                        (navController.getPreviousBackStackEntry()).getDestination().getId();
                if (previousFragmentId == R.id.finishedWorriesFragment) {
                    navController.popBackStack();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    /***
     * Checks the ongoing worry menu item if it is not selected
     */
    private void selectMenuItem() {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MenuItem toSelect = bottomNav.getMenu().findItem(R.id.navigation_ongoing_worries);
        if (bottomNav.getMenu().findItem(bottomNav.getSelectedItemId()) != toSelect) {
            toSelect.setChecked(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.lightBlue);
    }

    //TODO: comment and finish
    // navigates to the worry page, populating it with the information of the associated worry
    @Override
    public void onItemClick(int position) {
        Worry selectedWorry = sharedViewModel.getOngoingWorries().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", selectedWorry);

        NavHostFragment.findNavController(this).navigate
                (R.id.action_ongoingWorriesFragment_to_worryFragment, bundle);
    }
}