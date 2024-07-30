package com.example.scribble.fragments;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.scribble.MainActivity;
import com.example.scribble.OnItemClickListener;
import com.example.scribble.R;
import com.example.scribble.Worry;
import com.example.scribble.WorryCardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class FinishedWorriesFragment extends BaseFragment implements OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finished_worries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel.getFinishedWorriesLiveData().observe(getViewLifecycleOwner(), worries ->
                updateUI(view));
        handleBackPress();
    }

    @Override
    public void onResume() {
        super.onResume();
        selectMenuItem();
    }

    //TODO: comment
    private void updateUI(@NonNull View view) {
        WorryCardAdapter adapter = new WorryCardAdapter(sharedViewModel.getFinishedWorries(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
        updateNoWorryText(view);
    }

    /***
     * Enables back press only to return to ongoingWorriesFragment
     */
    public void handleBackPress() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavController navController = NavHostFragment.findNavController
                        (FinishedWorriesFragment.this);
                int previousFragmentId = Objects.requireNonNull
                        (navController.getPreviousBackStackEntry()).getDestination().getId();
                if (previousFragmentId == R.id.ongoingWorriesFragment) {
                    navController.popBackStack();
                }
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    /***
     * Checks the finished worry menu item if it is not selected
     */
    private void selectMenuItem() {
        BottomNavigationView bottomNav = requireActivity().findViewById(R.id.bottom_navigation);
        MenuItem toSelect = bottomNav.getMenu().findItem(R.id.navigation_finished_worries);
        if (bottomNav.getMenu().findItem(bottomNav.getSelectedItemId()) != toSelect) {
            toSelect.setChecked(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.lightOrange);
    }

    /***
     * Displays text on the ongoing and finished worries fragments if there are no worries to display
     *
     * @param view view on which to display text
     */
    private void updateNoWorryText(@NonNull View view) {
        TextView worryNumberText = view.findViewById(R.id.tvSecond);

        int numWorries = sharedViewModel.getFinishedWorries().size();
        if (numWorries == 1) {
            worryNumberText.setText(getString(R.string.finished_page_subtitle_1_worry));
        } else {
            worryNumberText.setText(getString(R.string.finished_page_subtitle,
                    Integer.toString(numWorries)));
        }

        TextView noWorriesText = view.findViewById(R.id.noWorriesText);
        if (numWorries == 0) {
            noWorriesText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemClick(int position) {
        Worry selectedWorry = sharedViewModel.getFinishedWorries().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", selectedWorry);

        NavHostFragment.findNavController(this).navigate
                (R.id.action_finishedWorriesFragment_to_worryFragment, bundle);
    }
}