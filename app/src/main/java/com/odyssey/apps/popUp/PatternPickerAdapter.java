package com.odyssey.apps.popUp;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.collagingpic.R;

/**
 * Created by admin on 2018-03-15.
 */

public class PatternPickerAdapter extends BaseAdapter {



    private Context context ;
    private LayoutInflater inflater ;
    Bitmap lock;


    public PatternPickerAdapter(Context context  ) {
        this.context = context ;

        lock = BitmapFactory.decodeResource(context.getResources(),R.drawable.lock);
    }


    @Override
    public int getCount() {
        return PopUpData.getSharedInstance().countPatterns();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View cell, ViewGroup viewGroup) {


        ImageView imageView;


        int valueInPixels = (int) context.getResources().getDimension(R.dimen.pattern_cell);
        if (cell == null) {
            imageView = new ImageView(context);

            imageView.setLayoutParams(new GridView.LayoutParams(valueInPixels, valueInPixels));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(5, 5, 5, 5);


        }
        else
        {
            imageView = (ImageView) cell;

        }


        //imageView.setBackgroundColor(Color.rgb(99, 101, 112));
        //Drawable d = mContext.getResources().getDrawable(images[position]);
        Bitmap original = BitmapFactory.decodeResource(context.getResources(), PopUpData.getSharedInstance().getMiniPatternsAt(position));

        if(!CheckIf.isPurchased(IAPData.getSharedInstance().PATTERN,context)) {
            if (position > 14)
                original = overlay(original,lock);
        }
        imageView.setImageBitmap(original);


        return imageView;





    }
    private Bitmap resize(Integer image, int width, int height) {
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), image);
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, width, height, false);
        return bitmapResized;
    }



    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap overlay(Bitmap bmp1, Bitmap bmp2) {
        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(bmp1, 0,0, null);
        canvas.drawBitmap(bmp2, 0, 0, null);
        return bmOverlay;
    }

}
