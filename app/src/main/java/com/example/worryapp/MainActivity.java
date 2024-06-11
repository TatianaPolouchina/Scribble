package com.example.worryapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    // TODO: refactor
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.ongoingWorriesFragment ||
                    destination.getId() == R.id.finishedWorriesFragment) {
                bottomNavigationView.setVisibility(View.VISIBLE);
            } else {
                bottomNavigationView.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Displays the fragment corresponding to the selected item in the bottom navigation menu
     *
     * @param item the selected menu item
     * @return true if the navigation was successful, false otherwise
     */
    private boolean onItemSelectedListener(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.navigation_ongoing_worries) {
            navController.navigate(R.id.ongoingWorriesFragment);
            return true;
        } else if (id == R.id.navigation_add_worry) {
            navController.navigate(R.id.newWorryFragment1);
            return true;
        } else if (id == R.id.navigation_finished_worries) {
            navController.navigate(R.id.finishedWorriesFragment);
            return true;
        }
        return false;
    }

    /**
     * Changes the background colour of the status bar
     *
     * @param colourId the desired background colour code
     */
    public void setStatusBarColor(int colourId) {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, colourId));
    }
}