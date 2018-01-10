package com.example.assistant;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main_screen = inflater.inflate(R.layout.fragment_main, container, false);

        // text fields to dield in main screen
        final TextView number_of_tasks = (TextView) main_screen.findViewById(R.id.textView_tasks);
        final TextView up_next = (TextView) main_screen.findViewById(R.id.textView_up_next);
        final TextView number_of_grades = (TextView) main_screen.findViewById(R.id.textView_grade);

        number_of_tasks.setText("5");
        up_next.setText("Algorithms Ex4 \n in 3 days, 2 hours, 20 minutes");
        number_of_grades.setText("4");


        return main_screen;
    }
}