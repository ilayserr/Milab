package com.example.assistant;

import android.content.SyncStatusObserver;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class tasks_page_activityFragment extends Fragment {

    private RecyclerView recyclerView;
    private  RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Task[] dataset;
    public tasks_page_activityFragment() {
//        dataset = new Task[9];
//        dataset[0] = new Task("Exercise #9",
//                "Automata And Formal Language", 120,
//                "Tuesday, 9 January 2018, 12:00 AM",
//                "4 hours 52 mins");
//        dataset[1] = new Task("Exercise 4",
//                "Computer Networks", 130,
//                "Friday, 12 January 2018, 02:00 PM",
//                "3 days and 4 hours");
//        dataset[2] = new Task("Assignment 7",
//                "Algorithms", 150,
//                "Sunday, 14 January 2018, 01:00 PM",
//                "5 days and 3 hours");
//        dataset[3] = new Task("Assignment 6",
//                "Algorithms", 150,
//                "Sunday, 7 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early");
//        dataset[4] = new Task("Assignment 5",
//                "Algorithms", 150,
//                "Sunday, 1 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early", 90, 5, 10, true);
//        dataset[5] = new Task("Assignment 6",
//                "Algorithms", 150,
//                "Sunday, 7 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early");
//        dataset[6] = new Task("Assignment 6",
//                "Algorithms", 150,
//                "Sunday, 7 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early");
//        dataset[7] = new Task("Assignment 6",
//                "Algorithms", 150,
//                "Sunday, 7 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early");
//        dataset[8] = new Task("Assignment 6",
//                "Algorithms", 150,
//                "Sunday, 7 January 2018, 01:00 PM",
//                "Assignment was submitted 4 hours 5 mins early");

        /*
        all_taks_output_example
        [
        {"_id":"5ab6764c734d1d57bac4a77a","name":"Algorithms","lecturer":"Prof Tami Tamir","numberOfStudents":"120",
        "tasks":
                [
                    {"title":"Assignment05","createdBy":"Shani Adir","deadline":"01-05-2018","difficulty":"6","estimatedTime":"9 hours","isSubmitted":"No","amountOfFinish":"5"},
                    {"title":"Assignment04","createdBy":"Ilay Serr","deadline":"20-04-2018","difficulty":"5","estimatedTime":"6 hours","isSubmitted":"No","amountOfFinish":"10"}
                ]
         }
        ]
        */



        dataset = new Task[1];
        dataset[0] = new Task("Exercise #9",
                "Automata And Formal Language", 120,
                "Tuesday, 9 January 2018, 12:00 AM",
                "4 hours 52 mins");

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


        /*

        [{"_id":"5ab6764c734d1d57bac4a77a","name":"Algorithms","lecturer":"Prof Tami Tamir","numberOfStudents":"120","tasks":[{"title":"Assignment05","createdBy":"Shani Adir","deadline":"01-05-2018","difficulty":"6","estimatedTime":"9 hours","isSubmitted":"No","amountOfFinish":"5"},{"title":"Assignment04","createdBy":"Ilay Serr","deadline":"20-04-2018","difficulty":"5","estimatedTime":"6 hours","isSubmitted":"No","amountOfFinish":"10"}]}]
         */

        Bundle bundle = getArguments();
        Task[] data_tasks= (Task[]) bundle.getSerializable("data_tasks");

        // specify an adapter
        adapter = new tasksListAdapter(this.getActivity() , data_tasks);
        recyclerView.setAdapter(adapter);
        return mainView;



    }

}
