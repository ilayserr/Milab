package com.example.assistant;

import android.content.SyncStatusObserver;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
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
import android.widget.ProgressBar;
import android.widget.SeekBar;

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

        dataset = new Task[1];
        dataset[0] = new Task("Exercise #9",
                "Automata And Formal Language", 120,
                "Tuesday, 9 January 2018, 12:00 AM",
                "4 hours 52 mins");

        Log.d("test_array", "success1");
        System.out.print(dataset[0].getCourse().getName());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Handler seekBarHandler = new Handler(); // must be created in the same thread that created the SeekBar

        View mainView =  inflater.inflate(R.layout.fragment_tasks_page_activity, container, false);
        Bundle bundle = getArguments();
        Task[] data_tasks = (Task[]) bundle.getSerializable("data_tasks");
        int stress_level = bundle.getInt("stress_level");
        int position = bundle.getInt("position");
//        SeekBar seekBar = mainView.findViewById(R.id.seekBar_luminosite);
        ProgressBar seekBar= mainView.findViewById((R.id.seekBar_luminosite));
        // you should define max in xml, but if you need to do this by code, you must set max as 0 and then your desired value. this is because a bug in SeekBar (issue 12945) (don't really checked if it was corrected)
        int max = 10000;
        seekBar.setMax(max);
        seekBar.setProgress(stress_level);
        seekBar.setEnabled(false);
        seekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#228B22")));
        if ((stress_level / (double)max) > 0.55)
            seekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#2ECC40")));
        if ((stress_level / (double)max) > 0.75)
            seekBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#FF4136")));

        recyclerView = (RecyclerView)mainView.findViewById(R.id.show_tasks_list);

        // improve performance
        recyclerView.setHasFixedSize(true);

        // linear layout manager
        layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        adapter = new tasksListAdapter(this.getActivity() , data_tasks);
        recyclerView.setAdapter(adapter);

        // move to a specific position
        recyclerView.scrollToPosition(position);
        return mainView;

    }

}
