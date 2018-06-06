package com.example.assistant;

import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;

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
public class MainActivityFragment extends Fragment {

    Task [] data_tasks;
    int stress_level = 0;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // put up loading screen
        final ProgressDialog dialog=new ProgressDialog(getContext());
        dialog.setMessage("Please wait");
        dialog.setCancelable(false);
        dialog.setInverseBackgroundForced(false);
        dialog.show();


        final FragmentManager manager = getActivity().getSupportFragmentManager();
        final tasks_page_activityFragment tasksListPage = new tasks_page_activityFragment();
        View main_screen = inflater.inflate(R.layout.fragment_main, container, false);

        Intent intent = getActivity().getIntent();
        String user_name = intent.getStringExtra("user_name");
        final TextView name_of_user = main_screen.findViewById(R.id.textView_name);
        name_of_user.setText("Hey " + user_name + ",");
        // text fields o dield in main screen
        final TextView number_of_tasks = main_screen.findViewById(R.id.textView_tasks);
        final TextView up_next = main_screen.findViewById(R.id.textView_up_next);
//        final TextView number_of_grades = main_screen.findViewById(R.id.textView_grade);


        /*
        [ {num_tasks: 5, up_next: "compa... and co... in/ minutes", total_stress: 56},
        {"_id":"5ab6764c734d1d57bac4a77a","name":"Algorithms","lecturer":"Prof Tami Tamir",
        "numberOfStudents":"120","tasks":
            [{"title":"Assignment06","createdBy":"Amit Gal",
        "deadline":"10-05-2018","difficulty":"4","estimatedTime":450,"isSubmitted":"No",
        "amountOfFinish":"30","timeRemaining":"4d 12h","sortAlgorithm":4574.921809999999}]
        },

        {"_id":"5ab6777b734d1d57bac4a7c5","name":"Graphics","lecturer":"Prof. Arik Shamir",
        "numberOfStudents":"100","tasks":
            [{"title":"Ex2 - Ray Tracing","createdBy":"Shani Adir",
            "deadline":"21-05-2018","difficulty":"5","estimatedTime":960,"isSubmitted":"No",
            "amountOfFinish":"45","timeRemaining":"15d 12h","sortAlgorithm":15663.221798333332}]
        },

        {"_id":"5adb4c91734d1d2de1f5be86","name":"Computability and Complexity","lecturer":"Shay Mozes"
        ,"numberOfStudents":"110",
            "tasks":[{"title":"Exercise4","createdBy":"Ilay Serr",
            "deadline":"06-05-2018","difficulty":"6","estimatedTime":760,"isSubmitted":"No",
            "amountOfFinish":"5","timeRemaining":"0d 12h","sortAlgorithm":543.5217866666666}]
            },

        {"_id":"5ae78158734d1d13318405fc","name":"Design Patterns","lecturer":"Guy Ronen",
        "numberOfStudents":"51","tasks":
            [{"title":"Creational Patterns","createdBy":"Shani Adir",
            "deadline":"18-05-2018","difficulty":"4","estimatedTime":550,"isSubmitted":"No",
            "amountOfFinish":"4","timeRemaining":"12d 12h","sortAlgorithm":12638.921786666666},
            {"title":"UML diagrams tutorial","createdBy":"Shani Adir","deadline":"11-05-2018",
            "difficulty":"2","estimatedTime":400,"isSubmitted":"No","amountOfFinish":"10",
            "timeRemaining":"5d 12h","sortAlgorithm":5582.321775}]}]
         */



        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://assistantrestapi.herokuapp.com/get_all_task_to_submit";
//        String url = "https://assistantrestapi.herokuapp.com/get_all_task_to_submit"; //"http://google.com";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.d("MainActivityFragment", "Response - " + response);
                int counter = 0;
                try {
                    JSONArray json_array = new JSONArray(response.toString());
                    JSONArray json_tasks_array = new JSONArray();
                    JSONObject jo = json_array.getJSONObject(json_array.length() - 1);
                    int total_number_of_tasks = jo.getInt("num_tasks");
                    String up_next_string = jo.getString("up_next");
                    stress_level = (int)jo.getDouble("stress_level");
                    data_tasks = new Task [total_number_of_tasks];
                    number_of_tasks.setText(Integer.toString(total_number_of_tasks));
                    up_next.setText(up_next_string);
                    // change i to 1
                    for (int i = 0; i < json_array.length() - 1; i++) {
                        jo = json_array.getJSONObject(i);
                        String course_name = jo.getString("name");
                        int course_numberOfStudents = jo.getInt("numberOfStudents");
                        Course course = new Course(course_name, course_numberOfStudents);
                        json_tasks_array = (JSONArray)jo.get("tasks");
                        int tasks_length = json_tasks_array.length();
                        for (int j = 0; j < tasks_length; j++) {
                            JSONObject subObject = json_tasks_array.getJSONObject(j);
                            String title = subObject.getString("title");
                            String deadline = subObject.getString("deadline");
                            String timeRemaining_str = subObject.getString("timeRemaining_str");
                            int timeRemaining_min = subObject.getInt(("timeRemaining_min"));
                            Double difficult = subObject.getDouble("difficulty");
                            int estimatedTime = subObject.getInt("estimatedTime");
                            int amountFinished = subObject.getInt("amountOfFinish");
                            String isSubmited_json = subObject.getString("isSubmitted");
                            boolean isSubmited;
                            if (isSubmited_json.equals("No")) {
                                isSubmited = false;
                            }
                            else {
                                isSubmited = true;
                            }
                            Double sortAlgorithm = subObject.getDouble("sortAlgorithm");
                            data_tasks[counter] = new Task(title, course, deadline, timeRemaining_str,
                                    timeRemaining_min, difficult, estimatedTime, isSubmited, amountFinished,sortAlgorithm);
                            counter++;
                        }
                    }
                    dialog.hide();
                    sort_tasks();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("MainActivityFragment", "Encountered error - " + error);
            }
        });
        queue.add(req);

//        ImageButton tasks_page = main_screen.findViewById(R.id.button_tasks_page);
        Button tasksButton = main_screen.findViewById(R.id.button_tasks_page);
        tasksButton.setVisibility(View.VISIBLE);
        tasksButton.setBackgroundColor(Color.TRANSPARENT); //Color.argb(150, 155, 155, 155));

        Log.d("click_to_view_tasks", "start");

        tasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle bundle = new Bundle();
                bundle.putSerializable("data_tasks", data_tasks);
                bundle.putInt("stress_level", stress_level);
                bundle.putInt("position", 0);
                tasksListPage.setArguments(bundle);
                ft.replace(R.id.fragment_container, tasksListPage);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        Button up_nextButton = main_screen.findViewById(R.id.button_up_next_page);
        up_nextButton.setVisibility(View.VISIBLE);
        up_nextButton.setBackgroundColor(Color.TRANSPARENT);

        up_nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                Bundle bundle = new Bundle();
                bundle.putSerializable("data_tasks", data_tasks);
                bundle.putInt("stress_level", stress_level);
                int position = findPosition();
                bundle.putInt("position", position);
                tasksListPage.setArguments(bundle);
                ft.replace(R.id.fragment_container, tasksListPage);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        Button recent_tasks_button = main_screen.findViewById(R.id.button_recent_tasks_page);
        recent_tasks_button.setVisibility(View.VISIBLE);
        recent_tasks_button.setBackgroundColor(Color.TRANSPARENT);
//
//        recent_tasks_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
////                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////
////                Bundle bundle = new Bundle();
////                bundle.putSerializable("data_tasks", data_tasks);
////                bundle.putInt("stress_level", stress_level);
////                bundle.putInt("position", 0);
////                tasksListPage.setArguments(bundle);
////                ft.replace(R.id.fragment_container, tasksListPage);
////                ft.addToBackStack(null);
////                ft.commit();
//            }
//        });

        return main_screen;
    }

    public int findPosition() {
        double minimum = 10000000;
        int min_index = 0;
        for(int i = 0; i < data_tasks.length; i++) {
            double temp = data_tasks[i].getTimeRemaining_min();
            if (temp < minimum) {
                minimum = temp;
                min_index = i;
            }
        }
        return min_index;
    }

    public void sort_tasks() {
        boolean swapped = true;
        int j = 0;
        Task tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < data_tasks.length - j; i++) {
                if (data_tasks[i].sortAlgorithm > data_tasks[i + 1].sortAlgorithm) {
                    tmp = data_tasks[i];
                    data_tasks[i] = data_tasks[i + 1];
                    data_tasks[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
    }

}
