package com.odyssey.apps.collagingpic.skeleton;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by OdysseyApps on 4/5/18.
 */

public class MyAdapter extends FragmentPagerAdapter {
    int NUMBER_OF_PAGES=0;
    public MyAdapter(FragmentManager fm,int NUMBER_OF_PAGES) {
        super(fm);
        this.NUMBER_OF_PAGES=NUMBER_OF_PAGES;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_PAGES;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return FragmentOne.newInstance(0, Color.BLACK);
            case 1:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentOne.newInstance(1, Color.BLACK);
            case 2:
                return FragmentOne.newInstance(2, Color.BLACK);
            case 3:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentOne.newInstance(3, Color.BLACK);
            case 4:
                // return a different Fragment class here
                // if you want want a completely different layout
                return FragmentOne.newInstance(4, Color.BLACK);
            default:
                return null;
        }
    }
}
