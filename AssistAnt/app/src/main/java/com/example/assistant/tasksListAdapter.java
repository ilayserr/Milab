package com.example.assistant;

/**
 * Created by ilay on 10/01/2018.
 */

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class tasksListAdapter extends RecyclerView.Adapter<tasksListAdapter.ViewHolder> {

    private Task[] dataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public tasksListAdapter(Task[] allTasks) {

        dataset = allTasks;
    }

    /**
     * Provide a reference to the views for each data item
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item contains:
        TextView difficulty;
        TextView estimated;
        TextView finished;
        TextView taskName;
        TextView timeRemain;
        ProgressBar difficulty_bar;
        ProgressBar estimated_bar;
        ProgressBar finish_bar;

        public ViewHolder(View view) {

            super(view);
            difficulty = (TextView) view.findViewById(R.id.difficulty_level);
            difficulty_bar = (ProgressBar) view.findViewById(R.id.progressBar_difficulty);
            estimated = (TextView) view.findViewById(R.id.estimate_level);
            estimated_bar = (ProgressBar) view.findViewById(R.id.progressBar_estimate);
            finished = (TextView) view.findViewById(R.id.finish_level);
            finish_bar = (ProgressBar) view.findViewById(R.id.progressBar_finish);
            taskName = (TextView) view.findViewById(R.id.task_name);
            timeRemain = (TextView) view.findViewById(R.id.time_remaining);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public tasksListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_view, parent, false);

        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.difficulty.setText(String.valueOf(dataset[position].getDifficult()));
        holder.estimated.setText(String.valueOf(dataset[position].getEstimatedTime()));
        holder.finished.setText(String.valueOf(dataset[position].getAmountOfFinish()));
        holder.timeRemain.setText(String.valueOf(dataset[position].getTimeRemaining()));
        holder.difficulty_bar.setProgress((int)dataset[position].getDifficult());
        holder.estimated_bar.setProgress((int)dataset[position].getEstimatedTime());
        holder.finish_bar.setProgress((int)dataset[position].getAmountOfFinish());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }
}