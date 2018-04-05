package com.odyssey.apps.collagingpic.skeleton;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.odyssey.apps.collagingpic.R;

public class HelpActivity extends AppCompatActivity {

    static final int NUMBER_OF_PAGES = 5;
    ViewPager mPager;
    MyAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*0.7),(int)(width*0.8));

        mAdapter = new MyAdapter(getSupportFragmentManager(),NUMBER_OF_PAGES);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(mAdapter);

    }
}
