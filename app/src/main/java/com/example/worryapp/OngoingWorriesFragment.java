package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OngoingWorriesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_worries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.darkPurpleMain);

        //Set up recycler view
        List<WorryItem> data = new ArrayList<>();
        data.add(new WorryItem("TEST", R.drawable.worry_1));
        data.add(new WorryItem("TEST2", R.drawable.worry_1));
        data.add(new WorryItem("TEST3", R.drawable.worry_1));
        data.add(new WorryItem("TEST4", R.drawable.worry_1));
        data.add(new WorryItem("TEST5", R.drawable.worry_1));
        data.add(new WorryItem("TEST6", R.drawable.worry_1));
        data.add(new WorryItem("TEST7", R.drawable.worry_1));
        data.add(new WorryItem("TEST8", R.drawable.worry_1));
        data.add(new WorryItem("TEST9", R.drawable.worry_1));
        data.add(new WorryItem("TEST10", R.drawable.worry_1));

        WorryCardAdapter adapter = new WorryCardAdapter(data);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }
}