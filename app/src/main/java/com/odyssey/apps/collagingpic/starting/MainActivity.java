package com.odyssey.apps.collagingpic.starting;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import com.odyssey.apps.Admobs.Advertisement;
import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.odyssey.apps.collagingpic.BuildConfig;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.collagingpic.skeleton.SkeletonActivity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {


    // Fire base Analytics
    //private FirebaseAnalytics mFirebaseAnalytics;


    public static ArrayList<Model_images> al_images = new ArrayList<Model_images>();
    boolean boolean_folder;
    ListViewAdapter obj_adapter;
    ListView lv_folder;
    GridView gv_folder;
    Button galleryButton;
    Button cameraButton;
    ImageView imageView;
    LinearLayout imageLinearLayout;
    HorizontalScrollView horizontalScrollView;
    Button cancelButton;
    Button button5;
    private static final int REQUEST_PERMISSIONS = 100;

    Boolean a = false;

    GridViewAdapter gridViewAdapter;

    private static final int REQUEST_TAKE_PHOTO = 1;;
    public static int count = 0;
    private String mCurrentPhotoPath;

    public static ArrayList<String> allImage = new ArrayList<String>();
    public static ArrayList<String> selection = new ArrayList<String>();
     ArrayList<Bitmap> bitmapArrayList = new ArrayList<Bitmap>();

    File photoFile = null;

    public static int listViewCellNumber;
    String selectedPath;
    int frameNumber;
    int j;
    int[] layout;
    Bitmap myBitmap;

//    private FirebaseAnalytics mFirebaseAnalytics;

    private int[] frameNumberArray = {6,7,5,10,9,6,3,12,3,2,2,2,2,2,2,2};
    private int[][] layoutArray = {{1000,0,1,3,6,15},{1000,3,4,5,6,7,8},{1000,2,11,15,16},{1000,1,9,10,12,13,14,17,19,20},{1000,18,24,25,31,38,39,43,46},{1000,23,32,33,41,42},
            {1000,37,40},{1000,21,22,26,27,34,35,36,44,45,47,48},{1000,29,30},{1000,50},{1000,51},{1000,52},{1000,49},{1000,53},{1000,54},{1000,28}};


    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            purchasedJustNow();
            System.out.println("Notified !");
        }
    };

    private void purchasedJustNow(){
        //collectionView.invalidate();
        if(CheckIf.isPurchased(IAPData.getSharedInstance().ADMOB,this)) {
            findViewById(R.id.AMAdmob).setVisibility(View.GONE);
        }
    }




    @Override
    public void onDestroy() {

        /*if(mInterstitialAd.isLoaded() && !CheckIf.isPurchased("admob",EditTextActivity.this)){
            mInterstitialAd.show();
        }*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        super.onDestroy();

    }




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain the FirebaseAnalytics instance.
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        //Notifications
        NotificationCenter.addReceiver(NotiData.getSharedInstance().SOMETHING_JUST_PURCHASED,mMessageReceiver,this);



        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i2 = new Intent(MainActivity.this, AdScreenActivity.class);
                startActivity(i2);
                overridePendingTransition( R.anim.go_up, R.anim.go_down );
            }
        }, 1000);




        lv_folder = (ListView) findViewById(R.id.listView);
        gv_folder = (GridView) findViewById(R.id.gridView);
        galleryButton = (Button) findViewById(R.id.button2);
        imageView = (ImageView) findViewById(R.id.imageView);
        cameraButton = (Button) findViewById(R.id.button);
        imageLinearLayout = (LinearLayout) findViewById(R.id.imageLinearLayout);
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalScrollView);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        button5 = (Button) findViewById(R.id.button5);



        horizontalScrollView.setVisibility(View.INVISIBLE);
        cancelButton.setVisibility(View.INVISIBLE);
        button5.setVisibility(View.INVISIBLE);

        cancelButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                horizontalScrollView.setVisibility(View.INVISIBLE);
                cancelButton.setVisibility(View.INVISIBLE);
                button5.setVisibility(View.INVISIBLE);
                selection.clear();
                bitmapArrayList.clear();
                imageView.setVisibility(View.VISIBLE);
                gridViewAdapter.notifyDataSetChanged();
            }

        });


        galleryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Perform action on click
                if (a == false) {
                    lv_folder.setVisibility(View.VISIBLE);

                    obj_adapter = new ListViewAdapter(getApplicationContext(), al_images);
                    lv_folder.setAdapter(obj_adapter);
                    a = true;
                }
                else {
                    lv_folder.setVisibility(View.INVISIBLE);
                    a = false;
                }
            }
        });


        cameraButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            try{
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go

                    try {
                        photoFile = createImageFile();
                        System.out.println("------------------------------>>>>>>"+photoFile);
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        return;
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(MainActivity.this,

                                BuildConfig.APPLICATION_ID + ".provider",
                                createImageFile());
                        System.out.println("------------------------------"+photoURI);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }
            } catch (IOException e) {
            }


            }
        });



        lv_folder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listViewCellNumber=i;

//                GridView gridView = (GridView)findViewById(R.id.gridView);
                gridViewAdapter = new GridViewAdapter(MainActivity.this,MainActivity.al_images,i);
                gv_folder.setAdapter(gridViewAdapter);

                if (i==0) {
                    galleryButton.setText(getString(R.string.CameraRoll));
                }
                else{
                    galleryButton.setText(al_images.get(i-1).getStr_folder());
                }
                lv_folder.setVisibility(View.INVISIBLE);
                a = false;

            }
        });




        gv_folder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    if (listViewCellNumber == 0) {
                        selectedPath = allImage.get(i);
                        Bitmap bitmap = BitmapFactory.decodeFile(selectedPath);
                        myBitmap = resize(bitmap,300,300);
                    } else {
                        selectedPath = al_images.get(listViewCellNumber - 1).getAl_imagepath().get(i);
                        Bitmap bitmap = BitmapFactory.decodeFile(selectedPath);
                        myBitmap = resize(bitmap,300,300);
                    }


                    if (selection.contains(selectedPath)) {

                        bitmapArrayList.remove(selection.indexOf(selectedPath));
                        selection.remove(selectedPath);
                        gridViewAdapter.notifyDataSetChanged();
//                        if (selection.contains(myBitmap)) {
//                            bitmapArrayList.remove(myBitmap);
                            System.out.println("bitmapArrayList.size()====="+bitmapArrayList.size());
                        System.out.println("selection.size()====="+selection.size());
//                        }


                        if (selection.size() > 0 ) {
                            if (selection.size() <= 16 ){

                                imageView.setVisibility(View.INVISIBLE);

                                horizontalScrollView.setVisibility(View.VISIBLE);
                                cancelButton.setVisibility(View.VISIBLE);
                                button5.setVisibility(View.VISIBLE);

                                imageLinearLayout.removeAllViewsInLayout();


                                int height = imageLinearLayout.getHeight();
                                int width = height;
                                LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
                                parms.setMargins(10, 0, 10, 0);

                                if (selection.size() == 1) {
                                    frameNumber = frameNumberArray[0];
                                    layout = layoutArray[0];
                                } else if (selection.size() == 2) {
                                    frameNumber = frameNumberArray[1];
                                    layout = layoutArray[1];
                                } else if (selection.size() == 3) {
                                    frameNumber = frameNumberArray[2];
                                    layout = layoutArray[2];
                                } else if (selection.size() == 4) {
                                    frameNumber = frameNumberArray[3];
                                    layout = layoutArray[3];
                                } else if (selection.size() == 5) {
                                    frameNumber = frameNumberArray[4];
                                    layout = layoutArray[4];
                                } else if (selection.size() == 6) {
                                    frameNumber = frameNumberArray[5];
                                    layout = layoutArray[5];
                                } else if (selection.size() == 7) {
                                    frameNumber = frameNumberArray[6];
                                    layout = layoutArray[6];
                                } else if (selection.size() == 8) {
                                    frameNumber = frameNumberArray[7];
                                    layout = layoutArray[7];
                                } else if (selection.size() == 9) {
                                    frameNumber = frameNumberArray[8];
                                    layout = layoutArray[8];
                                } else if (selection.size() == 10) {
                                    frameNumber = frameNumberArray[9];
                                    layout = layoutArray[9];
                                } else if (selection.size() == 11) {
                                    frameNumber = frameNumberArray[10];
                                    layout = layoutArray[10];
                                } else if (selection.size() == 12) {
                                    frameNumber = frameNumberArray[11];
                                    layout = layoutArray[11];
                                } else if (selection.size() == 13) {
                                    frameNumber = frameNumberArray[12];
                                    layout = layoutArray[12];
                                } else if (selection.size() == 14) {
                                    frameNumber = frameNumberArray[13];
                                    layout = layoutArray[13];
                                } else if (selection.size() == 15) {
                                    frameNumber = frameNumberArray[14];
                                    layout = layoutArray[14];
                                } else if (selection.size() == 16) {
                                    frameNumber = frameNumberArray[15];
                                    layout = layoutArray[15];
                                }


//                    Uri uri = Uri.parse("file://" + selectedPath);

                                for ( j = 0; j < frameNumber; j++) {

//                        ImageView iv = new ImageView(MainActivity.this);
//                        iv.setImageBitmap(resizedBitmap);
//                        iv.setLayoutParams(parms);
//                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);


                                    RelativeLayout rl = new RelativeLayout(MainActivity.this);
                                    rl.setBackgroundColor(Color.RED);
                                    rl.setLayoutParams(parms);

                                    imageLinearLayout.addView(rl);


                                    ImageView onClickImageView = new ImageView(MainActivity.this);
                                    onClickImageView.setBackgroundColor(Color.TRANSPARENT);
                                    onClickImageView.setLayoutParams(parms);
                                    onClickImageView.setTag(Integer.valueOf(j));
                                    rl.addView(onClickImageView);


                                    Bitmap resizedBitmap;
                                    LayerFrame layer = new LayerFrame(layout[j]);
                                    ArrayList<RectF> a = layer.getSize();



                                    onClickImageView.setOnClickListener(new View.OnClickListener() {

                                        public void onClick(View v) {
                                            System.out.println("j====="+j);
                                            System.out.println("frameNumber====="+frameNumber);
                                            System.out.println("Layout====="+layout[(Integer) v.getTag()]);

                                            if(layout[(Integer) v.getTag()]==1000){

                                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                                                intent.putExtra("EXTRA_SESSION_ID", selection);
                                                startActivity ( intent );

                                            }
                                            else{
                                                Intent skeleton= new Intent(MainActivity.this, SkeletonActivity.class);
                                                skeleton.putExtra("layerId", layout[(Integer) v.getTag()]);
                                                startActivity(skeleton);
                                            }

                                        }
                                    });



                                    for (int indx = 0; indx < a.size(); indx++) {
                                        RectF rect = a.get(indx);
                                        float x = rect.left;
                                        float y = rect.top;
                                        float w = rect.right;
                                        float h = rect.bottom;

                                        if (selection.size() == 1) {
                                            if (j == 0) {
                                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                                        R.drawable.s5);
                                                resizedBitmap = resize(bitmap, 300, 300);
                                            } else {
//                                                Bitmap bitmap = BitmapFactory.decodeFile(selection.get(0));
//                                                resizedBitmap = resize(bitmap, 300, 300);
                                                resizedBitmap = bitmapArrayList.get(0);
                                            }
                                        } else {
                                            if (j == 0) {
                                                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                                        R.drawable.s5);
                                                resizedBitmap = resize(bitmap, 300, 300);
                                            } else {
//                                                Bitmap bitmap = BitmapFactory.decodeFile(selection.get(indx));
//                                                resizedBitmap = resize(bitmap, 300, 300);
                                                resizedBitmap = bitmapArrayList.get(indx);
                                            }
                                        }

                                        float x2 = imageLinearLayout.getHeight() * x;
                                        float y2 = imageLinearLayout.getHeight() * y;
                                        float height2 = imageLinearLayout.getHeight() * h;
                                        float width2 = imageLinearLayout.getHeight() * w;
                                        RelativeLayout.LayoutParams parms2 = new RelativeLayout.LayoutParams(Math.round(width2), Math.round(height2));
                                        parms2.leftMargin = Math.round(x2);
                                        parms2.topMargin = Math.round(y2);
                                        ImageView iv = new ImageView(MainActivity.this);
                                        iv.setBackgroundColor(Color.GREEN);
                                        iv.setImageBitmap(resizedBitmap);
                                        iv.setLayoutParams(parms2);
                                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                        rl.addView(iv);

                                    }


                                }

                            }

                        } else {
//                    imageView.setImageResource(R.drawable.collage_frame);
                            imageView.setVisibility(View.VISIBLE);
                            horizontalScrollView.setVisibility(View.INVISIBLE);
                            cancelButton.setVisibility(View.INVISIBLE);
                            button5.setVisibility(View.INVISIBLE);
                        }



                    } else {
                        if (selection.size()< 16) {
                            selection.add(selectedPath);
                            gridViewAdapter.notifyDataSetChanged();
                            bitmapArrayList.add(myBitmap);
                            System.out.println("selection.size()====="+selection.size());


                            if (selection.size() > 0 ) {
                                if (selection.size() <= 16 ){

                                    imageView.setVisibility(View.INVISIBLE);

                                    horizontalScrollView.setVisibility(View.VISIBLE);
                                    cancelButton.setVisibility(View.VISIBLE);
                                    button5.setVisibility(View.VISIBLE);

                                    imageLinearLayout.removeAllViewsInLayout();


                                    int height = imageLinearLayout.getHeight();
                                    int width = height;
                                    LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width, height);
                                    parms.setMargins(10, 0, 10, 0);

                                    if (selection.size() == 1) {
                                        frameNumber = frameNumberArray[0];
                                        layout = layoutArray[0];
                                    } else if (selection.size() == 2) {
                                        frameNumber = frameNumberArray[1];
                                        layout = layoutArray[1];
                                    } else if (selection.size() == 3) {
                                        frameNumber = frameNumberArray[2];
                                        layout = layoutArray[2];
                                    } else if (selection.size() == 4) {
                                        frameNumber = frameNumberArray[3];
                                        layout = layoutArray[3];
                                    } else if (selection.size() == 5) {
                                        frameNumber = frameNumberArray[4];
                                        layout = layoutArray[4];
                                    } else if (selection.size() == 6) {
                                        frameNumber = frameNumberArray[5];
                                        layout = layoutArray[5];
                                    } else if (selection.size() == 7) {
                                        frameNumber = frameNumberArray[6];
                                        layout = layoutArray[6];
                                    } else if (selection.size() == 8) {
                                        frameNumber = frameNumberArray[7];
                                        layout = layoutArray[7];
                                    } else if (selection.size() == 9) {
                                        frameNumber = frameNumberArray[8];
                                        layout = layoutArray[8];
                                    } else if (selection.size() == 10) {
                                        frameNumber = frameNumberArray[9];
                                        layout = layoutArray[9];
                                    } else if (selection.size() == 11) {
                                        frameNumber = frameNumberArray[10];
                                        layout = layoutArray[10];
                                    } else if (selection.size() == 12) {
                                        frameNumber = frameNumberArray[11];
                                        layout = layoutArray[11];
                                    } else if (selection.size() == 13) {
                                        frameNumber = frameNumberArray[12];
                                        layout = layoutArray[12];
                                    } else if (selection.size() == 14) {
                                        frameNumber = frameNumberArray[13];
                                        layout = layoutArray[13];
                                    } else if (selection.size() == 15) {
                                        frameNumber = frameNumberArray[14];
                                        layout = layoutArray[14];
                                    } else if (selection.size() == 16) {
                                        frameNumber = frameNumberArray[15];
                                        layout = layoutArray[15];
                                    }


//                    Uri uri = Uri.parse("file://" + selectedPath);


                                    for ( j = 0; j < frameNumber; j++) {

//                        ImageView iv = new ImageView(MainActivity.this);
//                        iv.setImageBitmap(resizedBitmap);
//                        iv.setLayoutParams(parms);
//                        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);


                                        RelativeLayout rl = new RelativeLayout(MainActivity.this);
                                        rl.setBackgroundColor(Color.RED);
                                        rl.setLayoutParams(parms);

                                        imageLinearLayout.addView(rl);


                                        ImageView onClickImageView = new ImageView(MainActivity.this);
                                        onClickImageView.setBackgroundColor(Color.TRANSPARENT);
                                        onClickImageView.setLayoutParams(parms);
                                        onClickImageView.setTag(Integer.valueOf(j));
                                        rl.addView(onClickImageView);


                                        Bitmap resizedBitmap;
                                        LayerFrame layer = new LayerFrame(layout[j]);
                                        ArrayList<RectF> a = layer.getSize();



                                        onClickImageView.setOnClickListener(new View.OnClickListener() {

                                            public void onClick(View v) {
                                                System.out.println("j====="+j);
                                                System.out.println("frameNumber====="+frameNumber);
                                                System.out.println("Layout====="+layout[(Integer) v.getTag()]);

                                                if(layout[(Integer) v.getTag()]==1000){

                                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                                    startActivity ( intent );

                                                }
                                                else{
                                                    Intent skeleton= new Intent(MainActivity.this, SkeletonActivity.class);
                                                    skeleton.putExtra("layerId", layout[(Integer) v.getTag()]);
                                                    startActivity(skeleton);
                                                }

                                            }
                                        });


                                        for (int indx = 0; indx < a.size(); indx++) {
                                            RectF rect = a.get(indx);
                                            float x = rect.left;
                                            float y = rect.top;
                                            float w = rect.right;
                                            float h = rect.bottom;


                                            if (selection.size() == 1) {
                                                if (j == 0) {
                                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                                            R.drawable.s5);
                                                    resizedBitmap = resize(bitmap, 300, 300);
                                                } else {
//                                                    Bitmap bitmap = BitmapFactory.decodeFile(selection.get(0));
//                                                    resizedBitmap = resize(bitmap, 300, 300);
                                                    resizedBitmap = bitmapArrayList.get(0);
                                                }
                                            } else {
                                                if (j == 0) {
                                                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                                                            R.drawable.s5);
                                                    resizedBitmap = resize(bitmap, 300, 300);
                                                } else {
//                                                    Bitmap bitmap = BitmapFactory.decodeFile(selection.get(indx));
//                                                    resizedBitmap = resize(bitmap, 300, 300);
                                                    resizedBitmap = bitmapArrayList.get(indx);
                                                }
                                            }


                                            float x2 = imageLinearLayout.getHeight() * x;
                                            float y2 = imageLinearLayout.getHeight() * y;
                                            float height2 = imageLinearLayout.getHeight() * h;
                                            float width2 = imageLinearLayout.getHeight() * w;
                                            RelativeLayout.LayoutParams parms2 = new RelativeLayout.LayoutParams(Math.round(width2), Math.round(height2));
                                            parms2.leftMargin = Math.round(x2);
                                            parms2.topMargin = Math.round(y2);
                                            ImageView iv = new ImageView(MainActivity.this);
                                            iv.setBackgroundColor(Color.GREEN);
                                            iv.setImageBitmap(resizedBitmap);
                                            iv.setLayoutParams(parms2);
                                            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);


                                            rl.addView(iv);

                                        }


                                    }

                                }

                            } else {
//                    imageView.setImageResource(R.drawable.collage_frame);
                                imageView.setVisibility(View.VISIBLE);
                                horizontalScrollView.setVisibility(View.INVISIBLE);
                                cancelButton.setVisibility(View.INVISIBLE);
                                button5.setVisibility(View.INVISIBLE);
                            }


                        }

                    }





            }
        });



        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) || (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)) {

            cameraButton.setEnabled(false);

            /*if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {*/
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            //}
        }else {
            Log.e("Else","Else");

            fn_imagespath();
            gridViewAdapter = new GridViewAdapter(MainActivity.this,MainActivity.al_images,0);
            gv_folder.setAdapter(gridViewAdapter);
            lv_folder.setVisibility(View.INVISIBLE);
            horizontalScrollView.setVisibility(View.INVISIBLE);
            cancelButton.setVisibility(View.INVISIBLE);
            button5.setVisibility(View.INVISIBLE);
            a = false;
            listViewCellNumber = 0;
        }






        // Admob
        MobileAds.initialize(this, Advertisement.getSharedInstance().getNativeAdvanceAdAppID());
        final AdLoader adLoader = new AdLoader.Builder(this, Advertisement.getSharedInstance().getNativeAdvanceAdUnitID())
                .forAppInstallAd(new NativeAppInstallAd.OnAppInstallAdLoadedListener() {
                    @Override
                    public void onAppInstallAdLoaded(NativeAppInstallAd appInstallAd) {
                        // Show the app install ad.
                        //Toast.makeText(MainActivity.this, "Ad App Install loading", Toast.LENGTH_SHORT).show();;
                        FrameLayout frameLayout  = (FrameLayout)findViewById(R.id.AMAdmob);
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
                        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.AMAdmob);
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
            findViewById(R.id.AMAdmob).setVisibility(View.GONE);
        }



    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            // Show the thumbnail on ImageView
            Uri imageUri = Uri.parse(mCurrentPhotoPath);
            File file = new File(imageUri.getPath());
            try {
                InputStream ims = new FileInputStream(file);
                allImage.add(0,file.getPath());
                gridViewAdapter.notifyDataSetChanged();
                gridViewAdapter = new GridViewAdapter(MainActivity.this,MainActivity.al_images,0);
                gv_folder.setAdapter(gridViewAdapter);


            } catch (FileNotFoundException e) {
                return;
            }

            // ScanFile so it will be appeared on Gallery
            MediaScannerConnection.scanFile(MainActivity.this,
                    new String[]{imageUri.getPath()}, null,
                    new MediaScannerConnection.OnScanCompletedListener() {
                        public void onScanCompleted(String path, Uri uri) {
                            System.out.println("path--->"+path);
                            System.out.println("uri----->"+uri);

                        }
                    });



        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }





//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == TAKE_PHOTO_CODE && resultCode == RESULT_OK) {
//            Log.d("CameraDemo", "Pic saved");
//        }
//    }


    public ArrayList<Model_images> fn_imagespath() {
        al_images.clear();
        allImage.clear();

        int int_position = 0;
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

        Log.e("projection", String.valueOf(projection));

        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;
        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            Log.e("Column", absolutePathOfImage);
            Log.e("Folder", cursor.getString(column_index_folder_name));

            for (int i = 0; i < al_images.size(); i++) {
                if (al_images.get(i).getStr_folder().equals(cursor.getString(column_index_folder_name))) {
                    boolean_folder = true;
                    int_position = i;
                    break;
                } else {
                    boolean_folder = false;
                }
            }


            if (boolean_folder) {

                ArrayList<String> al_path = new ArrayList<>();
                al_path.addAll(al_images.get(int_position).getAl_imagepath());
                al_path.add(absolutePathOfImage);
                al_images.get(int_position).setAl_imagepath(al_path);

            } else {
                ArrayList<String> al_path = new ArrayList<>();
                al_path.add(absolutePathOfImage);
                Model_images obj_model = new Model_images();
                obj_model.setStr_folder(cursor.getString(column_index_folder_name));
                obj_model.setAl_imagepath(al_path);

                al_images.add(obj_model);



            }


        }

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        for (int i = 0; i < al_images.size(); i++) {
            Log.e("FOLDER", al_images.get(i).getStr_folder());
            for (int j = 0; j < al_images.get(i).getAl_imagepath().size(); j++) {
                Log.e("FILE", al_images.get(i).getAl_imagepath().get(j));
                allImage.add(al_images.get(i).getAl_imagepath().get(j)) ;

//                File file = new File (al_images.get(i).getAl_imagepath().get(j));
//                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(),bmOptions);
//                allBitmap.add(bitmap);

            }
        }

//        obj_adapter = new ListViewAdapter(getApplicationContext(),al_images);
//        lv_folder.setAdapter(obj_adapter);
        if(al_images.size() != 0) {
            galleryButton.setText(getString(R.string.CameraRoll));
        }
        return al_images;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
//                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                        cameraButton.setEnabled(true);
                        fn_imagespath();
                        gridViewAdapter = new GridViewAdapter(MainActivity.this,MainActivity.al_images,0);
                        gv_folder.setAdapter(gridViewAdapter);
                        lv_folder.setVisibility(View.INVISIBLE);
                        a = false;
                        horizontalScrollView.setVisibility(View.INVISIBLE);
                        cancelButton.setVisibility(View.INVISIBLE);
                        button5.setVisibility(View.INVISIBLE);
                        listViewCellNumber = 0;
                    }else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                        //Toast.makeText(MainActivity.this, "Go to Settings and Grant the permission to use this feature.", Toast.LENGTH_SHORT).show();
                        // User selected the Never Ask Again Option
                        goSettingsPage();
                    } else {
                        //Toast.makeText(MainActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
//
            }
        }
    }

    public void goSettingsPage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage(getString(R.string.GoSettingsMsg));
        builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application
                dialog.cancel();
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

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
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onPause()
    {
        super.onPause();
    }

}
