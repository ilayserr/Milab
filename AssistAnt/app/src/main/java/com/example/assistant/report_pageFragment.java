package com.example.assistant;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class report_pageFragment extends Fragment {

    private Task task_data;
    public report_pageFragment() {}

    @SuppressLint("ValidFragment")
    public report_pageFragment(Task task) {
        task_data = task;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        System.out.println(task_data.getCourse());
        final FragmentManager manager = getActivity().getSupportFragmentManager();
        final tasks_page_activityFragment tasksListPage = new tasks_page_activityFragment();
        View main_screen = inflater.inflate(R.layout.fragment_report_page, container, false);
        Button rate_button = main_screen.findViewById(R.id.button_rate_report);

        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    Log.i("MainActivity", "popping backstack");
                    fm.popBackStack();
                } else {
                    Log.i("MainActivity", "nothing on backstack, calling super");
                }

            }
        });

        return main_screen;

    }
}
