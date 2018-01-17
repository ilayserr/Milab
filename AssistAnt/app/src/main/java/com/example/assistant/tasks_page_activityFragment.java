package com.example.assistant;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class tasks_page_activityFragment extends Fragment {

    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Task[] dataset;
    public tasks_page_activityFragment() {
        dataset = new Task[5];
        dataset[0] = new Task("Exercise #9",
                "Automata And Formal Language", 120,
                "Tuesday, 9 January 2018, 12:00 AM",
                "4 hours 52 mins");
        dataset[1] = new Task("Exercise 4",
                "Computer Networks", 130,
                "Friday, 12 January 2018, 02:00 PM",
                "3 days and 4 hours");
        dataset[2] = new Task("Assignment 7",
                "Algorithms", 150,
                "Sunday, 14 January 2018, 01:00 PM",
                "5 days and 3 hours");
        dataset[3] = new Task("Assignment 6",
                "Algorithms", 150,
                "Sunday, 7 January 2018, 01:00 PM",
                "Assignment was submitted 4 hours 5 mins early");
        dataset[4] = new Task("Assignment 5",
                "Algorithms", 150,
                "Sunday, 1 January 2018, 01:00 PM",
                "Assignment was submitted 4 hours 5 mins early", 90, 5, 10, true);
        Log.d("test_array", "success1");
        System.out.print(dataset[0].getCourse().getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView =  inflater.inflate(R.layout.fragment_tasks_page_activity, container, false);
        recyclerView = (RecyclerView)mainView.findViewById(R.id.show_tasks_list);

        // improve performance
        recyclerView.setHasFixedSize(true);

        // linear layout manager
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        adapter = new tasksListAdapter(dataset);
        recyclerView.setAdapter(adapter);
        return mainView;
    }

}
