package com.odyssey.apps.collagingpic.skeleton;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.odyssey.apps.IAP.IAPData;
import com.odyssey.apps.Settings.SettingsActivity;
import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.popUp.PopUpActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class SkeletonActivity extends AppCompatActivity implements View.OnTouchListener {

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
    private int CLICK_ACTION_THRESHOLD = 20;
    RelativeLayout pop;
    int swapFrom=0;
    Boolean isSwap=false;
    ArrayList<RectF> layerSize;
    int imgSet[] = new int[]{R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2,
            R.drawable.image,R.drawable.image2};


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

    @Override
    public void onDestroy() {

        /*if(mInterstitialAd.isLoaded() && !CheckIf.isPurchased("admob",EditTextActivity.this)){
            mInterstitialAd.show();
        }*/
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mMessageReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mAspectValueReceiver);
        super.onDestroy();

    }

    private void purchasedJustNow(){
        //collectionView.invalidate();
        if(CheckIf.isPurchased(IAPData.getSharedInstance().PATTERN,this)) {
            //findViewById(R.id.ETAdmob).setVisibility(View.GONE);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);

        //Notifications
        NotificationCenter.addReceiver("Purchased",mMessageReceiver,this);
        NotificationCenter.addReceiver(NotiData.getSharedInstance().TIME_TO_PICK_ASPECT_VALUE,mAspectValueReceiver,this);


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
        layerid=48;

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

        pop = (RelativeLayout) LayoutInflater.from(this).inflate(
                R.layout.popupview, null);
        fullView.addView(pop);
        pop.setVisibility(View.INVISIBLE);
    }
    public Colage addColage(int x,int y, int width,int height, int i){


        Colage colage = new Colage(this);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                width,height);
        rlp.leftMargin =x;
        rlp.topMargin = y;
        colage.setLayoutParams(rlp);
        colage.setPadding(slider_bar,slider_bar,slider_bar,slider_bar);

        ImageView iv = new ImageView(this);
        iv.setTag(Integer.valueOf(i));
        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), imgSet[i]));
        iv.setBackgroundColor(Color.parseColor("#225465"));
        iv.setScaleType(ImageView.ScaleType.MATRIX);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        iv.setLayoutParams(lp);

        colage.addView(iv);
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
        ImageView view = (ImageView) cl.getChildAt(0);
        int tag = (Integer) view.getTag();
        limitDrag(matrix[tag],view,tag);
        view.setImageMatrix(matrix[tag]);
        Bitmap bmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmap);
        view.draw(canvas);
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
            view = (ImageView) rl.getChildAt(0);
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
                            ImageView s1 = (ImageView) allColages[swapFrom].getChildAt(0);
                            ImageView s2 = (ImageView) allColages[(Integer) view.getTag()].getChildAt(0);
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
                                pop.setX(event.getRawX() - 20);
                                pop.setY(event.getRawY());
                                pop.setVisibility(View.VISIBLE);
                                swapFrom = (Integer) view.getTag();
                            } else {
                                pop.setVisibility(View.INVISIBLE);
                            }
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
        Toast.makeText(this, "select an image to swap", Toast.LENGTH_SHORT).show();
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

            RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                    (int)(mainParam.width*layerSize.get(i).right),(int)(mainParam.height*layerSize.get(i).bottom));
            rlp.topMargin = (int)(mainParam.height*layerSize.get(i).top);
            rlp.leftMargin = (int)(mainParam.width*layerSize.get(i).left);
            colage.setLayoutParams(rlp);

        }
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

        Intent style = new Intent(SkeletonActivity.this,PopUpActivity.class);
        startActivity(style);
    }
    public void aspectAct(View view){

    }
    public void shareAct(View view){

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


    }
    public void settingAct(View view){
        Intent setting = new Intent(SkeletonActivity.this,SettingsActivity.class);
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

