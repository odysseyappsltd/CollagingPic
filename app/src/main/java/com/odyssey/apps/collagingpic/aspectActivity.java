package com.odyssey.apps.collagingpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class aspectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aspect);
    }
    public void aspect(View view){
        float aspectratio = 0.0f;
        if(view.getTag().equals("1")){
            aspectratio = 3.0f/2.0f;
        }
        else if(view.getTag().equals("2")){
            aspectratio = 4.0f/3.0f;
        }
        else if(view.getTag().equals("3")){
            aspectratio = 2.0f/3.0f;
        }
        else if(view.getTag().equals("4")){
            aspectratio = 3.0f/4.0f;
        }
        else if(view.getTag().equals("5")){
            aspectratio = 1.0f/1.0f;
        }

    }
}
