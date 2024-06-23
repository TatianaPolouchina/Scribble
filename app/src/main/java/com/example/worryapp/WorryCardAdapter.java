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

    private List<Worry> worryItems;
    private OnItemClickListener listener;


    public WorryCardAdapter(List<Worry> worryItems, OnItemClickListener listener) {
        this.worryItems = worryItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.worry_card_template,
                parent, false);
        return new ViewHolder(view);
    }


    /**
     * Replaces the contents of the view with the data from WorryItem and the correct image based
     * on whether the worry is finished or ongoing
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Worry worry = worryItems.get(position);
        holder.textView.setText(worry.getTitle());
        if (worry.isFinished()) {
            holder.image.setImageResource(worry.getFinishedImageResId());
        } else {
            holder.image.setImageResource(worry.getOngoingImageResId());
        }
        holder.bind(listener);
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

    // TODO: finish comments
    // ViewHolder class to hold the views
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView image;

        public ViewHolder(View worryView) {
            super(worryView);
            textView = worryView.findViewById(R.id.card_title);
            image = worryView.findViewById(R.id.card_image);
        }

        public void bind(final OnItemClickListener listener) {
            itemView.setOnClickListener(v -> listener.onItemClick(getAdapterPosition()));
            }
    }
}