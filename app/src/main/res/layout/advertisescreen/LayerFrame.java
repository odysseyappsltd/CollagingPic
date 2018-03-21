package com.example.macbookpro.advertisescreen;

import android.graphics.RectF;

import java.util.ArrayList;

/**
 * Created by OdysseyApps on 3/13/18.
 */

public class LayerFrame {
    private int layerId;
    private ArrayList<RectF> size = new ArrayList<RectF>();
    private ArrayList<int[]> widthBrothers = new ArrayList<int[]>();
    private ArrayList<int[]> heightBrothers = new ArrayList<int[]>();

    public ArrayList<RectF> getSize() {
        return size;
    }
    public ArrayList<int[]> getWidthBrothers() {
        return widthBrothers;
    }
    public ArrayList<int[]> getHeightBrothers() {
        return heightBrothers;
    }

    /*public void setSize(ArrayList<RectF> size) {
        this.size = size;
    }*/
    private void emptyHeightBro(int indx){
        for(int i=0;i<indx;i++)
            heightBrothers.add(new int[]{});
    }
    private void emptyWidthBro(int indx){
        for(int i=0;i<indx;i++)
            widthBrothers.add(new int[]{});
    }

    public LayerFrame(int layerId) {

        this.layerId = layerId;
        CustomDB();
    }
    private void CustomDB(){
        size.clear();
        widthBrothers.clear();
        heightBrothers.clear();

        if(layerId==1000){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 1.0f));
        }

        if(layerId==0){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 1.0f));
            emptyHeightBro(1);
            emptyWidthBro(1);
        }
        else if(layerId==1){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 0.5f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 0.5f, 0.5f));
            size.add(new RectF(0.5f, 0.5f, 0.5f, 0.5f));
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            emptyHeightBro(4);
        }
        else if(layerId==2){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 1.0f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f));
            emptyHeightBro(3);
            emptyWidthBro(3);
        }
        else if(layerId==3){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f, 0.5f));
            emptyWidthBro(2);
            emptyHeightBro(2);
        }
        else if(layerId==4){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 3.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f, 1.0f/4.0f));
            emptyWidthBro(2);
            emptyHeightBro(2);
        }
        else if(layerId==5){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f, 3.0f/4.0f));
            emptyWidthBro(2);
            emptyHeightBro(2);
        }
        else if(layerId==6){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f));
            emptyHeightBro(2);
            emptyWidthBro(2);
        }
        else if(layerId==7){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 3.0f/4.0f, 1.0f));
            emptyHeightBro(2);
            emptyWidthBro(2);
        }
        else if(layerId==8){
            size.add(new RectF(0.0f, 0.0f, 3.0f/4.0f, 1.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f));
            emptyHeightBro(2);
            emptyWidthBro(2);
        }
        else if(layerId==9){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 0.5f, 1.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f, 1.0f/4.0f));
            emptyHeightBro(4);
            emptyWidthBro(4);
        }
        else if(layerId==10){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f));
            size.add(new RectF(0.5f, 0.0f, 1.0f/4.0f, 1.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f));
            emptyHeightBro(4);
            emptyWidthBro(4);
        }
        else if(layerId==11){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 1.0f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 1.0f, 1.0f/3.0f));
            emptyHeightBro(3);
            emptyWidthBro(3);
        }
        else if(layerId==12){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 1.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));
            emptyHeightBro(4);
            widthBrothers.add(new int[]{});
            widthBrothers.add(new int[]{2,3});
            widthBrothers.add(new int[]{1,3});
            widthBrothers.add(new int[]{1,2});
        }
        else if(layerId==13){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));

            emptyHeightBro(4);
            widthBrothers.add(new int[]{2,3});
            widthBrothers.add(new int[]{});
            widthBrothers.add(new int[]{0,3});
            widthBrothers.add(new int[]{0,2});
        }
        else if(layerId==14){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f, 0.5f));
            emptyWidthBro(4);
            heightBrothers.add(new int[]{1,2});
            heightBrothers.add(new int[]{0,2});
            heightBrothers.add(new int[]{0,1});
            heightBrothers.add(new int[]{});
        }
        else if(layerId==15){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 0.5f, 0.5f));
            size.add(new RectF(0.5f, 0.5f, 0.5f, 0.5f));
            heightBrothers.add(new int[]{});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{1});
            emptyWidthBro(3);

        }
        else if(layerId==16){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 0.5f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f, 0.5f));
            emptyWidthBro(3);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{});
        }
        else if(layerId==17){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(1.0f/3.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(2.0f/3.0f, 0.5f, 1.0f/3.0f, 0.5f));
            emptyWidthBro(4);
            heightBrothers.add(new int[]{});
            heightBrothers.add(new int[]{2,3});
            heightBrothers.add(new int[]{1,3});
            heightBrothers.add(new int[]{1,2});
        }
        else if(layerId==18){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 0.5f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 0.5f, 0.5f, 0.5f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));
            emptyHeightBro(5);
            widthBrothers.add(new int[]{2,4});
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{0,4});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{0,2});
        }
        else if(layerId==19){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 2.0f/3.0f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 0.5f, 2.0f/3.0f));
            size.add(new RectF(0.5f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));
            emptyHeightBro(4);
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
        }
        else if(layerId==20){
            size.add(new RectF(0.0f, 0.0f, 2.0f/3.0f, 0.5f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(1.0f/3.0f, 0.5f, 2.0f/3.0f, 0.5f));
            emptyWidthBro(4);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
        }
        else if(layerId==21){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.5f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            emptyWidthBro(8);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{7});
            heightBrothers.add(new int[]{6});

        }
        else if(layerId==22){
            size.add(new RectF(0.0f, 0.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            emptyWidthBro(8);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{7});
            heightBrothers.add(new int[]{6});
        }
        else if(layerId==23){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 1.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));
            size.add(new RectF(0.5f, 2.0f/3.0f, 0.5f, 1.0f/3.0f));
            emptyWidthBro(6);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});

        }
        else if(layerId==24){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 3.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            emptyWidthBro(5);
            heightBrothers.add(new int[]{});
            heightBrothers.add(new int[]{2,3,4});
            heightBrothers.add(new int[]{1,3,4});
            heightBrothers.add(new int[]{1,2,4});
            heightBrothers.add(new int[]{1,2,3});

        }
        else if(layerId==25){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f, 3.0f/4.0f));
            emptyWidthBro(5);
            heightBrothers.add(new int[]{1,2,3});
            heightBrothers.add(new int[]{0,2,3});
            heightBrothers.add(new int[]{0,1,3});
            heightBrothers.add(new int[]{0,1,2});
            heightBrothers.add(new int[]{});
        }
        else if(layerId==26){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(2.0f/4.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(3.0f/4.0f, 0.5f, 1.0f/4.0f, 0.5f));
            emptyHeightBro(8);
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{6});
            widthBrothers.add(new int[]{7});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3});
        }
        else if(layerId==27){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 1.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 2.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            emptyWidthBro(8);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{7});
            heightBrothers.add(new int[]{6});
        }
        else if(layerId==28){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            widthBrothers.add(new int[]{4,8,12});
            widthBrothers.add(new int[]{5,9,13});
            widthBrothers.add(new int[]{6,10,14});
            widthBrothers.add(new int[]{7,11,15});
            widthBrothers.add(new int[]{0,8,12});
            widthBrothers.add(new int[]{1,9,13});
            widthBrothers.add(new int[]{2,10,14});
            widthBrothers.add(new int[]{3,11,15});
            widthBrothers.add(new int[]{0,4,12});
            widthBrothers.add(new int[]{1,5,13});
            widthBrothers.add(new int[]{2,6,14});
            widthBrothers.add(new int[]{3,7,15});
            widthBrothers.add(new int[]{0,4,8});
            widthBrothers.add(new int[]{1,5,9});
            widthBrothers.add(new int[]{2,6,10});
            widthBrothers.add(new int[]{3,7,11});

            heightBrothers.add(new int[]{1,2,3});
            heightBrothers.add(new int[]{0,2,3});
            heightBrothers.add(new int[]{0,1,3});
            heightBrothers.add(new int[]{0,1,2});
            heightBrothers.add(new int[]{5,6,7});
            heightBrothers.add(new int[]{4,6,7});
            heightBrothers.add(new int[]{4,5,7});
            heightBrothers.add(new int[]{4,5,6});
            heightBrothers.add(new int[]{9,10,11});
            heightBrothers.add(new int[]{8,10,11});
            heightBrothers.add(new int[]{8,9,11});
            heightBrothers.add(new int[]{8,9,10});
            heightBrothers.add(new int[]{13,14,15});
            heightBrothers.add(new int[]{12,14,15});
            heightBrothers.add(new int[]{12,13,15});
            heightBrothers.add(new int[]{12,13,14});
        }
        else if(layerId==29){
            size.add(new RectF(0.0f, 0.0f, 1.0f, 0.5f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            emptyWidthBro(9);
            heightBrothers.add(new int[]{});
            heightBrothers.add(new int[]{2,3,4});
            heightBrothers.add(new int[]{1,3,4});
            heightBrothers.add(new int[]{1,2,4});
            heightBrothers.add(new int[]{1,2,3});
            heightBrothers.add(new int[]{6,7,8});
            heightBrothers.add(new int[]{5,7,8});
            heightBrothers.add(new int[]{5,6,8});
            heightBrothers.add(new int[]{5,6,7});
        }
        else if(layerId==30){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 0.5f, 1.0f, 0.5f));
            emptyWidthBro(9);
            heightBrothers.add(new int[]{1,2,3});
            heightBrothers.add(new int[]{0,2,3});
            heightBrothers.add(new int[]{0,1,3});
            heightBrothers.add(new int[]{0,1,2});
            heightBrothers.add(new int[]{5,6,7});
            heightBrothers.add(new int[]{4,6,7});
            heightBrothers.add(new int[]{4,5,7});
            heightBrothers.add(new int[]{4,5,6});
            heightBrothers.add(new int[]{});
        }
        else if(layerId==31){
            size.add(new RectF(0.0f, 0.0f, 3.0f/4.0f, 1.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            emptyHeightBro(5);
            widthBrothers.add(new int[]{});
            widthBrothers.add(new int[]{2,3,4});
            widthBrothers.add(new int[]{1,3,4});
            widthBrothers.add(new int[]{1,2,4});
            widthBrothers.add(new int[]{1,2,3});
        }
        else if(layerId==32){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 3.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/3.0f, 3.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 3.0f/4.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 1.0f/4.0f, 1.0f/3.0f, 3.0f/4.0f));
            emptyHeightBro(6);
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
        }
        else if(layerId==33){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 3.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 3.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 1.0f/4.0f, 1.0f/3.0f, 3.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 3.0f/4.0f, 1.0f/3.0f, 1.0f/4.0f));
            emptyHeightBro(6);
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
        }
        else if(layerId==34){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/3.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(2.0f/3.0f, 1.0f/4.0f, 1.0f/3.0f, 2.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/3.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 3.0f/4.0f, 1.0f/3.0f, 1.0f/4.0f));
            emptyHeightBro(8);
            widthBrothers.add(new int[]{3,6});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5,7});
            widthBrothers.add(new int[]{0,6});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2,7});
            widthBrothers.add(new int[]{0,3});
            widthBrothers.add(new int[]{2,5});
        }
        else if(layerId==35){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/5.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 2.0f/5.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 3.0f/5.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 4.0f/5.0f));
            size.add(new RectF(0.0f, 1.0f/5.0f, 1.0f/4.0f, 4.0f/5.0f));
            size.add(new RectF(1.0f/4.0f, 2.0f/5.0f, 1.0f/4.0f, 3.0f/5.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/5.0f, 1.0f/4.0f, 2.0f/5.0f));
            size.add(new RectF(3.0f/4.0f, 4.0f/5.0f, 1.0f/4.0f, 1.0f/5.0f));
            emptyHeightBro(8);
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{6});
            widthBrothers.add(new int[]{7});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3});
        }
        else if(layerId==36){
            size.add(new RectF(0.0f, 0.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f));
            emptyWidthBro(8);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{7});
            heightBrothers.add(new int[]{6});
        }
        else if(layerId==37){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3,4});
            heightBrothers.add(new int[]{2,4});
            heightBrothers.add(new int[]{2,3});
            heightBrothers.add(new int[]{6});
            heightBrothers.add(new int[]{5});
            emptyWidthBro(7);
        }
        else if(layerId==38){
            size.add(new RectF(0.0f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f, 2.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(0.5f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            emptyWidthBro(5);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{3});
        }
        else if(layerId==39){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 2.0f/4.0f, 1.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(3.0f/4.0f, 0.5f, 1.0f/4.0f, 0.5f));
            emptyHeightBro(5);
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{2});
        }
        else if(layerId==40){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 0.5f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 0.5f, 0.5f));
            size.add(new RectF(3.0f/4.0f, 0.5f, 1.0f/4.0f, 0.5f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 0.5f, 1.0f/4.0f));
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{4,6});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1,6});
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{1,4});

            emptyHeightBro(7);

        }
        else if(layerId==41){
            size.add(new RectF(0.0f, 0.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f));

            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{2});
            heightBrothers.add(new int[]{5});
            heightBrothers.add(new int[]{4});

            emptyWidthBro(6);


        }
        else if(layerId==42){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 2.0f/3.0f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 1.0f/3.0f, 2.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 2.0f/3.0f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(1.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f, 1.0f/3.0f));
            emptyHeightBro(6);
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
        }
        else if(layerId==43){
            size.add(new RectF(0.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(1.0f/3.0f, 0.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(0.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(1.0f/3.0f, 1.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(1.0f/3.0f, 2.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));

            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3,4});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1,4});
            widthBrothers.add(new int[]{1,3});
            emptyHeightBro(5);
        }
        else if(layerId==44){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));

            emptyHeightBro(8);
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{4,6,7});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{0});
            widthBrothers.add(new int[]{1,6,7});
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{1,4,7});
            widthBrothers.add(new int[]{1,4,6});

        }
        else if(layerId==45){
            size.add(new RectF(0.0f, 0.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 2.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(0.0f, 3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));

            emptyWidthBro(8);
            heightBrothers.add(new int[]{1});
            heightBrothers.add(new int[]{0});
            heightBrothers.add(new int[]{3,4,5});
            heightBrothers.add(new int[]{2,4,5});
            heightBrothers.add(new int[]{2,3,5});
            heightBrothers.add(new int[]{2,3,4});
            heightBrothers.add(new int[]{7});
            heightBrothers.add(new int[]{6});
        }
        else if(layerId==46){
            size.add(new RectF(0.0f, 0.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 0.0f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(0.0f, 1.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/3.0f, 0.5f, 1.0f/3.0f, 0.5f));
            size.add(new RectF(0.0f, 2.0f/3.0f, 2.0f/3.0f, 1.0f/3.0f));
            widthBrothers.add(new int[]{2,4});
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{0,4});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{0,2});
            emptyHeightBro(5);
        }
        else if(layerId==47){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 2.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            widthBrothers.add(new int[]{3,6});
            widthBrothers.add(new int[]{4});
            widthBrothers.add(new int[]{5,7});
            widthBrothers.add(new int[]{0,6});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2,7});
            widthBrothers.add(new int[]{0,3});
            widthBrothers.add(new int[]{2,5});
            emptyHeightBro(8);
        }
        else if(layerId==48){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            emptyWidthBro(8);
            heightBrothers.add(new int[]{1,2});
            heightBrothers.add(new int[]{0,2});
            heightBrothers.add(new int[]{0,1});
            heightBrothers.add(new int[]{4});
            heightBrothers.add(new int[]{3});
            heightBrothers.add(new int[]{6,7});
            heightBrothers.add(new int[]{5,7});
            heightBrothers.add(new int[]{5,6});
        }
        else if(layerId==49){
            size.add(new RectF(0.0f, 0.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(4.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/3.0f, 2.0f/4.0f));
            size.add(new RectF(1.0f/3.0f, 1.0f/4.0f, 1.0f/3.0f, 2.0f/4.0f));
            size.add(new RectF(2.0f/3.0f, 1.0f/4.0f, 1.0f/3.0f, 2.0f/4.0f));

            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/5.0f, 3.0f/4.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/5.0f, 3.0f/4.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/5.0f, 3.0f/4.0f, 1.0f/5.0f, 1.0f/4.0f));
            size.add(new RectF(4.0f/5.0f, 3.0f/4.0f, 1.0f/5.0f, 1.0f/4.0f));

            heightBrothers.add(new int[]{1,2,3,4});
            heightBrothers.add(new int[]{0,2,3,4});
            heightBrothers.add(new int[]{0,1,3,4});
            heightBrothers.add(new int[]{0,1,2,4});
            heightBrothers.add(new int[]{0,1,2,3});
            heightBrothers.add(new int[]{6,7});
            heightBrothers.add(new int[]{5,7});
            heightBrothers.add(new int[]{5,6});
            heightBrothers.add(new int[]{9,10,11,12});
            heightBrothers.add(new int[]{8,10,11,12});
            heightBrothers.add(new int[]{8,9,11,12});
            heightBrothers.add(new int[]{8,9,10,12});
            heightBrothers.add(new int[]{8,9,10,11});
            emptyWidthBro(13);
        }
        else if(layerId==50){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 3.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 3.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 3.0f/4.0f));

            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            emptyHeightBro(10);
            widthBrothers.add(new int[]{4,8,9});
            widthBrothers.add(new int[]{5});
            widthBrothers.add(new int[]{6});
            widthBrothers.add(new int[]{7});
            widthBrothers.add(new int[]{0,8,9});
            widthBrothers.add(new int[]{1});
            widthBrothers.add(new int[]{2});
            widthBrothers.add(new int[]{3});
            widthBrothers.add(new int[]{0,4,9});
            widthBrothers.add(new int[]{0,4,8});

        }
        else if(layerId==51){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            widthBrothers.add(new int[]{3,6,8});
            widthBrothers.add(new int[]{4,9});
            widthBrothers.add(new int[]{5,7,10});
            widthBrothers.add(new int[]{0,6,8});
            widthBrothers.add(new int[]{1,9});
            widthBrothers.add(new int[]{2,7,10});
            widthBrothers.add(new int[]{0,3,8});
            widthBrothers.add(new int[]{2,5,10});
            widthBrothers.add(new int[]{0,3,6});
            widthBrothers.add(new int[]{1,4});
            widthBrothers.add(new int[]{2,5,7});
            emptyHeightBro(11);
        }
        else if(layerId==52){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));

            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            //size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            //size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            emptyWidthBro(12);
            heightBrothers.add(new int[]{1,2,3});
            heightBrothers.add(new int[]{0,2,3});
            heightBrothers.add(new int[]{0,1,3});
            heightBrothers.add(new int[]{0,1,2});
            heightBrothers.add(new int[]{5,6,7});
            heightBrothers.add(new int[]{4,6,7});
            heightBrothers.add(new int[]{4,5,7});
            heightBrothers.add(new int[]{4,5,6});
            heightBrothers.add(new int[]{9,10,11});
            heightBrothers.add(new int[]{8,10,11});
            heightBrothers.add(new int[]{8,9,11});
            heightBrothers.add(new int[]{8,9,10});
        }
        else if(layerId==53){
            size.add(new RectF(0.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 0.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 2.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            size.add(new RectF(3.0f/4.0f, 2.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(0.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(1.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(2.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));
            size.add(new RectF(3.0f/4.0f, 3.0f/4.0f, 1.0f/4.0f, 1.0f/4.0f));

            widthBrothers.add(new int[]{4,8,10});
            widthBrothers.add(new int[]{5,11});
            widthBrothers.add(new int[]{6,12});
            widthBrothers.add(new int[]{7,9,13});
            widthBrothers.add(new int[]{0,8,10});
            widthBrothers.add(new int[]{1,11});
            widthBrothers.add(new int[]{2,12});
            widthBrothers.add(new int[]{3,9,13});
            widthBrothers.add(new int[]{0,4,10});
            widthBrothers.add(new int[]{3,7,13});
            widthBrothers.add(new int[]{0,4,8});
            widthBrothers.add(new int[]{1,5});
            widthBrothers.add(new int[]{2,6});
            widthBrothers.add(new int[]{3,7,9});

            emptyHeightBro(14);
        }
        else if(layerId==54){
            size.add(new RectF(0.0f, 0.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(1.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(3.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(4.0f/5.0f, 0.0f, 1.0f/5.0f, 1.0f/3.0f));

            size.add(new RectF(0.0f, 1.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(1.0f/5.0f, 1.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/5.0f, 1.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(3.0f/5.0f, 1.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(4.0f/5.0f, 1.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));

            size.add(new RectF(0.0f, 2.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(1.0f/5.0f, 2.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(2.0f/5.0f, 2.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(3.0f/5.0f, 2.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            size.add(new RectF(4.0f/5.0f, 2.0f/3.0f, 1.0f/5.0f, 1.0f/3.0f));
            emptyHeightBro(15);
            widthBrothers.add(new int[]{5,10});
            widthBrothers.add(new int[]{6,11});
            widthBrothers.add(new int[]{7,12});
            widthBrothers.add(new int[]{8,13});
            widthBrothers.add(new int[]{9,14});
            widthBrothers.add(new int[]{0,10});
            widthBrothers.add(new int[]{1,11});
            widthBrothers.add(new int[]{2,12});
            widthBrothers.add(new int[]{3,13});
            widthBrothers.add(new int[]{4,14});
            widthBrothers.add(new int[]{0,5});
            widthBrothers.add(new int[]{1,6});
            widthBrothers.add(new int[]{2,7});
            widthBrothers.add(new int[]{3,8});
            widthBrothers.add(new int[]{4,9});
        }

    }


}


        /*else if(self.no==4){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0, 1.0/4.0)]];
        }
        else if(self.no==5){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0, 3.0/4.0)]];
        }
        else if(self.no==6){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0)]];
        }
        else if(self.no==7){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 3.0/4.0, 1.0)]];
        }
        else if(self.no==8){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 3.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0)]];
        }
        else if(self.no==9){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0, 1.0/4.0)]];
        }
        else if(self.no==10){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0)]];
        }
        else if(self.no==11){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 1.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/3.0, 1.0, 1.0/3.0)]];
        }
        else if(self.no==12){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 1.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 2.0/3.0, 0.5, 1.0/3.0)]];
        }
        else if(self.no==13){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/3.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0)]];
        }
        else if(self.no==14){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0, 0.5)]];
        }
        else if(self.no==15){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.5, 0.5, 0.5)]];
        }
        else if(self.no==16){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0, 0.5)]];
        }
        else if(self.no==17){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.5, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.5, 1.0/3.0, 0.5)]];
        }
        else if(self.no==18){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.5, 0.5, 0.5)]];
        }
        else if(self.no==19){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 0.5, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 2.0/3.0, 0.5, 1.0/3.0)]];
        }
        else if(self.no==20){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 2.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.5, 2.0/3.0, 0.5)]];
        }
        else if(self.no==21){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];



        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 3.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==22){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 3.0/4.0, 1.0/4.0)]];



        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==23){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 1.0/3.0, 0.5, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 2.0/3.0, 0.5, 1.0/3.0)]];
        }
        else if(self.no==24){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==25){

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0, 3.0/4.0)]];
        }
        else if(self.no==26){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.5, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.5, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.5, 1.0/4.0, 0.5)]];
        }
        else if(self.no==27){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 1.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 2.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 3.0/4.0, 0.5, 1.0/4.0)]];
        }
        else if(self.no==28){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==29){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==30){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];

        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0, 0.5)]];
        }
        else if(self.no==31){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 3.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==32){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/3.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 1.0/3.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 3.0/4.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 1.0/4.0, 1.0/3.0, 3.0/4.0)]];
        }
        else if(self.no==33){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 1.0/4.0, 1.0/3.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 3.0/4.0, 1.0/3.0, 1.0/4.0)]];
        }
        else if(self.no==34){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/3.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.5, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 1.0/4.0, 1.0/3.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 3.0/4.0, 1.0/3.0, 1.0/4.0)]];
        }
        else if(self.no==35){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/5.0, 1.0/4.0, 4.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 2.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/5.0, 1.0/4.0, 3.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 3.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/5.0, 1.0/4.0, 2.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 4.0/5.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 4.0/5.0, 1.0/4.0, 1.0/5.0)]];
        }
        else if(self.no==36){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 3.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 3.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==37){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 2.0/4.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0, 1.0/4.0)]];
        }
        else if(self.no==38){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 0.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 0.5, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.5, 3.0/4.0, 0.5, 1.0/4.0)]];
        }
        else if(self.no==39){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 2.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.5, 1.0/4.0, 0.5)]];
        }
        else if(self.no==40){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 2.0/4.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0)]];
        }
        else if(self.no==41){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 2.0/3.0, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 1.0/3.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        }
        else if(self.no==42){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 2.0/3.0, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 1.0/3.0, 1.0/3.0, 1.0/3.0)]];


        }
        else if(self.no==43){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.5, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 2.0/3.0, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 2.0/3.0, 1.0/3.0, 1.0/3.0)]];
        }
        else if(self.no==44){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 3.0/4.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==45){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 3.0/4.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];


        }
        else if(self.no==46){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/3.0, 0.0, 1.0/3.0, 1.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/3.0, 2.0/3.0, 2.0/3.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.0, 1.0/3.0, 0.5)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/3.0, 0.5, 1.0/3.0, 0.5)]];

        }
        else if(self.no==47){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 3.0/4.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==48){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 3.0/4.0, 3.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==49){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 2.0/4.0, 2.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==50){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        }
        else if(self.no==51){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }
        else if(self.no==52){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];



        }
        else if(self.no==53){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        //[self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];




        }
        else if(self.no==54){
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 0.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 1.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(2.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 2.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(0.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(1.0/4.0, 3.0/4.0, 2.0/4.0, 1.0/4.0)]];
        [self.size addObject:[NSValue valueWithCGRect:CGRectMake(3.0/4.0, 3.0/4.0, 1.0/4.0, 1.0/4.0)]];

        }*/




















