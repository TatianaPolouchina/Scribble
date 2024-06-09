package com.example.worryapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(this::onItemSelectedListener);

        showOngoingWorriesFragment();
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
            showOngoingWorriesFragment();
            return true;
        } else if (id == R.id.navigation_add_worry) {
            showNewWorryFragment();
            return true;
        } else if (id == R.id.navigation_finished_worries) {
            showFinishedWorriesFragment();
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

    /*
    Replaces the current fragment with OngoingWorriesFragment
    */
    public void showOngoingWorriesFragment() {
        OngoingWorriesFragment ongoingWorriesFragment = new OngoingWorriesFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, ongoingWorriesFragment);
        bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.commit();
    }

    /*
   Replaces the current fragment with FinishedWorriesFragment
   */
    public void showFinishedWorriesFragment() {
        FinishedWorriesFragment finishedWorriesFragment = new FinishedWorriesFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, finishedWorriesFragment);
        bottomNavigationView.setVisibility(View.VISIBLE);
        transaction.commit();
    }

    /*
    Replaces the current fragment with NewWorryFragment1
    */
    public void showNewWorryFragment() {
        NewWorryFragment1 newWorryFragment = new NewWorryFragment1();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main, newWorryFragment);
        bottomNavigationView.setVisibility(View.GONE);
        transaction.commit();
    }


}