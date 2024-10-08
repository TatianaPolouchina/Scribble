package com.tatiana.scribble.fragments;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.tatiana.scribble.MainActivity;
import com.tatiana.scribble.OnItemClickListener;
import com.tatiana.scribble.R;
import com.tatiana.scribble.Worry;
import com.tatiana.scribble.WorryCardAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

/***
 * Fragment displaying all of the ongoing worries
 */
public class OngoingWorriesFragment extends BaseFragment implements OnItemClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ongoing_worries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addObservers(view);
    }

    /***
     * Observes the ongoing and finished worries for changes, updates the text and recycler view
     * when a change occurs
     *
     * @param view container View
     */
    private void addObservers(@NonNull View view) {
        sharedViewModel.getOngoingWorriesLiveData().observe(getViewLifecycleOwner(), worries -> {
            setupRecyclerView(view);
            setupWorryNumberText(view);
        });
        sharedViewModel.getFinishedWorriesLiveData().observe(getViewLifecycleOwner(), worries ->
                updateNoWorryText(view));
        handleBackPress();
    }

    /***
     * Updates the status bar and selected menu item
     */
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.lightBlue);
        selectMenuItem();
    }

    /***
     * Updates the text and its visibility depending on how many finished or ongoing worries
     * are saved
     *
     * @param view container View
     */
    private void updateNoWorryText(@NonNull View view) {
        int numOngoing = sharedViewModel.getOngoingWorries().size();
        int numFinished = sharedViewModel.getFinishedWorries().size();
        TextView noWorriesText = view.findViewById(R.id.noWorriesText);
        ImageView downArrowImage = view.findViewById(R.id.downArrow);

        if (numOngoing == 0 && numFinished == 0) {
            noWorriesText.setVisibility(View.VISIBLE);
            downArrowImage.setVisibility(View.VISIBLE);
        } else if (numOngoing == 0) {
            noWorriesText.setText(R.string.no_ongoing_worries_text);
            noWorriesText.setVisibility(View.VISIBLE);
            downArrowImage.setVisibility(View.GONE);
        } else {
            noWorriesText.setVisibility(View.GONE);
            downArrowImage.setVisibility(View.GONE);
        }
    }


    /***
     * Displays the number of worries
     *
     * @param view container View
     */
    private void setupWorryNumberText(@NonNull View view) {
        TextView worryNumberText = view.findViewById(R.id.tvSecond);
        int numWorries = sharedViewModel.getOngoingWorries().size();
        if (numWorries == 1) {
            worryNumberText.setText(getString(R.string.ongoing_page_subtitle_1_worry));
        } else {
            worryNumberText.setText(getString(R.string.ongoing_page_subtitle,
                    Integer.toString(numWorries)));
        }
    }

    /***
     * sets up the Recycler view's layout and adapter
     *
     * @param view container View
     */
    private void setupRecyclerView(@NonNull View view) {
        WorryCardAdapter adapter = new WorryCardAdapter(sharedViewModel.getOngoingWorries(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
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


    /***
     * Opens the WorryFragment for the selected Worry in the RecyclerView
     *
     * @param position position of selected worry in the RecyclerView
     */
    @Override
    public void onItemClick(int position) {
        Worry selectedWorry = sharedViewModel.getOngoingWorries().get(position);

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", selectedWorry);

        NavHostFragment.findNavController(this).navigate
                (R.id.action_ongoingWorriesFragment_to_worryFragment, bundle);
    }
}