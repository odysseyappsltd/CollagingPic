package com.odyssey.apps.collagingpic.starting;

import java.util.ArrayList;

public class SingletonArrayList {
    private static SingletonArrayList mInstance;
    private ArrayList<Integer> imageViewObjectHeight = null;
    private ArrayList<Integer> imageViewObjectWidth = null;

    public static SingletonArrayList getInstance() {
        if(mInstance == null)
            mInstance = new SingletonArrayList();

        return mInstance;
    }

    private SingletonArrayList() {
        imageViewObjectHeight = new ArrayList<Integer>();
        imageViewObjectWidth = new ArrayList<Integer>();
    }
    // retrieve array from anywhere
    public ArrayList<Integer> getHeightListArray() {
        return this.imageViewObjectHeight;
    }
    public ArrayList<Integer> getWidthtListArray() {
        return this.imageViewObjectWidth;
    }
    //Add element to array
    public void addToArray(Integer value) {
        imageViewObjectHeight.add(value);
        imageViewObjectWidth.add(value);
    }
}
