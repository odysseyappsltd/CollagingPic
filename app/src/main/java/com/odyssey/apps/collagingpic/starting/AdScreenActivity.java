package com.odyssey.apps.collagingpic.starting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.odyssey.apps.collagingpic.R;

/**
 * Created by macbookpro on 3/4/18.
 */

public class AdScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_screen);


//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
////                Intent i2 = new Intent(AdScreenActivity.this, MainActivity.class);
////                startActivity(i2);
////                overridePendingTransition( R.anim.go_down, R.anim.go_up );
//
//                finish();
//
//            }
//        }, 1000);







//        ImageView profilePic=(ImageView)findViewById(R.id.imageView);
//
////get bitmap of the image
//        Bitmap imageBitmap= BitmapFactory.decodeResource(getResources(),  R.drawable.sqr2);
//        RoundedBitmapDrawable roundedBitmapDrawable= RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
//
//
////setting radius
//        roundedBitmapDrawable.setCornerRadius(imageBitmap.getWidth()/2);
//        roundedBitmapDrawable.setAntiAlias(true);
//        profilePic.setImageDrawable(roundedBitmapDrawable);


        ListView listView = findViewById(R.id.ListView);
        CustomAdapter customAdapter = new CustomAdapter(this);
        listView.setAdapter(customAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0){

                    finish();

                }

                if (position == 1){

                    System.out.println("Photo vault");

                }


            }
        });



    }



}
