package com.example.scribble;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;

import com.example.scribble.persistence.JSONReader;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TODO: comment methods
public class MainActivity extends AppCompatActivity {
    private NavController navController;
    private BottomNavigationView bottomNavigationView;
    private static final String JSON_STORE = "data.json";
    private File dataFile;

    //TODO: refactor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment)
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        setUpBottomNavMenu();
        SharedViewModel sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        //NEW:
        // TODO: refactor
        executor.execute(() -> {
            boolean newFile = loadFile();
            JSONReader jsonReader = null;

            if (newFile) {
                try {
                    jsonReader = new JSONReader(dataFile);
                    List<Worry> ongoingWorries = jsonReader.readOngoingWorries();
                    List<Worry> finishedWorries = jsonReader.readFinishedWorries();
                    WorryImageHelper worryImageHelper = jsonReader.readWorryImageHelper();
                    handler.post(() -> {
                        sharedViewModel.setOngoingWorries(ongoingWorries);
                        sharedViewModel.setFinishedWorries(finishedWorries);
                        sharedViewModel.setWorryImageHelper(worryImageHelper);
                    });
                } catch (JSONException ignored) {
                }
            }
        });
    }

    // TODO: comment
    // Returns true if a new file was created
    private boolean loadFile() {
        dataFile = new File(getFilesDir(), JSON_STORE);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    private void loadFiles(SharedViewModel sharedViewModel) {
        File file = new File(getFilesDir(), JSON_STORE);
        if (file.exists()) {
            try {
                loadData(file, sharedViewModel);
            } catch (JSONException ignored) {
            }
        } else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static void loadData(File file, SharedViewModel sharedViewModel) throws JSONException {
        JSONReader jsonReader = new JSONReader(file);
        List<Worry> ongoingWorries = jsonReader.readOngoingWorries();
        List<Worry> finishedWorries = jsonReader.readFinishedWorries();
        WorryImageHelper worryImageHelper = jsonReader.readWorryImageHelper();

        Log.d("SharedViewModel", "Ongoing Worries: " + ongoingWorries);
        Log.d("SharedViewModel", "Finished Worries: " + finishedWorries);

        sharedViewModel.setOngoingWorries(ongoingWorries);
        sharedViewModel.setFinishedWorries(finishedWorries);
        sharedViewModel.setWorryImageHelper(worryImageHelper);

        Log.d("SharedViewModel", "Ongoing Worries in ViewModel: " + sharedViewModel.getOngoingWorries());
        Log.d("SharedViewModel", "Finished Worries in ViewModel: " + sharedViewModel.getFinishedWorries());
    }

    // Creates the bottom navigation menu
    private void setUpBottomNavMenu() {
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
            navigateWithFade(R.id.newWorryFragment1);
            return true;
        } else if (id == R.id.navigation_finished_worries) {
            navController.navigate(R.id.finishedWorriesFragment);
            return true;
        }
        return false;
    }

    //TODO: comment
    public void navigateWithFade(int fragmentId) {
        navController.navigate(fragmentId, null, new NavOptions.Builder()
                .setEnterAnim(R.anim.fade_in)
                .setExitAnim(R.anim.empty_transition)
                .setPopEnterAnim(R.anim.empty_transition)
                .setPopExitAnim(R.anim.fade_out)
                .build());
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