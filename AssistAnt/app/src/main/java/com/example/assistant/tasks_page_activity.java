package com.example.assistant;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

public class tasks_page_activity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    LinearLayout relativeLayout;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    String[] londonPlacesDataset =
            {"Big Ben", "London Eye", "London Bridge", "Buckingham Palace", "Hyde Park",
                    "Madame Tussauds", "Tower of London", "River Thames", "Piccadilly Circus",
                    "Leicester Square", "Oxford Street", "Wimbledon"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_page_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = getApplicationContext();
        relativeLayout = (LinearLayout) findViewById(R.id.london_linearlayout);
        recyclerView = (RecyclerView) findViewById(R.id.london_recyclerView);
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new MyAdapter(context, londonPlacesDataset);
        recyclerView.setAdapter(recyclerViewAdapter);




    }

}
