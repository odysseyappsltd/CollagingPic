package com.odyssey.apps.collagingpic.starting;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.baoyz.actionsheet.ActionSheet;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mukesh.image_processing.ImageProcessor;
import com.odyssey.apps.Admobs.AdmobClass;
import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.collagingpic.R;

import com.odyssey.apps.util.Custom;
import com.odyssey.apps.util.FileUtil;

import net.alhazmy13.imagefilter.ImageFilter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilterActivity extends AppCompatActivity implements ActionSheet.ActionSheetListener {

    public ImageView imageView;
    public ImageView imageView2;
    public ImageView imageView3;
    public ImageView imageView4;
    public ImageView imageView5;
    public ImageView imageView6;
    public ImageView imageView7;
    public ImageView imageView8;
    public ImageView imageView9;
    public ImageView imageView10;
    public ImageView imageView11;
    public ImageView imageView12;
    public ImageView imageView13;
    public ImageView imageView14;
    public ImageView imageView15;
    public ImageView imageView16;
    public ImageView imageView17;
    public ImageView imageView18;

    public Button doneButton;
    public Button cancelButton;
    private InterstitialAd mInterstitialAd;
    public static final int PERM_RQST_CODE = 110;

    Bitmap bitmap;
//    Bitmap resized;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_filter);
        imageView = (ImageView)  findViewById(R.id.imageView);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView10 = (ImageView) findViewById(R.id.imageView10);
        imageView11 = (ImageView) findViewById(R.id.imageView11);
        imageView12 = (ImageView) findViewById(R.id.imageView12);
        imageView13 = (ImageView) findViewById(R.id.imageView13);
        imageView14 = (ImageView) findViewById(R.id.imageView14);
        imageView15 = (ImageView) findViewById(R.id.imageView15);
        imageView16 = (ImageView) findViewById(R.id.imageView16);
        imageView17 = (ImageView) findViewById(R.id.imageView17);
        imageView18 = (ImageView) findViewById(R.id.imageView18);
        doneButton = (Button)  findViewById(R.id.buttonDone);
        cancelButton = (Button) findViewById(R.id.buttonCancel);

//        Bundle extras = getIntent().getExtras();
//        byte[] byteArray = extras.getByteArray("collageBitmap");
//
//        bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        bitmap = DataPassingSingelton.getInstance().getImage();
        imageView.setImageBitmap(bitmap);
//        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
        Bitmap resized = resize(bitmap,100,100);
        System.out.println("resized image height"+(resized.getHeight()));
        System.out.println("resized image width"+(resized.getWidth()));



        Bitmap alhazmy_INVERT = ImageFilter.applyFilter(resized, ImageFilter.Filter.INVERT);

        ImageProcessor imageProcessor = new ImageProcessor();
        Bitmap applyBlackFilter = imageProcessor.applyBlackFilter(resized);

        Bitmap alhazmy_GRAY = ImageFilter.applyFilter(resized, ImageFilter.Filter.GRAY);

        Bitmap applySnowEffect = imageProcessor.applySnowEffect(resized);

        Bitmap alhazmy_OLD = ImageFilter.applyFilter(resized, ImageFilter.Filter.OLD);

        Bitmap applyReflection = imageProcessor.applyReflection(resized);

        Bitmap alhazmy_BLOCK = ImageFilter.applyFilter(resized, ImageFilter.Filter.BLOCK);

        Bitmap alhazmy_NEON = ImageFilter.applyFilter(resized, ImageFilter.Filter.NEON,255,0,0);

        Bitmap alhazmy_SKETCH = ImageFilter.applyFilter(resized, ImageFilter.Filter.SKETCH);

        Bitmap alhazmy_GOTHAM = ImageFilter.applyFilter(resized, ImageFilter.Filter.GOTHAM);

        Bitmap alhazmy_HDR = ImageFilter.applyFilter(resized, ImageFilter.Filter.HDR);

        Bitmap alhazmy_LOMO = ImageFilter.applyFilter(resized, ImageFilter.Filter.LOMO);

        Bitmap alhazmy_TV = ImageFilter.applyFilter(resized, ImageFilter.Filter.TV);

        Bitmap alhazmy_RELIEF = ImageFilter.applyFilter(resized, ImageFilter.Filter.RELIEF);

        Bitmap alhazmy_LIGHT = ImageFilter.applyFilter(resized, ImageFilter.Filter.LIGHT);

        Bitmap alhazmy_SOFT_GLOW = ImageFilter.applyFilter(resized, ImageFilter.Filter.SOFT_GLOW);

        imageView2.setImageBitmap(alhazmy_INVERT);
        imageView3.setImageBitmap(applyBlackFilter);
        imageView4.setImageBitmap(alhazmy_GRAY);
        imageView5.setImageBitmap(applySnowEffect);
        imageView6.setImageBitmap(alhazmy_OLD);
        imageView7.setImageBitmap(applyReflection);
        imageView8.setImageBitmap(alhazmy_BLOCK);
        imageView9.setImageBitmap(alhazmy_NEON);
        imageView10.setImageBitmap(alhazmy_SKETCH);
        imageView11.setImageBitmap(alhazmy_GOTHAM);
        imageView12.setImageBitmap(alhazmy_HDR);
        imageView13.setImageBitmap(alhazmy_LOMO);
        imageView14.setImageBitmap(alhazmy_TV);
        imageView15.setImageBitmap(alhazmy_RELIEF);
        imageView16.setImageBitmap(alhazmy_LIGHT);
        imageView17.setImageBitmap(alhazmy_SOFT_GLOW);
        imageView18.setImageBitmap(resized);



        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Bitmap imageviewImageBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                if (ActivityCompat.checkSelfPermission(FilterActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(FilterActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(FilterActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERM_RQST_CODE);
                }
                else {


                    ActionSheet.createBuilder(FilterActivity.this, getSupportFragmentManager())
                            .setCancelButtonTitle(getString(R.string.Cancel))
                            .setOtherButtonTitles(getString(R.string.Share), getString(R.string.SaveToAlbum))
                            .setCancelableOnTouchOutside(true)
                            .setListener(FilterActivity.this).show();
                }

            }
        });


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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


    }
    // Listeners for ACtion Methods
    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

        // Code if you need  . . .

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

        //Ashraf Vai to code in my absence . .
        // Code here for different indexea . . .

        if(index==0)
            share();
        else if(index==1)
            saveImage();

    }

    public void saveImage(){
        File file = FileUtil.getNewFile(FilterActivity.this, "Sticker");
        Bitmap icon = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        System.out.println(icon);


        //Bitmap icon =Bitmap.createBitmap(result,bgImage.getLeft(), bgImage.getTop(), bgImage.getWidth(), bgImage.getHeight());
        try {

            StickerUtils.saveImageToGallery(file, icon);
            StickerUtils.notifySystemGallery(this, file);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage(getString(R.string.SuccessfullySavePhotoAlbum));
            builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } catch (IllegalArgumentException | IllegalStateException ignored) {
            //
        }

        /*if (file != null) {
            stickerView.save(file);
            //Toast.makeText(MainActivity.this, "saved in " + file.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        } else {
            //Toast.makeText(MainActivity.this, "the file is null", Toast.LENGTH_SHORT).show();
        }*/
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERM_RQST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    // do your work here
                } else if (Build.VERSION.SDK_INT >= 23 && !shouldShowRequestPermissionRationale(permissions[0])) {
                    //Toast.makeText(MainActivity.this, "Go to Settings and Grant the permission to use this feature.", Toast.LENGTH_SHORT).show();
                    // User selected the Never Ask Again Option
                    goSettingsPage();
                } else {

                    //Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void share(){

        Bitmap icon = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        String filename = Custom.makeFileNameFrom(".jpg");
        System.out.println(filename);
        saveTempFile(icon, filename);
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/"+filename));
        startActivity(Intent.createChooser(share, getString(R.string.ShareImage)));

    }
    public void saveTempFile(Bitmap icon, String fileName){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        icon.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + fileName);
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (IOException e) {
            e.printStackTrace();
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

    public void noFilter(View view){
        imageView.setImageBitmap(bitmap);
    }

    public void alhazmy_INVERT(View view){

//        ImageProcessor imageProcessor = new ImageProcessor();
//        imageView.setImageBitmap(imageProcessor.doInvert(bitmap));


//        Toast.makeText(FilterActivity.this,
//                "Processing.....", Toast.LENGTH_SHORT).show();

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.INVERT));
    }

    public void applyBlackFilter(View view){

//        Toast.makeText(getApplicationContext(),
//                "Processing.....", Toast.LENGTH_SHORT).show();

        ImageProcessor imageProcessor = new ImageProcessor();
        imageView.setImageBitmap(imageProcessor.applyBlackFilter(bitmap));
    }


    public void alhazmy_GRAY(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.GRAY));
    }

    public void applySnowEffect(View view){

        ImageProcessor imageProcessor = new ImageProcessor();
        imageView.setImageBitmap(imageProcessor.applySnowEffect(bitmap));
    }

    public void alhazmy_OLD(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.OLD));

    }

    public void applyReflection(View view){

        ImageProcessor imageProcessor = new ImageProcessor();
        imageView.setImageBitmap(imageProcessor.applyReflection(bitmap));
    }

    public void alhazmy_BLOCK(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.BLOCK));
    }

    public void alhazmy_NEON(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.NEON,255,0,0));
    }

    public void alhazmy_SKETCH(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.SKETCH));
    }

    public void alhazmy_GOTHAM(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.GOTHAM));
    }


    public void alhazmy_HDR(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.HDR));
    }

    public void alhazmy_LOMO(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.LOMO));
    }

    public void alhazmy_TV(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.TV));
    }

    public void alhazmy_RELIEF(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.RELIEF));
    }

    public void alhazmy_LIGHT(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.LIGHT));
    }

    public void alhazmy_SOFT_GLOW(View view){

        imageView.setImageBitmap(ImageFilter.applyFilter(bitmap, ImageFilter.Filter.SOFT_GLOW));
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




//    public Bitmap toSephia(Bitmap bmpOriginal)
//    {
//        int width, height, r,g, b, c, gry;
//        height = bmpOriginal.getHeight();
//        width = bmpOriginal.getWidth();
//        int depth = 20;
//
//        Bitmap bmpSephia = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bmpSephia);
//        Paint paint = new Paint();
//        ColorMatrix cm = new ColorMatrix();
//        cm.setScale(.3f, .3f, .3f, 1.0f);
//        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
//        paint.setColorFilter(f);
//        canvas.drawBitmap(bmpOriginal, 0, 0, paint);
//        for(int x=0; x < width; x++) {
//            for(int y=0; y < height; y++) {
//                c = bmpOriginal.getPixel(x, y);
//
//                r = Color.red(c);
//                g = Color.green(c);
//                b = Color.blue(c);
//
//                gry = (r + g + b) / 3;
//                r = g = b = gry;
//
//                r = r + (depth * 2);
//                g = g + depth;
//
//                if(r > 255) {
//                    r = 255;
//                }
//                if(g > 255) {
//                    g = 255;
//                }
//                bmpSephia.setPixel(x, y, Color.rgb(r, g, b));
//            }
//        }
//        return bmpSephia;
//    }



//    public Bitmap createEffect(Bitmap src, int depth, double red, double green, double blue) {
//        // image size
//        int width = src.getWidth();
//        int height = src.getHeight();
//        // create output bitmap
//        Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
//        // constant grayscale
//        final double GS_RED = 0.3;
//        final double GS_GREEN = 0.59;
//        final double GS_BLUE = 0.11;
//        // color information
//        int A, R, G, B;
//        int pixel;
//
//        // scan through all pixels
//        for(int x = 0; x < width; ++x) {
//            for(int y = 0; y < height; ++y) {
//                // get pixel color
//                pixel = src.getPixel(x, y);
//                // get color on each channel
//                A = Color.alpha(pixel);
//                R = Color.red(pixel);
//                G = Color.green(pixel);
//                B = Color.blue(pixel);
//                // apply grayscale sample
//                B = G = R = (int)(GS_RED * R + GS_GREEN * G + GS_BLUE * B);
//
//                // apply intensity level for sepid-toning on each channel
//                R += (depth * red);
//                if(R > 255) { R = 255; }
//
//                G += (depth * green);
//                if(G > 255) { G = 255; }
//
//                B += (depth * blue);
//                if(B > 255) { B = 255; }
//
//                // set new pixel color to output image
//                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
//            }
//        }
//
//        // return final image
//        return bmOut;
//    }



}
