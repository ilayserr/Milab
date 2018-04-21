package com.example.assistant;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        System.out.println(task_data.getCourse());
        return inflater.inflate(R.layout.fragment_report_page, container, false);

    }
}
