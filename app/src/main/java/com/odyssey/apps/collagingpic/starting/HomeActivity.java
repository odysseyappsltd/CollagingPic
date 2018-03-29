package com.odyssey.apps.collagingpic.starting;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.popUp.PopUpData;

import java.util.Random;

public class HomeActivity extends Activity {

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            changeColor();
            System.out.println("Notified !");
        }
    };

    @Override
    public void onDestroy() {

        /*if(mInterstitialAd.isLoaded() && !CheckIf.isPurchased("admob",EditTextActivity.this)){
            mInterstitialAd.show();
        }*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();

    }

    private void changeColor(){


        // Code here to change color . . .
        System.out.println("Chosen color : " + PopUpData.getSharedInstance().getColor());
    }


    private int[] imageArray;
    int i;
    int min = 0;
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    int xMax = getScreenWidth();
    int yMax = getScreenHeight();
    int xMaxDp;
    int yMaxDp;
    int[][] positionArray = {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Notifications
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_COLOR,mMessageReceiver,this);


//        imageArray = new int[]{R.drawable.pic12, R.drawable.pic3, R.drawable.sqr3};
//        imageArray = getIntent().getStringExtra("EXTRA_SESSION_ID");

         xMaxDp = PxToDp(this,xMax);
         yMaxDp = PxToDp(this,yMax);

        findViewById(R.id.collageBgView).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        });

//        ImageView mImageView1 = (ImageView) findViewById(R.id.collageView1);
//        ImageView mImageView2 = (ImageView) findViewById(R.id.collageView2);
//        ImageView mImageView3 = (ImageView) findViewById(R.id.collageView3);
//        ImageView mImageView4 = (ImageView) findViewById(R.id.collageView4);

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.RelativeLayout);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        rootLayout.setLayoutParams(params);




        System.out.println("xMaxDp====="+xMaxDp);
        System.out.println("yMaxDp====="+yMaxDp);

        for(i=0; i<MainActivity.selection.size(); i++) {


            Random r = new Random();
            int xRandom = r.nextInt(xMaxDp - min + 1) + min;
            int yRandom = r.nextInt(yMaxDp - min + 1) + min;
            int rotation = r.nextInt(30-(-30)+1)+(-30);
            System.out.println("xRandom====="+xRandom);
            System.out.println("yRandom====="+yRandom);

            ImageView mImageView = new ImageView(this);
//            mImageView.setImageResource(imageArray[i]);
            mImageView.setImageBitmap( resize(BitmapFactory.decodeFile(MainActivity.selection.get(i)),800,800) );

            float scaleX = (float) (mImageView.getScaleX() * 0.5);
            float scaleY = (float) (mImageView.getScaleY() * 0.5);
            mImageView.setScaleX(scaleX);
            mImageView.setScaleY(scaleY);
            mImageView.setX(xRandom-xRandom*scaleX);
            mImageView.setY(yRandom-yRandom*scaleY);
            mImageView.setRotation(rotation);
            rootLayout.addView(mImageView);
//            setContentView(rootLayout);

//            RelativeLayout.LayoutParams layoutParams =
//                    (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//            mImageView.setLayoutParams(layoutParams);

            mImageView.setOnTouchListener(new MultiTouchListener(HomeActivity.this));


//




//            final GestureDetector.SimpleOnGestureListener listener = new GestureDetector.SimpleOnGestureListener() {
//                @Override
//                public boolean onDoubleTap(MotionEvent e) {
//                    Toast.makeText(HomeActivity.this, "onDoubleTap", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//
//                @Override
//                public void onLongPress(MotionEvent e) {
//                    Toast.makeText(HomeActivity.this, "onLongPress", Toast.LENGTH_SHORT).show();
//                }
//            };
//
//            final GestureDetector detector = new GestureDetector(listener);
//
//            detector.setOnDoubleTapListener(listener);
//            detector.setIsLongpressEnabled(true);
//
        }




//        ImageView mImageView2 = new ImageView(this);
//        mImageView2.setImageResource(R.drawable.pic12);
//        rootLayout.addView(mImageView2);
//        setContentView(rootLayout);
//
//        ImageView mImageView3 = new ImageView(this);
//        mImageView3.setImageResource(R.drawable.pic3);
//        rootLayout.addView(mImageView3);
//        setContentView(rootLayout);

//        RelativeLayout.LayoutParams layoutParams1 =
//                (RelativeLayout.LayoutParams)mImageView1.getLayoutParams();
//        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//        mImageView1.setLayoutParams(layoutParams1);
//
//        RelativeLayout.LayoutParams layoutParams2 =
//                (RelativeLayout.LayoutParams)mImageView2.getLayoutParams();
//        layoutParams2.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//        mImageView2.setLayoutParams(layoutParams2);
//
//
//        RelativeLayout.LayoutParams layoutParams3 =
//                (RelativeLayout.LayoutParams)mImageView3.getLayoutParams();
//        layoutParams3.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//        mImageView3.setLayoutParams(layoutParams3);


//        mImageView1.setOnTouchListener(new MultiTouchListener());
//        mImageView2.setOnTouchListener(new MultiTouchListener());
//        mImageView3.setOnTouchListener(new MultiTouchListener());

//        findViewById(R.id.collageView1).setOnTouchListener(new MultiTouchListener());
//        findViewById(R.id.collageView2).setOnTouchListener(new MultiTouchListener());
//        findViewById(R.id.collageView3).setOnTouchListener(new MultiTouchListener());
//        findViewById(R.id.collageView4).setOnTouchListener(new MultiTouchListener());

    }


    private static Bitmap resize(Bitmap image, int maxWidth, int maxHeight) {
        if (maxHeight > 0 && maxWidth > 0) {
            int width = image.getWidth();
            int height = image.getHeight();
            float ratioBitmap = (float) width / (float) height;
            float ratioMax = (float) maxWidth / (float) maxHeight;

            int finalWidth = maxWidth;
            int finalHeight = maxHeight;
            if (ratioMax > ratioBitmap) {
                finalWidth = (int) ((float)maxHeight * ratioBitmap);
            } else {
                finalHeight = (int) ((float)maxWidth / ratioBitmap);
            }
            image = Bitmap.createScaledBitmap(image, finalWidth, finalHeight, true);
            return image;
        } else {
            return image;
        }
    }

    public int PxToDp(Context context, int px) {
        return (int)(px / context.getResources().getDisplayMetrics().density);
    }


    public void frameAct(View view){

        finish();
    }
    public void styleAct(View view){

//        Intent style = new Intent(HomeActivity.this,PopUpActivity.class);
//        startActivity(style);


    }
    public void aspectAct(View view){

    }
    public void shareAct(View view){

//    layout to bitmap image
      /*  RelativeLayout forImage = (RelativeLayout)findViewById(R.id.RelativeLayout);

        forImage.setDrawingCacheEnabled(true);

        forImage.buildDrawingCache();

        Bitmap bm = forImage.getDrawingCache();  */

      //image filtering
//        Bitmap result = stickerView.createBitmap();
//        Bitmap imageviewImageBitmap = Bitmap.createBitmap(result, bgImage.getLeft(), bgImage.getTop(), bgImage.getWidth(), bgImage.getHeight());
//        DataPassingSingelton.getInstance().setImage(imageviewImageBitmap);
//        Intent erase = new Intent(MainActivity.this, FilterActivity.class);
//        startActivityForResult(erase, REQUEST_GET_FILTER_IMAGE);


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p200);
//        String path = bitmap.toString();
//        Uri imageUri = Uri.parse(path);
//        System.out.println(imageUri);
//        Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(imageUri).build();
//        startActivityForResult(imageEditorIntent, 1);
//        finish(); // Comment this out to receive edited image


    }
    public void settingAct(View view){
//        Intent setting = new Intent(HomeActivity.this,SettingsActivity.class);
//        startActivity(setting);

    }


}