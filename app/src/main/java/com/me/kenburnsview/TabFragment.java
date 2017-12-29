package com.me.kenburnsview;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {
    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v=inflater.inflate(R.layout.fragment_tab, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        DataBaseHelper dbHelper=new DataBaseHelper(getContext());
        dbHelper.addTrip(new TripDetailsPOJO("pn148","Pranay","SNU","Noida","TS 14 DR 4041","231117",1));
        Log.d("tttttttttttttttttt", "onCreateView: ");
        dbHelper.addTrip(new TripDetailsPOJO("pn148","Pranay","SNU","Noida","TS 14 DR 4041","231117",1));
        dbHelper.addTrip(new TripDetailsPOJO("pn148","Pranay","SNU","Noida","TS 14 DR 4041","231117",1));
        dbHelper.addTrip(new TripDetailsPOJO("pn148","Pranay","SNU","Noida","TS 14 DR 4041","231117",1));
        int count=dbHelper.getTripsCount();
        Log.d("tttttttttttttttttttttt", "onCreateView: "+count);
        Toast.makeText(getContext(),count+"",Toast.LENGTH_LONG);
        viewPager.setAdapter(new TabAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return  v;
    }

}
