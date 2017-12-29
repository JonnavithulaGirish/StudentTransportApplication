package com.me.kenburnsview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HULK on 11/26/2017.
 */

public class Custom_Trip_Adapter extends BaseAdapter {

    Context context;
    ArrayList<TripDetailsPOJO> trip=new ArrayList<>();
    LayoutInflater inflater;

    public Custom_Trip_Adapter(Context context, ArrayList<TripDetailsPOJO> trip ){
        this.context=context;
        this.trip.clear();
        this.trip=trip;

    }
    public void clearList()
    {
        if(trip!=null)
            trip.clear();
    }
    public void setBus(ArrayList<TripDetailsPOJO> trip )
    {
        this.trip=trip;
    }
    @Override
    public int getCount() {
        Log.d("mytag", "setBus: "+trip.size());
        return trip.size();
    }

    @Override
    public Object getItem(int position) {
        return trip.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.from(context).inflate(R.layout.list_trip_item,null);
        TextView tripID= (TextView) convertView.findViewById(R.id.tripId);
        TextView tripName= (TextView) convertView.findViewById(R.id.tripName);
        TextView tripSource= (TextView) convertView.findViewById(R.id.tripSource);
        TextView tripDestination= (TextView) convertView.findViewById(R.id.tripDestination);
        TextView tripDate= (TextView) convertView.findViewById(R.id.tripDate);
        TextView tripBusNo= (TextView) convertView.findViewById(R.id.tripBusNo);

        tripID.setText(trip.get(position).id);
        tripName.setText(trip.get(position).name);
        tripSource.setText(trip.get(position).source);
        tripDestination.setText(trip.get(position).destination);
        tripDate.setText(trip.get(position).date);
        tripBusNo.setText(trip.get(position).busno);


        return convertView;
    }



}
