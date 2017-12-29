package com.me.kenburnsview;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by HULK on 11/25/2017.
 */

public class TabAdapter extends FragmentPagerAdapter {
    public static int int_items = 3;
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return fragment with respect to Position .
     */

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new BlankFragment();
            case 1:;
                return new BlankFragment();
            case 2:
                return  new BlankFragment();
        }
        return null;
    }

    @Override
    public int getCount() {

        return int_items;

    }

    /**
     * This method returns the title of the tab according to the position.
     */

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                String input = "On Going";
                return input;
            case 1:
                String in = "Completed";
                return in;
            case 2:
                String inp = "Cancelled";
                return inp;

        }
        return null;
    }
}


