package com.example.assistant;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class Log_in_pageFragment extends Fragment {

    public Log_in_pageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View log_in_view = inflater.inflate(R.layout.fragment_log_in_page, container, false);
        Button main_screen_page = log_in_view.findViewById(R.id.button_log_in);

        final EditText user = log_in_view.findViewById(R.id.user_log_in);
        final EditText password = log_in_view.findViewById(R.id.password_log_in);

        main_screen_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("view_main_screen", "start1");
                String user_name = user.getText().toString();
                String user_password = password.getText().toString();
//                Task [] data_tasks = new Task[0];
                if (validate_details(user_name, user_password)) {
//                    RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
//                    String url = "https://assistantrestapi.herokuapp.com/get_all_task_to_submit"; //"http://google.com";
//                    StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                        public void onResponse(String response) {
//                            Log.d("MainActivityFragment", "Response - " + response);
//                            try {
//                                //                    JSONObject jsonObject = new JSONObject();
//                                JSONArray json_array = new JSONArray(response.toString());
//                                JSONArray json_tasks_array = new JSONArray();
//                                for (int i = 0; i < json_array.length(); i++) {
//                                    JSONObject jo = json_array.getJSONObject(i);
//                                    String course_name = jo.getString("name");
//                                    int course_numberOfStudents = jo.getInt("numberOfStudents");
//                                    Course course = new Course(course_name, course_numberOfStudents);
//                                    json_tasks_array = (JSONArray)jo.get("tasks");
//                                    int tasks_length = json_tasks_array.length();
//                                    Task [] data_tasks = new Task [tasks_length];
//                                    for (int j = 0; j < tasks_length; j++) {
//                                        JSONObject subObject = json_tasks_array.getJSONObject(j);
//                                        String title = subObject.getString("title");
//                                        String deadline = subObject.getString("deadline");
//                                        String timeRemaining = "placeHolder";
//                                        int grade = 0;
//                                        Double difficult = subObject.getDouble("difficulty");
//                                        int estimatedTime = subObject.getInt("estimatedTime");
//                                        int amountFinished = subObject.getInt("amountOfFinish");
//                                        String isSubmited_json = subObject.getString("isSubmitted");
//                                        boolean isSubmited;
//                                        if (isSubmited_json.equals("No"))
//                                            isSubmited = false;
//                                        else
//                                            isSubmited = true;
//                                        data_tasks[j] = new Task(title, course, deadline, timeRemaining,
//                                                grade, difficult, estimatedTime, isSubmited, amountFinished);
//                                    }
//                                    System.out.println();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Log.e("MainActivityFragment", "Encountered error - " + error);
//                        }
//                    });
//                    queue.add(req);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
//                    intent.putExtra("data_tasks", data_tasks);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(view.getContext(), "Wrong user name or password, try again", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        return log_in_view;
    }

    public boolean validate_details(String user_name, String user_password) {
        Map dict = get_dict();
        String password_from_dict = (String)dict.get(user_name);
        if (password_from_dict != null && password_from_dict.equals(user_password))
            return true;
        else
            return false;
    }

    public Map get_dict() {
        Map<String, String> dict= new HashMap<String, String>();
        dict.put("a", "1");
        dict.put("ilay", "12345678");
        dict.put("shani", "12345678");
        return dict;
    }
}
