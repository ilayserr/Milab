package com.example.assistant;

import android.app.ProgressDialog;
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

//        Task [] data_tasks = (Task[]) getIntent().getSerializableExtra("data_tasks");

        // text fields o dield in main screen
        final TextView name_of_user = main_screen.findViewById(R.id.textView_name);
        final TextView number_of_tasks = main_screen.findViewById(R.id.textView_tasks);
        final TextView up_next = main_screen.findViewById(R.id.textView_up_next);
        final TextView number_of_grades = main_screen.findViewById(R.id.textView_grade);

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "https://assistantrestapi.herokuapp.com/get_all_task_to_submit"; //"http://google.com";
        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            public void onResponse(String response) {
                Log.d("MainActivityFragment", "Response - " + response);
                try {
                    //                    JSONObject jsonObject = new JSONObject();
                    JSONArray json_array = new JSONArray(response.toString());
                    JSONArray json_tasks_array = new JSONArray();
                    for (int i = 0; i < json_array.length(); i++) {
                        JSONObject jo = json_array.getJSONObject(i);
                        String course_name = jo.getString("name");
                        int course_numberOfStudents = jo.getInt("numberOfStudents");
                        Course course = new Course(course_name, course_numberOfStudents);
                        json_tasks_array = (JSONArray)jo.get("tasks");
                        int tasks_length = json_tasks_array.length();
                        data_tasks = new Task [tasks_length];
                        for (int j = 0; j < tasks_length; j++) {
                            JSONObject subObject = json_tasks_array.getJSONObject(j);
                            String title = subObject.getString("title");
                            String deadline = subObject.getString("deadline");
                            String timeRemaining = "placeHolder";
                            int grade = 0;
                            Double difficult = subObject.getDouble("difficulty");
                            int estimatedTime = subObject.getInt("estimatedTime");
                            int amountFinished = subObject.getInt("amountOfFinish");
                            String isSubmited_json = subObject.getString("isSubmitted");
                            boolean isSubmited;
                            if (isSubmited_json.equals("No"))
                                isSubmited = false;
                            else
                                isSubmited = true;
                            data_tasks[j] = new Task(title, course, deadline, timeRemaining,
                                    grade, difficult, estimatedTime, isSubmited, amountFinished);
                        }
                        name_of_user.setText("Hey Tom,");
                        number_of_tasks.setText(Integer.toString(data_tasks.length));
                        // placeholder time
                        int time = 3010;
                        String time_string = time/24/60 + " days, " + time/60%24 + " hours, " + time%60 + " minutes.";
                        up_next.setText(course_name + "\n in " + time_string);
                        number_of_grades.setText("0");
                        dialog.hide();
                    }
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

        ImageButton tasks_page = main_screen.findViewById(R.id.button_tasks_page);
        Log.d("click_to_view_tasks", "start");

        tasks_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FragmentTransaction ft =  getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                //final tasks_page_activityFragment tasksListPage = new tasks_page_activityFragment();

                Bundle bundle = new Bundle();
                Task [] data_to_pass = data_tasks;
                bundle.putSerializable("data_tasks", data_to_pass);
                tasksListPage.setArguments(bundle);
                ft.replace(R.id.fragment_container, tasksListPage);
                ft.addToBackStack(null);
                ft.commit();

            }
        });


        return main_screen;
    }
}
