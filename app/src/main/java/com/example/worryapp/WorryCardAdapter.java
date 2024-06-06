package com.example.worryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorryCardAdapter extends RecyclerView.Adapter<WorryCardAdapter.ViewHolder> {

    private List<WorryItem> worryItems;

    public WorryCardAdapter(List<WorryItem> worryItems) {
        this.worryItems = worryItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worry_card_template,
                parent, false);
        return new ViewHolder(view);
    }

    // !!! Replaces the contents of the view with the data from WorryItem
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorryItem item = worryItems.get(position);
        holder.textView.setText(item.getTitle());
        holder.image.setImageResource(item.getImageResId());
    }

    /**
     * Returns the number of ongoing worries
     *
     * @return number of ongoing worries
     */
    @Override
    public int getItemCount() {
        return worryItems.size();
    }

    // ViewHolder class to hold the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView image;

        public ViewHolder(View worryView) {
            super(worryView);
            textView = worryView.findViewById(R.id.card_title);
            image = worryView.findViewById(R.id.card_image);
        }
    }
}