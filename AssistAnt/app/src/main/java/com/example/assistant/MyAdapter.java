package com.example.assistant;

/**
 * Created by ilay on 10/01/2018.
 */


import android.content.Context;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    String[] mplacesValues;
    Context mcontext;
    View mview;
    ViewHolder mviewHolder;

    public MyAdapter(Context context, String[] placesValues) {

        mplacesValues = placesValues;
        mcontext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mtextView;

        public ViewHolder(View view) {

            super(view);

            mtextView = (TextView) view.findViewById(R.id.places_textview);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mview = LayoutInflater.from(mcontext).inflate(R.layout.recyclerview_items, parent, false);

        mviewHolder = new ViewHolder(mview);

        return mviewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mtextView.setText(mplacesValues[position]);
    }

    @Override
    public int getItemCount() {

        return mplacesValues.length;
    }
}