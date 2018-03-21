package com.odyssey.apps.popUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.odyssey.apps.IAP.IAPActivity;
import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.collagingpic.R;

/**
 * Created by admin on 2018-03-11.
 */

public class PopUpActivity extends Activity {





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        // Setting Display Metrics for Activity
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int)(width*0.8),(int)(width*0.8));

        //Segment Button
        Button colorButton = (Button) findViewById(R.id.colorButton);
        Button patternButton = (Button) findViewById(R.id.patternButton);
        Button shapeButton = (Button) findViewById(R.id.shapeButton);


        //SettingUp Segment Layout
        final ConstraintLayout colorPicker = (ConstraintLayout) View.inflate(this,R.layout.did_pressed_color_button,null);
        final ConstraintLayout patternPicker = (ConstraintLayout) View.inflate(this,R.layout.did_pressed_pattern_button,null);
        final ConstraintLayout shapePicker = (ConstraintLayout) View.inflate(this,R.layout.did_pressed_shape_button,null);

        //SettingUp pattern Picker Adapter
        //Here . . .
        final PatternPickerAdapter patternPickerAdapter = new PatternPickerAdapter(PopUpActivity.this);

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addLayoutToDynamicView(colorPicker);

                //Gradient View Setup
                GradientView mTop = (GradientView) findViewById(R.id.top);
                GradientView mBottom = (GradientView) findViewById(R.id.bottom);
                mTop.setBrightnessGradientView(mBottom);
                mTop.setColor(PopUpData.getSharedInstance().getColor());

                    mBottom.setOnColorChangedListener(new GradientView.OnColorChangedListener() {
                        @Override
                        public void onColorChanged(GradientView view, int color) {

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                //Here is color
                                System.out.println(color);
                                //Color Saved in Singleton
                                PopUpData.getSharedInstance().saveColor(color);
                                //Now A Notification is needed to send to change the
                                //background color as in Singleton
                                //Send here . . .





                            }
                        }
                    });

                }

        });

        //Set An Initial Click to colorButton
        colorButton.performClick();

        patternButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addLayoutToDynamicView(patternPicker);
                GridView collectionView = (GridView) findViewById(R.id.collectionView);
                collectionView.setAdapter(patternPickerAdapter);
                collectionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        if(!CheckIf.isPurchased(IAPData.getSharedInstance().PATTERN,PopUpActivity.this) && i>14) {
                            Intent iap = new Intent(PopUpActivity.this,IAPActivity.class);
                            startActivity(iap);
                        }
                        else {

                            PopUpData.getSharedInstance().savePatternForIndex(i);
                            //Send Notification
                        }

                    }
                });


            }
        });

        shapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLayoutToDynamicView(shapePicker);
            }
        });





    }



    void addLayoutToDynamicView(ConstraintLayout constraintLayout){

        //Adding Color Picker Layout to dynamic layout
        ConstraintLayout dyanamicView = (ConstraintLayout) findViewById(R.id.dynamicViewGroup);
        dyanamicView.removeAllViews();
        dyanamicView.addView(constraintLayout,0);

        // Constraint Setup
        ConstraintSet set = new ConstraintSet();
        set.clone(dyanamicView);
        set.connect(constraintLayout.getId(),ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
        set.connect(constraintLayout.getId(),ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.connect(constraintLayout.getId(),ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.connect(constraintLayout.getId(),ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
        set.constrainHeight(constraintLayout.getId(), dyanamicView.getHeight());
        set.constrainWidth(constraintLayout.getId(), dyanamicView.getWidth());
        set.applyTo(dyanamicView);
    }




}



