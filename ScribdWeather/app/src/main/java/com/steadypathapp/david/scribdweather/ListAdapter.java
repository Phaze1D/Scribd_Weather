package com.steadypathapp.david.scribdweather;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by david on 11/8/16.
 */



public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private CityWeather[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public View mView;
        public ViewHolder(View v) {
            super(v);
            mView = v;
        }
    }

    public ListAdapter(CityWeather[] dataset){
        mDataset = dataset;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_weather, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        TextView date = (TextView)holder.mView.findViewById(R.id.date_text);
        TextView title = (TextView)holder.mView.findViewById(R.id.title_text);
        TextView high = (TextView)holder.mView.findViewById(R.id.high_text);
        TextView low = (TextView)holder.mView.findViewById(R.id.low_text);
        TextView day = (TextView)holder.mView.findViewById(R.id.day_text);

        day.setText(mDataset[position].getDay());
        date.setText(mDataset[position].getDate());
        title.setText(mDataset[position].getText());
        high.setText("High " + mDataset[position].getHigh() +" C");
        low.setText("Low " + mDataset[position].getLow() + " C");
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
