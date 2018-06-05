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
                if (validate_details(user_name, user_password)) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    intent.putExtra("user_name", user_name);
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
        dict.put("שashani", "12345678");
        return dict;
    }
}
