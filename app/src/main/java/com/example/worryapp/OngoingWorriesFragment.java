package com.example.worryapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_worries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.darkPurpleMain);
        WorryCardAdapter adapter = new WorryCardAdapter(sharedViewModel.getOngoingWorries(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

    //TODO: comment and finish
    // navigates to the worry page, populating it with the information of the associated worry
    @Override
    public void onItemClick(int position) {
        Worry selectedWorry = sharedViewModel.getOngoingWorries().get(position);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", selectedWorry);

        NavHostFragment.findNavController(this).navigate
                (R.id.action_ongoingWorriesFragment_to_worryFragment, bundle);
    }
}