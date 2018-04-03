package com.odyssey.apps.collagingpic.starting;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.odyssey.apps.Settings.SettingsActivity;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.collagingpic.skeleton.AspectData;
import com.odyssey.apps.collagingpic.skeleton.Colage;
import com.odyssey.apps.collagingpic.skeleton.LayerFrame;
import com.odyssey.apps.collagingpic.skeleton.aspectActivityForFreeStyle;
import com.odyssey.apps.popUp.PopUpData;
import com.odyssey.apps.collagingpic.skeleton.SkeletonActivity;
import com.odyssey.apps.popUp.PopUpActivity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import android.graphics.PorterDuff.Mode;

import it.sephiroth.android.library.picasso.Picasso;


public class HomeActivity extends Activity {


    ImageView imageView;
    ImageView imageView2;
    int shrinkValue;




    private BroadcastReceiver mColorReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
                changeColor();
            System.out.println("Notified !");
        }
    };


    private BroadcastReceiver mPatternReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            changePattern();
            System.out.println("Notified !");
        }
    };
    private BroadcastReceiver mShrinkValueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            changeShrinkValue();
            System.out.println("Notified !");
        }
    };

    private BroadcastReceiver mRoundValueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            changeRoundValue();
            System.out.println("Notified !");
        }
    };

    private BroadcastReceiver mShadeValueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            changeShadeValue();
            System.out.println("Notified !");
        }
    };
    private BroadcastReceiver mAspectValueReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            setaspect();
            System.out.println("Notified !");
        }
    };





    @Override
    public void onDestroy() {

        /*if(mInterstitialAd.isLoaded() && !CheckIf.isPurchased("admob",EditTextActivity.this)){
            mInterstitialAd.show();
        }*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mAspectValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mColorReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPatternReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mShrinkValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRoundValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mShadeValueReceiver);
        super.onDestroy();

    }

    private void changeColor(){


        // Code here to change color . . .
        imageView.setVisibility(View.VISIBLE);
        imageView2.setVisibility(View.INVISIBLE);
//        imageView2.setVisibility(View.VISIBLE);
//        imageView.setVisibility(View.INVISIBLE);


        int receviedColor = PopUpData.getSharedInstance().getColor() ;
        System.out.println("Chosen color : " + receviedColor );

        imageView.setBackgroundColor(receviedColor);


    }

    private void changePattern(){


        // Code here to change color . . .
        imageView2.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.INVISIBLE);
//        imageView.setVisibility(View.VISIBLE);
//        imageView2.setVisibility(View.INVISIBLE);



        int receviedPatternID = PopUpData.getSharedInstance().getPattern() ;
        System.out.println("Chosen Pattern : " + receviedPatternID );
        imageView2.setImageResource(receviedPatternID);
//        Drawable bm = getResources().getDrawable(receviedPatternID);
//        imageView.setBackground(bm);


    }

    private void changeShrinkValue(){

         shrinkValue = PopUpData.getSharedInstance().getShrinkValue();
        System.out.println("Shrink Value Received :" + shrinkValue);

        for( int i=0; i<MainActivity.selection.size(); i++) {
            rootLayout.findViewWithTag(i).setPadding(shrinkValue,shrinkValue,shrinkValue,shrinkValue);
        }


    }

    private void changeRoundValue(){

        int roundValue = PopUpData.getSharedInstance().getRoundValue();
        System.out.println("Round Value Received :" +roundValue);

        // Code here . . .


        for( int i=0; i<MainActivity.selection.size(); i++) {
            ImageView img = (ImageView) rootLayout.findViewWithTag(i);
            img.setImageBitmap(roundCornerImage(bitmapArray.get(i),roundValue));

//            RoundedImageView img2 = new RoundedImageView(rootLayout.findViewWithTag(i).getContext());
//            img2.setImageBitmap(bitmapArray.get(i));


        }

    }




    private void changeShadeValue(){

        int shadeValue = PopUpData.getSharedInstance().getShadeValue();
        System.out.println("Shade Value Received :" +shadeValue);

        // Code here . . .


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

    private static final String TAG = SkeletonActivity.class.getName();
    private static final int IMAGE_EDITOR_RESULT = 1;

    ArrayList<Bitmap> bitmapArray = new ArrayList<Bitmap>();

    RelativeLayout rootLayout;
    float layoutHeight;
    float layoutWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Notifications
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_COLOR,mColorReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_ASPECT_VALUE,mAspectValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_PATTERN,mPatternReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_SHRINK_VALUE,mShrinkValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_ROUND_VALUE,mRoundValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_SHADE_VALUE,mShadeValueReceiver,this);



        imageView = (ImageView)findViewById(R.id.collageBgView);
        imageView2 = (ImageView)findViewById(R.id.collageBgView2);

//        imageView2.setVisibility(View.INVISIBLE);
//        imageView.setVisibility(View.VISIBLE);

         xMaxDp = PxToDp(this,xMax);
         yMaxDp = PxToDp(this,yMax);

        rootLayout = (RelativeLayout) findViewById(R.id.RelativeLayout);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        rootLayout.setLayoutParams(params);
        layoutHeight = getScreenHeight()-150;
        layoutWidth = getScreenWidth();


        findViewById(R.id.collageBgView).setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                return true;
            }
        });


        createImageView();

    }

    private void createImageView(){


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
             Bitmap imgBitmap = resize(BitmapFactory.decodeFile(MainActivity.selection.get(i)),800,800);
            mImageView.setImageBitmap( imgBitmap );
            bitmapArray.add(imgBitmap);

            float scaleX = (float) (mImageView.getScaleX() * 0.5);
            float scaleY = (float) (mImageView.getScaleY() * 0.5);
            mImageView.setBackgroundColor(Color.WHITE);
            mImageView.setScaleX(scaleX);
            mImageView.setScaleY(scaleY);
            mImageView.setX(xRandom-xRandom*scaleX);
            mImageView.setY(yRandom-yRandom*scaleY);
            mImageView.setRotation(rotation);

            mImageView.setTag(Integer.valueOf(i));
            rootLayout.addView(mImageView);
//            setContentView(rootLayout);

//            RelativeLayout.LayoutParams layoutParams =
//                    (RelativeLayout.LayoutParams) mImageView.getLayoutParams();
//            layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
//            mImageView.setLayoutParams(layoutParams);

            mImageView.setOnTouchListener(new MultiTouchListener(HomeActivity.this));

        }

    }


    public Bitmap roundCornerImage(Bitmap raw, float round) {
        int width = raw.getWidth();
        int height = raw.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        canvas.drawARGB(0, 0, 0, 0);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.parseColor("#000000"));

        final Rect rect = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(rect);

        canvas.drawRoundRect(rectF, round, round, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(raw, rect, rect, paint);

        return result;
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


    public void setaspect(){
        //Set aspect is being called against evry button click
        //float aspectratio=1.0f;


        float aspectratio = AspectData.getSharedInstance().getAspectValue();

        System.out.println("Received aspect value : " + aspectratio);


        RelativeLayout.LayoutParams mainParam = (RelativeLayout.LayoutParams) rootLayout.getLayoutParams();
        if(aspectratio==(float) 0.8) {
            mainParam.width = (int) (layoutWidth*(float) 0.9);
            mainParam.height=(int)(layoutHeight*(float) 0.76);

        }
        else{
            mainParam.width = (int)layoutWidth;
            mainParam.height=(int)(layoutHeight*aspectratio);
        }
        rootLayout.setLayoutParams(mainParam);

    }



    public int PxToDp(Context context, int px) {
        return (int)(px / context.getResources().getDisplayMetrics().density);
    }


    public void frameAct(View view){

        finish();
    }
    public void styleAct(View view){

        Intent style = new Intent(HomeActivity.this,PopUpActivity.class);
        startActivity(style);

    }
    public void aspectAct(View view){

//        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pp2);
//        File f = new File(getExternalCacheDir()+"/collagingpictempimage.png");
//        try {
//            FileOutputStream outStream = new FileOutputStream(f);
//            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
//            outStream.flush();
//            outStream.close();
//        } catch (Exception e) { throw new RuntimeException(e); }
//        System.out.println(Uri.fromFile(f));
//        Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(Uri.fromFile(f)).build();
//        startActivityForResult(imageEditorIntent, 1);
////        finish(); // Comment this out to receive edited image

        Intent aspect = new Intent(HomeActivity.this,aspectActivityForFreeStyle.class);
        startActivity(aspect);



    }

    public void shareAct(View view){

//    layout to bitmap image
        RelativeLayout forImage = (RelativeLayout)findViewById(R.id.RelativeLayout);

        forImage.setDrawingCacheEnabled(true);

        forImage.buildDrawingCache();

        Bitmap result = forImage.getDrawingCache();

      //image filtering

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.PNG, 100, bs);
        byte[] byteArray = bs.toByteArray();
        Intent send = new Intent(HomeActivity.this, FilterActivity.class);
        send.putExtra("collageBitmap",byteArray);
        startActivity(send);



    }
    public void settingAct(View view){
        Intent setting = new Intent(HomeActivity.this,SettingsActivity.class);
        startActivity(setting);

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE_EDITOR_RESULT:
                    Uri editedImageUri = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
                    Log.d(TAG, "editedImageUri: " + editedImageUri.toString());
                    Bundle extra = data.getExtras();
                    if (extra != null) {
                        boolean changed = extra.getBoolean(AdobeImageIntent.EXTRA_OUT_BITMAP_CHANGED);
                        Log.d(TAG, "Image edited: " + changed);
                        if (changed) {
                            //
                        }
                    }
                    break;
            }
        }

    }


}