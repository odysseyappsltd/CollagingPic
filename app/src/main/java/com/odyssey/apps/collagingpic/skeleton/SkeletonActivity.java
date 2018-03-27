package com.odyssey.apps.collagingpic.skeleton;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.adobe.creativesdk.aviary.AdobeImageIntent;
import com.odyssey.apps.Settings.SettingsActivity;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.popUp.PopUpActivity;

import java.util.ArrayList;

public class SkeletonActivity extends AppCompatActivity implements View.OnTouchListener {


    private Colage[] allColages = new Colage[50];
    ViewGroup mainView;
    private int initialPosX;
    private int initialPosY;
    int border;
    int slider_bar;
    int NO_OF_COLLAGE_FRAMES;

    private Matrix matrix1 = new Matrix();
    private Matrix savedMatrix1 = new Matrix();
    private Matrix matrix2 = new Matrix();
    private Matrix savedMatrix2 = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;

    private ImageView view;
    private  Bitmap bmap;
    private int mVSize;
    private Colage topColage;
    private Colage bottomColage;
    private Colage leftColage;
    private Colage rightColage;
    private RectF fakeMainView;
    private int layerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_skeleton);
        mVSize = getScreenWidth()-50;

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
                    (int)(mVSize*layerSize.get(i).right), (int)(mVSize*layerSize.get(i).bottom));
            mainView.addView(colage);
            allColages[i] = colage;
            colage.setOnTouchListener(this);
            colage.setHeightBrothers(layerHBro.get(i));
            colage.setWidthBrothers(layerWBro.get(i));

        }
    }
    public Colage addColage(int x,int y, int width,int height){


        Colage colage = new Colage(this);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                width,height);
        rlp.leftMargin =x;
        rlp.topMargin = y;
        colage.setLayoutParams(rlp);
        colage.setPadding(slider_bar,slider_bar,slider_bar,slider_bar);

        ImageView iv = new ImageView(this);
        iv.setTag("1");
        iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.p2));
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

        moveTopCollageBrothers(tc);
    }
    private void moveBottomCollage(int y){
        Colage bc = bottomColage;
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) bc.getLayoutParams();
        lparam.height=(int)fakeMainView.bottom-(y-(int)fakeMainView.top);//mainView.getHeight()-y;
        lparam.topMargin=y;
        bc.setLayoutParams(lparam);
        bc.invalidate();

        moveBottomCollageBrothers(bc);
    }
    private void moveLeftCollage(int x){
        Colage lc = leftColage;
        RelativeLayout.LayoutParams lparam = (RelativeLayout.LayoutParams) lc.getLayoutParams();
        lparam.width=x-(int)fakeMainView.left;
        lc.setLayoutParams(lparam);
        lc.invalidate();
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

    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();

        // handle touch events here
        if(v instanceof ImageView) {

            view = (ImageView) v;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:

                    if (view.getTag().equals("1"))
                        savedMatrix1.set(matrix1);
                    else
                        savedMatrix2.set(matrix2);
                    start.set(event.getX(), event.getY());
                    mode = DRAG;
                    //lastEvent = null;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    oldDist = spacing(event);
                    if (oldDist > 10f) {
                        if (view.getTag().equals("1"))
                            savedMatrix1.set(matrix1);
                        else
                            savedMatrix2.set(matrix2);
                        midPoint(mid, event);
                        mode = ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    //lastEvent = null;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        if (view.getTag().equals("1"))
                            matrix1.set(savedMatrix1);
                        else
                            matrix2.set(savedMatrix2);

                        float dx = event.getX() - start.x;
                        float dy = event.getY() - start.y;

                        if (view.getTag().equals("1"))
                            matrix1.postTranslate(dx, dy);
                        else
                            matrix2.postTranslate(dx, dy);

                    } else if (mode == ZOOM) {
                        float newDist = spacing(event);
                        if (newDist > 10f) {
                            if (view.getTag().equals("1"))
                                matrix1.set(savedMatrix1);
                            else
                                matrix2.set(savedMatrix2);


                            float scale = (newDist / oldDist);

                            if (view.getTag().equals("1"))
                                matrix1.postScale(scale, scale, mid.x, mid.y);
                            else
                                matrix2.postScale(scale, scale, mid.x, mid.y);
                        }
                    }
                    break;
            }
            Matrix matrix;
            matrix = matrix2;
            if (view.getTag().equals("1"))
                matrix = matrix1;

            view.setImageMatrix(matrix);
            bmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bmap);
            view.draw(canvas);

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
        return true;
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

    public void frameAct(View view){

    }
    public void styleAct(View view){

        Intent style = new Intent(SkeletonActivity.this,PopUpActivity.class);
        startActivity(style);
    }
    public void aspectAct(View view){

    }
    public void shareAct(View view){

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.p200);
        String path = bitmap.toString();
        Uri imageUri = Uri.parse(path);
        System.out.println(imageUri);
        Intent imageEditorIntent = new AdobeImageIntent.Builder(this).setData(imageUri).build();
        startActivityForResult(imageEditorIntent, 1);
        finish(); // Comment this out to receive edited image


    }
    public void settingAct(View view){
        Intent setting = new Intent(SkeletonActivity.this,SettingsActivity.class);
        startActivity(setting);

    }

}

        /*Button popButton = (Button) findViewById(R.id.popButton);
        popButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PopUpActivity.class));


            }
        });

        Button settingButtom = (Button) findViewById(R.id.settingsButton);
        settingButtom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SettingsActivity.class));


            }
        });*/
