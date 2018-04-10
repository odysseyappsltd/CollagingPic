package com.odyssey.apps.collagingpic.skeleton;

import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;



import com.baoyz.actionsheet.ActionSheet;
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity;
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.odyssey.apps.Admobs.AdmobClass;
import com.odyssey.apps.Admobs.Advertisement;

import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.Settings.SettingsActivity;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.odyssey.apps.collagingpic.DSPhotoLab;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.collagingpic.starting.FilterActivity;
import com.odyssey.apps.collagingpic.starting.HomeActivity;
import com.odyssey.apps.collagingpic.starting.MainActivity;
import com.odyssey.apps.popUp.PopUpActivity;
import com.odyssey.apps.popUp.PopUpData;
import com.odyssey.apps.util.Custom;
import com.odyssey.apps.util.FileUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SkeletonActivity extends AppCompatActivity implements View.OnTouchListener{

    private static final String TAG = SkeletonActivity.class.getName();
    private static final int IMAGE_EDITOR_RESULT = 1;
    private Colage[] allColages = new Colage[50];
    ViewGroup mainView;
    private int initialPosX;
    private int initialPosY;
    int border;
    int slider_bar;
    int NO_OF_COLLAGE_FRAMES;


    private Matrix[] matrix = new Matrix[50];
    private Matrix[] savedMatrix = new Matrix[50];
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private PointF[] oldDxy = new PointF[50];//oldDx=0.0f,oldDy=0.0f;
    private float[] oldScale = new float[50];


    private int mVSize;
    private Colage topColage;
    private Colage bottomColage;
    private Colage leftColage;
    private Colage rightColage;
    private RectF fakeMainView;
    private int layerid;
    private static final float MIN_ZOOM = 1f,MAX_ZOOM = 5f;
    private int CLICK_ACTION_THRESHOLD = 10;
    RelativeLayout pop;
    int swapFrom=0;
    private InterstitialAd mInterstitialAd;
    Boolean isSwap=false;
    ImageView activateImageView;
    ArrayList<RectF> layerSize;
    /*int imgSet[] = new int[]{R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2};*/
    Bitmap imgSet[] = new Bitmap[50];



    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            purchasedJustNow();
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

    private BroadcastReceiver mHelpRunCommandReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            helpScreen();
            System.out.println("Notified !");
        }
    };



    private void changeColor(){


        // Code here to change color . . .

        int receviedColor = PopUpData.getSharedInstance().getColor() ;
        System.out.println("Chosen color : " + receviedColor );
        mainView.setBackgroundColor(receviedColor);



    }

    private void changePattern(){


        // Code here to change color . . .

        int receviedPatternID = PopUpData.getSharedInstance().getPattern() ;
        System.out.println("Chosen Pattern : " + receviedPatternID );
        //mainView.setBackgroundColor(Color.TRANSPARENT);
        mainView.setBackground(ContextCompat.getDrawable(this, receviedPatternID));


    }

    private void changeShrinkValue(){

        int shrinkValue = PopUpData.getSharedInstance().getShrinkValue();
        System.out.println("Shrink Value Received :" + shrinkValue);
        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++){
            Colage colage = allColages[i];
            colage.setPadding(slider_bar+((int)(shrinkValue/12.0f)-4),slider_bar+((int)(shrinkValue/12.0f)-4),
                    slider_bar+((int)(shrinkValue/12.0f)-4),slider_bar+((int)(shrinkValue/12.0f)-4));
        }

        // Code here  . .


    }

    private void changeRoundValue(){

        int roundValue = PopUpData.getSharedInstance().getRoundValue();
        System.out.println("Round Value Received :" +roundValue);

        GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
                new int[] {0xFFFFFFFF,0xFFFFFFFF});
        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++) {
            Colage col = allColages[i];
            gd.setCornerRadius(roundValue);
            col.setBackground(gd);

            CardView cv = (CardView) col.getChildAt(0);
            cv.setRadius((float)roundValue);
            //col.setBackground(gradientDrawable);
            //Drawable dr = iv.getDrawable();
            //Bitmap bm = ((BitmapDrawable)dr).getBitmap();
            //Bitmap roundedBitmap = roundCornerImage(bm,roundValue);
            //iv.setImageBitmap(roundedBitmap);
        }


    }


    private void changeShadeValue(){

        int shadeValue = PopUpData.getSharedInstance().getShadeValue();
        System.out.println("Shade Value Received :" +shadeValue);

        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++) {
            Colage col = allColages[i];
            CardView cv = (CardView) col.getChildAt(0);
            cv.setCardElevation(shadeValue);

        }

        // Code here . . .


    }

    @Override
    public void onDestroy() {

        /*if(mInterstitialAd.isLoaded() && !CheckIf.isPurchased("admob",EditTextActivity.this)){
            mInterstitialAd.show();
        }*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mAspectValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mColorReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPatternReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mShrinkValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRoundValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mShadeValueReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mHelpRunCommandReceiver);
        super.onDestroy();

    }

    private void purchasedJustNow(){
        //collectionView.invalidate();
        if(CheckIf.isPurchased(IAPData.getSharedInstance().ADMOB,this)) {
            findViewById(R.id.ASAdmob).setVisibility(View.GONE);
        }
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        for(int i=0;i<MainActivity.selection.size();i++) {
            Bitmap bitmap = BitmapFactory.decodeFile(MainActivity.selection.get(i));
            int rotateImage = getCameraPhotoOrientation(this, Uri.parse(MainActivity.selection.get(i)), MainActivity.selection.get(i));
            System.out.println("asdf"+rotateImage);
            Matrix matrix = new Matrix();
            matrix.postRotate(rotateImage);
            bitmap = Bitmap.createBitmap(bitmap , 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(),
                    matrix, true);
            imgSet[i] = resize(bitmap, 800, 800);
        }

        layerid = getIntent().getIntExtra("layerId",1);
        if(layerid!=0&&MainActivity.selection.size()==1){
            for(int i=0;i<4;i++){
                imgSet[i]=imgSet[0];
            }
        }

        //Notifications
        NotificationCenter.addReceiver(NotiData.getSharedInstance().SOMETHING_JUST_PURCHASED,mMessageReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_ASPECT_VALUE,mAspectValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_COLOR,mColorReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_PATTERN,mPatternReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_SHRINK_VALUE,mShrinkValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_ROUND_VALUE,mRoundValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_SHADE_VALUE,mShadeValueReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_RUN_HELP_SCREEN,mHelpRunCommandReceiver,this);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_skeleton);
        mVSize = getScreenWidth()-50;

        RelativeLayout fullView = (RelativeLayout)findViewById(R.id.colage_view);
        mainView = (RelativeLayout)findViewById(R.id.view);
        RelativeLayout.LayoutParams mainParam = (RelativeLayout.LayoutParams) mainView.getLayoutParams();
        mainParam.width=mVSize;
        mainParam.height=mVSize;
        mainView.setLayoutParams(mainParam);

        border = (int) getResources().getDimension(R.dimen.border);
        slider_bar = (int) getResources().getDimension(R.dimen.slider_bar);
        //layerid=48;

        LayerFrame layer = new LayerFrame(layerid);
        ArrayList<RectF> layerSize = layer.getSize();
        ArrayList<int[]> layerWBro = layer.getWidthBrothers();
        ArrayList<int[]> layerHBro = layer.getHeightBrothers();
        NO_OF_COLLAGE_FRAMES=layerSize.size();

        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++) {
            Colage colage = addColage((int)(mVSize*layerSize.get(i).left), (int)(mVSize*layerSize.get(i).top),
                    (int)(mVSize*layerSize.get(i).right), (int)(mVSize*layerSize.get(i).bottom),i);
            mainView.addView(colage);
            allColages[i] = colage;
            colage.setOnTouchListener(this);
            colage.setHeightBrothers(layerHBro.get(i));
            colage.setWidthBrothers(layerWBro.get(i));

        }
        for(int i=0;i<savedMatrix.length;i++){
            matrix[i]=new Matrix();
            savedMatrix[i]=new Matrix();
            oldDxy[i]=new PointF(0.0f,0.0f);
            oldDxy[i]=new PointF(0.0f,0.0f);
            oldScale[i]=1.0f;
        }

        pop = (RelativeLayout)findViewById(R.id.popupview);
        //fullView.addView(pop);
        pop.setVisibility(View.INVISIBLE);
        //scaleImageView(allColages[0]);
        //scaleAllImageView();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scaleAllImageView();
            }
        }, 1500);

        if(PopUpData.getSharedInstance().getPattern()==R.drawable.p1)
            changeColor();
        else
            changePattern();

        changeRoundValue();
        changeShadeValue();
        changeShrinkValue();






        //

        // Admob

        MobileAds.initialize(this, Advertisement.getSharedInstance().getNativeAdvanceAdAppID());
        final AdLoader adLoader = new AdLoader.Builder(this, Advertisement.getSharedInstance().getNativeAdvanceAdUnitID())
                .forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                    @Override
                    public void onAppInstallAdLoaded(NativeAppInstallAd appInstallAd) {
                        // Show the app install ad.
                        //Toast.makeText(MainActivity.this, "Ad App Install loading", Toast.LENGTH_SHORT).show();;
                        FrameLayout frameLayout  = (FrameLayout)findViewById(R.id.ASAdmob);
                        frameLayout.setVisibility(View.VISIBLE);
                        NativeAppInstallAdView adView = (NativeAppInstallAdView) getLayoutInflater()
                                .inflate(R.layout.ad_app_install, null);
                        Advertisement.getSharedInstance().populateAppInstallAdView(appInstallAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    }
                })
                .forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                    @Override
                    public void onContentAdLoaded(NativeContentAd contentAd) {

                        // Show the content ad.
                        //Toast.makeText(MainActivity.this, "Ad Content loading", Toast.LENGTH_SHORT).show();
                        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.ASAdmob);
                        frameLayout.setVisibility(View.VISIBLE);
                        NativeContentAdView adView = (NativeContentAdView) getLayoutInflater()
                                .inflate(R.layout.ad_content, null);
                        Advertisement.getSharedInstance().populateContentAdView(contentAd, adView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    }
                })
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();



                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // Handle the failure by logging, altering the UI, and so on.
                        //Toast.makeText(MainActivity.this, "Failed Ad loading", Toast.LENGTH_SHORT).show();
                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        // Methods in the NativeAdOptions.Builder class can be
                        // used here to specify individual options settings.
                        .build())
                .build();

        //Admob Visibility
        //findViewById(R.id.AMAdmob).setVisibility(View.GONE);
        if (!CheckIf.isPurchased(IAPData.getSharedInstance().ADMOB,this)){
            adLoader.loadAd(new AdRequest.Builder().build());
        } else {
            findViewById(R.id.ASAdmob).setVisibility(View.GONE);
        }

        if (!CheckIf.isPurchased(IAPData.getSharedInstance().ADMOB,this)) {
            mInterstitialAd = new InterstitialAd(this);
            mInterstitialAd.setAdUnitId(AdmobClass.INTERSTITIAL_AD_UNIT_ID);
            AdRequest request = new AdRequest.Builder().build();
            mInterstitialAd.loadAd(request);
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {

                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }

                }
            });
        }


        SharedPreferences preferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        Boolean isFirstTime = preferences.getBoolean("firstTime",true);
        if(isFirstTime) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
            helpScreen();
        }
    }



    public Colage addColage(int x, int y, int width, int height, int i){


        Colage colage = new Colage(this);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                width,height);
        rlp.leftMargin =x;
        rlp.topMargin = y;
        colage.setLayoutParams(rlp);
        colage.setPadding(slider_bar,slider_bar,slider_bar,slider_bar);
        //colage.setClipChildren(true);
        //colage.setClipBounds(new Rect(1,1,1,1));

        CardView cv = new CardView(this);
        RelativeLayout.LayoutParams cvp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        cvp.setMargins(10,10,10,10);
        cv.setLayoutParams(cvp);
        cv.setPreventCornerOverlap(false);
        //cv.setMaxCardElevation(10.0f);



        ImageView iv = new ImageView(this);
        iv.setTag(Integer.valueOf(i));
        iv.setImageBitmap(imgSet[i]);
        iv.setBackgroundColor(Color.parseColor("#225465"));
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);

        cv.addView(iv);

        colage.addView(cv);
        iv.setOnTouchListener(this);
        return colage;
    }
    private void moveCollages(int x, int y){

        x-=(int)mainView.getX();
        y-=(int)mainView.getY();

        int border = (int) getResources().getDimension(R.dimen.border);
        if(fakeMainView!=null) {
            if(leftColage!=null)
                if (fakeMainView.left + border < initialPosX && fakeMainView.right - border + fakeMainView.left > initialPosX
                        && x > (int) (fakeMainView.left + 2.5 * border) && x < fakeMainView.left + fakeMainView.right - (int) (2.5 * border))
                {

                    System.out.println("asf");
                    if (leftColage != null) {
                        moveLeftCollage(x);
                    }
                    if (rightColage != null) {
                        moveRightCollage(x);
                    }
                }
            if(topColage!=null)
                if (initialPosY > border + fakeMainView.top && initialPosY < (fakeMainView.bottom - border + fakeMainView.top)
                        && y > (int) (fakeMainView.top + 2.5 * border) && y < fakeMainView.top + fakeMainView.bottom - (int) (2.5 * border)) {
                    if (bottomColage != null) {
                        moveBottomCollage(y);
                    }
                    if (topColage != null) {
                        moveTopCollage(y);
                    }
                }

        }
        //mainView.invalidate();
    }
    private void findColagesFromPoint(int x,int y){
        topColage=null;
        bottomColage=null;
        leftColage=null;
        rightColage=null;
        int dx=x+(int)(border);
        int dy=y+(int)(border);
        int cx=x-(int)(border);
        int cy=y-(int)(border);
        int br=slider_bar;
        System.out.println("asdf"+allColages[0].getX());


        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++){
            if(allColages[i].getHeight()+allColages[i].getY()-br>dy&&allColages[i].getX()+br<x&&allColages[i].getY()+br<dy
                    &&allColages[i].getWidth()+allColages[i].getX()-br>x){
                bottomColage=allColages[i];

            }
            else if(allColages[i].getHeight()+allColages[i].getY()-br>cy&&allColages[i].getX()+br<x&&allColages[i].getY()+br<cy
                    &&allColages[i].getWidth()+allColages[i].getX()-br>x){
                topColage=allColages[i];

            }
            else if(allColages[i].getHeight()+allColages[i].getY()-br>y&&allColages[i].getX()+br<cx&&allColages[i].getY()+br<y
                    &&allColages[i].getWidth()+allColages[i].getX()-br>cx){
                leftColage=allColages[i];
                System.out.println("left"+i);

            }
            else if(allColages[i].getHeight()+allColages[i].getY()-br>y&&allColages[i].getX()+br<dx&&allColages[i].getY()+br<y
                    &&allColages[i].getWidth()+allColages[i].getX()-br>dx){
                rightColage=allColages[i];
                System.out.println("right"+i);

            }

        }
        if(leftColage==null)
            rightColage=null;
        if(rightColage==null)
            leftColage=null;
        if(topColage==null)
            bottomColage=null;
        if(bottomColage==null)
            topColage=null;
        if(leftColage!=null&&rightColage!=null)
            fakeMainView=new RectF(leftColage.getX()-slider_bar,leftColage.getY()-slider_bar,leftColage.getWidth()+rightColage.getWidth(),leftColage.getHeight());
        if(topColage!=null&&bottomColage!=null)
            fakeMainView=new RectF(topColage.getX()-slider_bar,topColage.getY()-slider_bar,topColage.getWidth(),bottomColage.getHeight()+topColage.getHeight());

    }
    private void moveTopCollage(int y){
        Colage tc = topColage;
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) tc.getLayoutParams();
        lparam.height=y-(int)fakeMainView.top;
        tc.setLayoutParams(lparam);
        tc.invalidate();
        scaleImageView(tc);
        moveTopCollageBrothers(tc);
    }
    private void moveBottomCollage(int y){
        Colage bc = bottomColage;
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) bc.getLayoutParams();
        lparam.height=(int)fakeMainView.bottom-(y-(int)fakeMainView.top);//mainView.getHeight()-y;
        lparam.topMargin=y;
        bc.setLayoutParams(lparam);
        bc.invalidate();
        scaleImageView(bc);
        moveBottomCollageBrothers(bc);
    }
    private void moveLeftCollage(int x){
        Colage lc = leftColage;
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) lc.getLayoutParams();
        lparam.width=x-(int)fakeMainView.left;
        lc.setLayoutParams(lparam);
        lc.invalidate();
        scaleImageView(lc);
        moveLeftCollageBrothers(lc);
    }
    private void moveRightCollage(int x){
        Colage rc = rightColage;
        RelativeLayout.LayoutParams lparamrc = (RelativeLayout.LayoutParams) rc.getLayoutParams();
        RelativeLayout.LayoutParams lparamlc = (RelativeLayout.LayoutParams) leftColage.getLayoutParams();
        lparamrc.width = (int)fakeMainView.right-(x-(int)fakeMainView.left);
        System.out.println("asdfjkl"+lparamrc.width);
        lparamrc.leftMargin=x;
        rc.setLayoutParams(lparamrc);
        rc.invalidate();
        scaleImageView(rc);
        moveRightCollageBrothers(rc);
    }

    private void moveLeftCollageBrothers(Colage colage){
        int[] br = colage.getWidthBrothers();
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) colage.getLayoutParams();

        for(int i=0;i<br.length;i++){
            Colage mColage = allColages[br[i]];
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mColage.getLayoutParams();
            lp.width=lparam.width;
            mColage.setLayoutParams(lp);
            mColage.invalidate();
        }
    }
    private void moveTopCollageBrothers(Colage colage){
        int[] br = colage.getHeightBrothers();
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) colage.getLayoutParams();

        for(int i=0;i<br.length;i++){

            Colage mColage = allColages[br[i]];
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mColage.getLayoutParams();
            lp.height = lparam.height;
            mColage.setLayoutParams(lp);
            mColage.invalidate();

        }
    }
    private void moveRightCollageBrothers(Colage colage){
        int[] br = colage.getWidthBrothers();
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) colage.getLayoutParams();

        for(int i=0;i<br.length;i++){
            Colage mColage = allColages[br[i]];
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mColage.getLayoutParams();
            lp.width=lparam.width;
            lp.leftMargin=lparam.leftMargin;
            mColage.setLayoutParams(lp);
            mColage.invalidate();
        }
    }
    private void moveBottomCollageBrothers(Colage colage){
        int[] br = colage.getHeightBrothers();
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) colage.getLayoutParams();

        for(int i=0;i<br.length;i++){

            Colage mColage = allColages[br[i]];
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mColage.getLayoutParams();
            lp.height = lparam.height;
            lp.topMargin = lparam.topMargin;
            mColage.setLayoutParams(lp);
            mColage.invalidate();

        }
    }
    private void scaleImageView(Colage cl){
        CardView cv = (CardView) cl.getChildAt(0);
        ImageView view = (ImageView) cv.getChildAt(0);
        int tag = (Integer) view.getTag();
        limitDrag(matrix[tag],view,tag);
        view.setImageMatrix(matrix[tag]);
        Bitmap bmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmap);
        view.draw(canvas);
    }
    private void scaleAllImageView(){
        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++) {
            Colage cl = allColages[i];
            CardView cv = (CardView) cl.getChildAt(0);
            ImageView view = (ImageView) cv.getChildAt(0);
            int tag = (Integer) view.getTag();
            limitDrag(matrix[tag], view, tag);
            view.setImageMatrix(matrix[tag]);
            if(view.getWidth()>0&&view.getHeight()>0) {
                Bitmap bmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bmap);
                view.draw(canvas);
            }
        }
    }


    public boolean onTouch(View v, MotionEvent event) {

        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        ImageView view;

        // handle touch events here
        if(v instanceof ImageView)
            view = (ImageView) v;
        else
        {
            RelativeLayout rl = (RelativeLayout)v;
            CardView cv = (CardView) rl.getChildAt(0);
            view = (ImageView) cv.getChildAt(0);
        }

        int tag = (Integer) view.getTag();
        if(v instanceof ImageView) {


            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    savedMatrix[tag].set(matrix[tag]);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    //handler.postDelayed(mLongPressed, 300);
                    //lastEvent = null;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 5f) {

                        savedMatrix[tag].set(matrix[tag]);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (isAClick(start.x, event.getX(), start.y, event.getY())) {

                        if(isSwap==true){
                            CardView cv1 = (CardView) allColages[swapFrom].getChildAt(0);
                            CardView cv2 = (CardView) allColages[(Integer) view.getTag()].getChildAt(0);
                            ImageView s1 = (ImageView) cv1.getChildAt(0);
                            ImageView s2 = (ImageView) cv2.getChildAt(0);
                            Drawable temp = s2.getDrawable();
                            s2.setImageDrawable(s1.getDrawable());
                            s1.setImageDrawable(temp);

                            Matrix sm1 = savedMatrix[swapFrom];
                            savedMatrix[swapFrom] = savedMatrix[(Integer) view.getTag()];
                            savedMatrix[(Integer) view.getTag()] = sm1;
                            Matrix m1 = matrix[swapFrom];
                            matrix[swapFrom] = matrix[(Integer) view.getTag()];
                            matrix[(Integer) view.getTag()] = m1;
                            scaleImageView(allColages[swapFrom]);
                            isSwap=false;
                            swapFrom=0;
                        }
                        else {
                            if (pop.getVisibility() == View.INVISIBLE) {
                                if(event.getRawX()<mainView.getLeft()+mVSize/2)
                                {
                                    pop.setX(event.getRawX()-40);
                                    //pop.setBackgroundResource(R.drawable.popup_left);
                                }
                                else
                                {
                                    pop.setX(event.getRawX() - getResources().getDimension(R.dimen.popupwidth)+40);
                                    //pop.setBackgroundResource(R.drawable.popup_right);
                                }

                                if(event.getRawY()<mainView.getTop()+mVSize/2)
                                {
                                    pop.setY(event.getRawY());
                                    //pop.setBackgroundResource(R.drawable.popup_bottom_left);
                                }
                                else
                                {
                                    pop.setY(event.getRawY()-getResources().getDimension(R.dimen.popupheight));
                                    //pop.setBackgroundResource(R.drawable.popup_bottom_right);
                                }
                                if(event.getRawX()<mainView.getLeft()+mVSize/2&&event.getRawY()<mainView.getTop()+mVSize/2)
                                    pop.setBackgroundResource(R.drawable.popup_left);
                                else if(event.getRawX()>mainView.getLeft()+mVSize/2&&event.getRawY()<mainView.getTop()+mVSize/2)
                                    pop.setBackgroundResource(R.drawable.popup_right);
                                else if(event.getRawX()<mainView.getLeft()+mVSize/2&&event.getRawY()>mainView.getTop()+mVSize/2)
                                    pop.setBackgroundResource(R.drawable.popup_bottom_left);
                                else if(event.getRawX()>mainView.getLeft()+mVSize/2&&event.getRawY()>mainView.getTop()+mVSize/2)
                                    pop.setBackgroundResource(R.drawable.popup_bottom_right);

                                pop.setVisibility(View.VISIBLE);
                                swapFrom = (Integer) view.getTag();
                            } else {
                                pop.setVisibility(View.INVISIBLE);
                            }
                            activateImageView=view;
                        }

                    }
                    /*handler.removeCallbacks(mLongPressed);

                    longPress=false;
                    imgViewFoundWIthPoint=false;*/
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    //lastEvent = null;
                    break;
                case MotionEvent.ACTION_MOVE:
                    /*handler.removeCallbacks(mLongPressed);
                    if(!longPress) {
                        if (mode == DRAG) {


                            float dx = event.getX() - start.x;
                            float dy = event.getY() - start.y;


                            matrix[tag].set(savedMatrix[tag]);
                            matrix[tag].postTranslate(dx, dy);


                        } else if (mode == ZOOM) {
                            float newDist = spacing(event);
                            if (newDist > 5f) {


                                matrix[tag].set(savedMatrix[tag]);
                                float scale = (newDist / oldDist);
                                matrix[tag].postScale(scale, scale, mid.x, mid.y);


                                //limitZoom(matrix[tag]);
                            }
                        }
                    }
                    else{
                        if(mode==DRAG){
                            float dx = X-(int)mainView.getX();
                            float dy = Y-(int)mainView.getY();

                            if(!imgViewFoundWIthPoint){
                                dragiv = foundImageViewWithPoints(dx,dy);
                                imgViewFoundWIthPoint=true;
                            }

                            dragiv.setX(dx);
                            dragiv.setY(dy);

                        }
                    }*/
                    if (mode == DRAG) {


                        float dx = event.getX() - start.x;
                        float dy = event.getY() - start.y;


                        matrix[tag].set(savedMatrix[tag]);
                        matrix[tag].postTranslate(dx, dy);


                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        if (newDist > 5f) {


                            matrix[tag].set(savedMatrix[tag]);
                            float scale = (newDist / oldDist);
                            matrix[tag].postScale(scale, scale, mid.x, mid.y);


                            //limitZoom(matrix[tag]);
                        }
                    }
                    break;
            }




        }
        else{
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    initialPosX=X-(int)mainView.getX();
                    initialPosY=Y-(int)mainView.getY();
                    findColagesFromPoint(initialPosX,initialPosY);
                    //_FixedDeltaX = X;
                    break;
                case MotionEvent.ACTION_MOVE:
                    moveCollages(X, Y);
                    break;
                case MotionEvent.ACTION_UP:
                    // _FixedDeltaX=X;
            }
        }
        limitDrag(matrix[tag],view,tag);
        view.setImageMatrix(matrix[tag]);
        Bitmap bmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmap);
        view.draw(canvas);

        return true;
    }
    private boolean isAClick(float startX, float endX, float startY, float endY) {
        float differenceX = Math.abs(startX - endX);
        float differenceY = Math.abs(startY - endY);
        return !(differenceX > CLICK_ACTION_THRESHOLD/* =5 */ || differenceY > CLICK_ACTION_THRESHOLD);
    }
    public void swap(View view){
        isSwap=true;
        pop.setVisibility(View.INVISIBLE);
        Toast.makeText(this, getString(R.string.selectanimagetoswap), Toast.LENGTH_SHORT).show();
    }
    public void album(View view){
        pop.setVisibility(View.INVISIBLE);
        finish();
    }
    public void edit(View view){

        // Adobe creative sdk calling . .


        Bitmap bm = ((BitmapDrawable)activateImageView.getDrawable()).getBitmap();
        File f = new File(getExternalCacheDir()+"/collagingpictempimage.png");
        try {
            FileOutputStream outStream = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) { throw new RuntimeException(e); }
        System.out.println(Uri.fromFile(f));

        //Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(Uri.fromFile(f)).build();
        //startActivityForResult(imageEditorIntent, );

        Intent dsPhotoEditorIntent = new Intent(this,DsPhotoEditorActivity.class);
        dsPhotoEditorIntent.setData(Uri.fromFile(f));
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_API_KEY, DSPhotoLab.API_KEY);
        startActivityForResult(dsPhotoEditorIntent,IMAGE_EDITOR_RESULT);

        pop.setVisibility(View.INVISIBLE);

    }
    public void setaspect(){
        //Set aspect is being called against evry button click
        //float aspectratio=1.0f;
        float aspectratio = AspectData.getSharedInstance().getAspectValue();
        System.out.println("Received aspect value : " + aspectratio);


        RelativeLayout.LayoutParams mainParam = (RelativeLayout.LayoutParams) mainView.getLayoutParams();
        mainParam.width=mVSize;
        mainParam.height=(int)(mVSize*aspectratio);
        mainView.setLayoutParams(mainParam);
        for(int i=0;i<NO_OF_COLLAGE_FRAMES;i++){
            Colage colage = allColages[i];
            /*RelativeLayout.LayoutParams colparam = (RelativeLayout.LayoutParams) colage.getLayoutParams();
            colparam.height=(int)(mVSize*aspectratio);
            colparam.topMargin=(int)(colparam.topMargin*aspectratio);*/

            LayerFrame layertemp = new LayerFrame(layerid);
            ArrayList<RectF> layerSizeTemp = layertemp.getSize();

            //System.out.println("width"+mainParam.width);
            //System.out.println("top"+layerSizeTemp.get(i).top);

            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    (int)(mainParam.width*layerSizeTemp.get(i).right),(int)(mainParam.height*layerSizeTemp.get(i).bottom));
            rlp.topMargin = (int)(mainParam.height*layerSizeTemp.get(i).top);
            rlp.leftMargin = (int)(mainParam.width*layerSizeTemp.get(i).left);
            colage.setLayoutParams(rlp);

        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                scaleAllImageView();
            }
        }, 100);
    }

    public int PxToDp(Context context, int px) {
        return (int)(px / context.getResources().getDisplayMetrics().density);
    }
    public int DpToPx(int dp) {
        return (int)(dp * this.getResources().getDisplayMetrics().density);
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        float s=x * x + y * y;
        return (float)Math.sqrt(s);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * Calculate the degree to be rotated by.
     *
     * @param event
     * @return Degrees
     */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }
    private void limitDrag(Matrix m,ImageView iv,int tag) {
        float[] values = new float[9];
        m.getValues(values);
        float transX = values[Matrix.MTRANS_X];
        float transY = values[Matrix.MTRANS_Y];
        float scaleX = values[Matrix.MSCALE_X];
        float scaleY = values[Matrix.MSCALE_Y];



        Rect bounds = iv.getDrawable().getBounds();
        float width = bounds.right - bounds.left;
        float height = bounds.bottom - bounds.top;

        if((scaleX*width < allColages[tag].getWidth())) {
            scaleX = (allColages[tag].getWidth() / width);
            scaleY = (allColages[tag].getWidth() / width);
            //transY=0.0f;
        }
        else if((scaleX*height < allColages[tag].getHeight())){
            scaleX = (allColages[tag].getHeight() / height);
            scaleY = (allColages[tag].getHeight() / height);
        }
        System.out.println("scale"+scaleX);
        System.out.println("width"+allColages[tag].getWidth());


        if(transX >= 0.0f) {
            transX = 0.0f;
        } else if(transX+(width*scaleX) < iv.getWidth()) {
            transX = -(width*scaleX)+iv.getWidth();
        }

        if(transY >= 0.0f) {
            transY = 0.0f;
        } else if(transY+(height*scaleY) < iv.getHeight()) {
            transY = -(height*scaleY)+iv.getHeight();
        }


        values[Matrix.MTRANS_X] = transX;
        values[Matrix.MTRANS_Y] = transY;
        values[Matrix.MSCALE_X] = scaleX;
        values[Matrix.MSCALE_Y] = scaleY;
        m.setValues(values);

    }

    public void frameAct(View view){

        finish();
    }
    public void styleAct(View view){
        if(pop.getVisibility()==View.VISIBLE)
            pop.setVisibility(View.INVISIBLE);

        Intent style = new Intent(SkeletonActivity.this,PopUpActivity.class);
        startActivity(style);
    }
    public void aspectAct(View view){

        if(pop.getVisibility()==View.VISIBLE)
            pop.setVisibility(View.INVISIBLE);
        Intent aspect = new Intent(SkeletonActivity.this,aspectActivity.class);
        startActivity(aspect);


    }
    public void helpScreen(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                helpScreenafter1s();
            }
        }, 1000);

    }
    public void helpScreenafter1s(){
        /*Intent help = new Intent(SkeletonActivity.this,HelpActivity.class);
        startActivity(help);*/
    }
    public void shareAct(View view){
        if(pop.getVisibility()==View.VISIBLE)
            pop.setVisibility(View.INVISIBLE);

        // If the input image uri for DS Photo Editor is "inputImageUri", launch the editor UI

        // using the following code
        /*
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pp2);
        File f = new File(getExternalCacheDir()+"/collagingpictempimage.png");
        try {
            FileOutputStream outStream = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) { throw new RuntimeException(e); }
        Intent dsPhotoEditorIntent = new Intent(this,DsPhotoEditorActivity.class);
        dsPhotoEditorIntent.setData(Uri.fromFile(f));
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_API_KEY, DSPhotoLab.API_KEY);
        startActivityForResult(dsPhotoEditorIntent,IMAGE_EDITOR_RESULT); */

       // Set actions at the listener below . . .
        mainView.setDrawingCacheEnabled(true);
        Bitmap result = mainView.getDrawingCache();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.PNG, 100, bs);
        byte[] byteArray = bs.toByteArray();
        Intent send = new Intent(SkeletonActivity.this, FilterActivity.class);
        send.putExtra("collageBitmap",byteArray);
        startActivity(send);
        mainView.setDrawingCacheEnabled(false);




        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_RQST_CODE);
        }
        else {


            ActionSheet.createBuilder(this, getSupportFragmentManager())
                    .setCancelButtonTitle(getString(R.string.Cancel))
                    .setOtherButtonTitles(getString(R.string.Share), getString(R.string.SaveToAlbum))
                    .setCancelableOnTouchOutside(true)
                    .setListener(SkeletonActivity.this).show();
        }*/


        /*
        // Adobe creative sdk calling . .
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.pp2);
        File f = new File(getExternalCacheDir()+"/collagingpictempimage.png");
        try {
            FileOutputStream outStream = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) { throw new RuntimeException(e); }
        System.out.println(Uri.fromFile(f));
        Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(Uri.fromFile(f)).build();
        startActivityForResult(imageEditorIntent, 1);
        finish(); // Comment this out to receive edited image
        */


    }
    public void settingAct(View view){
        if(pop.getVisibility()==View.VISIBLE)
            pop.setVisibility(View.INVISIBLE);
        Intent setting = new Intent(SkeletonActivity.this,SettingsActivity.class);
        startActivity(setting);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE_EDITOR_RESULT: /*adobe
                    Uri editedImageUri = data.getParcelableExtra(AdobeImageIntent.EXTRA_OUTPUT_URI);
                    Log.d(TAG, "editedImageUri: " + editedImageUri.toString());
                    Bundle extra = data.getExtras();
                    if (extra != null) {
                        boolean changed = extra.getBoolean(AdobeImageIntent.EXTRA_OUT_BITMAP_CHANGED);
                        Log.d(TAG, "Image edited: " + changed);
                        if (changed) {
                            try {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), editedImageUri);
                                activateImageView.setImageBitmap(bitmap);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    break;*/
                    Uri editedImageUri = data.getData();
                    try{
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), editedImageUri);
                        activateImageView.setImageBitmap(bitmap);
                    }catch (IOException ioe){
                        ioe.printStackTrace();
                    }

                    break;
            }
        }

    }
    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

            Log.i("RotateImage", "Exif orientation: " + orientation);
            Log.i("RotateImage", "Rotate value: " + rotate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }


}

