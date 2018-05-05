package com.example.assistant;

/**
 * Created by ilay on 10/01/2018.
 */

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.io.FileReader;

public class tasksListAdapter extends RecyclerView.Adapter<tasksListAdapter.ViewHolder> {

    private Task[] dataset;
    private FragmentActivity current_fragment;

    // Provide a suitable constructor (depends on the kind of dataset)
    public tasksListAdapter(FragmentActivity fragment, Task[] allTasks) {
        current_fragment = fragment;
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
        ImageButton report_page;

        public ViewHolder(View view) {

            super(view);
            difficulty = (TextView) view.findViewById(R.id.difficulty_level);
            difficulty_bar = ((ProgressBar) view.findViewById(R.id.progressBar_difficulty));  // .getProgressDrawable().setColorFilter(
//                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
            estimated = (TextView) view.findViewById(R.id.estimate_level);
            estimated_bar = (ProgressBar) view.findViewById(R.id.progressBar_estimate);
            finished = (TextView) view.findViewById(R.id.finish_level);
            finish_bar = (ProgressBar) view.findViewById(R.id.progressBar_finish);
            taskName = (TextView) view.findViewById(R.id.task_name);
            timeRemain = (TextView) view.findViewById(R.id.time_remaining);
            report_page = (ImageButton) view.findViewById((R.id.report_button));



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
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.taskName.setText((String.valueOf((dataset[position].getTaskName()))));
        holder.difficulty.setText(String.valueOf((int)dataset[position].getDifficult()) + "/7");
        int estimatedTime = dataset[position].getEstimatedTime();
        String days = String.valueOf(estimatedTime/24/60);
        String hours =(estimatedTime/60%24 > 9) ? String.valueOf(estimatedTime/60%24) : "0" + String.valueOf(estimatedTime/60%24);
        String minutes =(estimatedTime%60 > 9) ? String.valueOf(estimatedTime%60) : "0" + String.valueOf(estimatedTime%60);
        String estimated_time_string = days + "d " + hours + "h " + minutes + "m";
        holder.estimated.setText(estimated_time_string);
        String how_many_finished = String.valueOf(dataset[position].getAmountOfFinish()) + "/" +
                String.valueOf(dataset[position].getCourse().getNumOfStudents());
        while (how_many_finished.length() < 6)
            how_many_finished += "  ";
        holder.finished.setText(how_many_finished);
        holder.timeRemain.setText(String.valueOf(dataset[position].getTimeRemaining_str()));
        holder.difficulty_bar.setProgress((int)dataset[position].getDifficult());
        holder.difficulty_bar.setMax(7);
        holder.difficulty_bar.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        holder.estimated_bar.setProgress((int)dataset[position].getEstimatedTime());
        holder.estimated_bar.setMax(1000);
        holder.estimated_bar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        holder.finish_bar.setMax((int)dataset[position].getCourse().getNumOfStudents());
        holder.finish_bar.setProgress((int)dataset[position].getAmountOfFinish());
        holder.finish_bar.setProgressTintList(ColorStateList.valueOf(Color.BLUE));
        holder.finish_bar.setMax((int)dataset[position].getCourse().getNumOfStudents());

        final FragmentManager manager = current_fragment.getSupportFragmentManager();
        final report_pageFragment report_page = new report_pageFragment(dataset[position]);
//        View main_screen = inflater.inflate(R.layout.fragment_main, container, false);
        holder.report_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragment_container, report_page);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataset.length;
    }
}