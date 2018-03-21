package com.example.macbookpro.advertisescreen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class HomeActivity extends Activity {

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
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        rootLayout.setLayoutParams(params);


        System.out.println("xMaxDp====="+xMaxDp);
        System.out.println("yMaxDp====="+yMaxDp);

        for(i=0;i<MainActivity.selection.size();i++) {


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

            mImageView.setOnTouchListener(new com.example.macbookpro.advertisescreen.MultiTouchListener(com.example.macbookpro.advertisescreen.HomeActivity.this));


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


}