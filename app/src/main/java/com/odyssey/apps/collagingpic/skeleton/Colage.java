package com.odyssey.apps.collagingpic.skeleton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by OdysseyApps on 3/13/18.
 */

public class Colage extends RelativeLayout {

    private int[] heightBrothers = new int[50];
    private int[] widthBrothers = new int[50];
    //private Boolean isLeftinBorder=false;


    /*public Boolean getLeftinBorder() {
        return isLeftinBorder;
    }

    public void setLeftinBorder(Boolean leftinBorder) {
        this.isLeftinBorder = leftinBorder;
    }*/

    public Colage(Context context) {
        super(context);
        init();

    }
    public Colage(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);

        init();
    }
    public Colage(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }
    public void init()
    {


    }


    public int[] getHeightBrothers() {
        return heightBrothers;
    }

    public void setHeightBrothers(int[] heightBrothers) {
        this.heightBrothers = heightBrothers;
    }

    public int[] getWidthBrothers() {
        return widthBrothers;
    }

    public void setWidthBrothers(int[] widthBrothers) {
        this.widthBrothers = widthBrothers;
    }

}
