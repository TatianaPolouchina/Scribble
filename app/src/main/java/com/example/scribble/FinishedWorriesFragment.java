package com.example.scribble;

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
import android.widget.TextView;

import com.example.scribble.R;

public class FinishedWorriesFragment extends Fragment implements OnItemClickListener {

    private SharedViewModel sharedViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finished_worries, container, false);
    }

    // TODO: refactor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.lightOrange);
        WorryCardAdapter adapter = new WorryCardAdapter(sharedViewModel.getFinishedWorries(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);


        TextView worryNumberText = view.findViewById(R.id.tvSecond);

        int numWorries = sharedViewModel.getFinishedWorries().size();
        if (numWorries == 1) {
            worryNumberText.setText(getString(R.string.finished_page_subtitle_1_worry));
        } else {
            worryNumberText.setText(getString(R.string.finished_page_subtitle,
                    Integer.toString(numWorries)));
        }
    }

    @Override
    public void onItemClick(int position) {
        Worry selectedWorry = sharedViewModel.getFinishedWorries().get(position);
        ((MainActivity) requireActivity()).setStatusBarColor(R.color.white);

        Bundle bundle = new Bundle();
        bundle.putSerializable("selectedWorry", selectedWorry);

        NavHostFragment.findNavController(this).navigate
                (R.id.action_finishedWorriesFragment_to_worryFragment, bundle);
    }
}