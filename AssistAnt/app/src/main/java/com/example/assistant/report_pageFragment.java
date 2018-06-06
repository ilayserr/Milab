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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

        View main_screen = inflater.inflate(R.layout.fragment_report_page, container, false);

        super.onCreate(savedInstanceState);
        String[] arraySpinner = new String[] {
                "0.5h", "1.0h", "1.5h","2.0h", "2.5h", "3.0h","3.5h", "4.0h", "4.5h","5.0h", "5.5h", "6.0h",
                "6.5h", "7.0h", "7.5h","8.0h", "8.5h", "9.0h","9.5h", "10.0h", "10.5h","11.0h", "11.5h", "12.0h"
        };
        Spinner s = (Spinner) main_screen.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        Button rate_button = main_screen.findViewById(R.id.button_rate_report);
        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getFragmentManager();
                String text = "Thank you!!! \n Your report has been entered ";
                Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                toast.show();
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
