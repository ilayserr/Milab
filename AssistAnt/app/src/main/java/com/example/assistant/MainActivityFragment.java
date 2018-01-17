package com.example.assistant;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        final FragmentManager manager = getActivity().getSupportFragmentManager();
        final FragmentTransaction transaction = manager.beginTransaction();
        final tasks_page_activityFragment tasksListPage = new tasks_page_activityFragment();
        View main_screen = inflater.inflate(R.layout.fragment_main, container, false);

        ImageButton tasks_page = main_screen.findViewById(R.id.button_tasks_page);
        Log.d("click_to_view_tasks", "start");

        tasks_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction.replace(R.id.fragment_container, tasksListPage);
                transaction.addToBackStack(null);
                transaction.commit();
                /*
                Intent intent = new Intent(view.getContext(), tasks_page_activityFragment.class);
                Log.d("click_to_view_tasks", "start1");
                //intent.putExtra("fragmetToLoad", "");
                startActivity(intent);
                */
            }
        });
//        // text fields to dield in main screen
//        final TextView name_of_user = (TextView) main_screen.findViewById(R.id.textView_name);
//        final TextView number_of_tasks = (TextView) main_screen.findViewById(R.id.textView_tasks);
//        final TextView up_next = (TextView) main_screen.findViewById(R.id.textView_up_next);
//        final TextView number_of_grades = (TextView) main_screen.findViewById(R.id.textView_grade);
//
//        name_of_user.setText("Hey Tom,");
//        number_of_tasks.setText("5");
//        up_next.setText("Algorithms Ex4 \n in 3 days, 2 hours, 20 minutes");
//        number_of_grades.setText("4");


        return main_screen;
    }
}
