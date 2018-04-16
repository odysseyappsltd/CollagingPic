package com.odyssey.apps.collagingpic.starting;

import android.graphics.Bitmap;

/**
 * Created by OdysseyApps on 2/12/18.
 */

public class DataPassingSingelton {
    private Bitmap Image;
    private Bitmap Image2;
    private static final DataPassingSingelton ourInstance = new DataPassingSingelton();

    public static DataPassingSingelton getInstance() {
        return ourInstance;
    }

    private DataPassingSingelton() {
    }
    public Bitmap getImage() {
        return this.Image;
    }

    public void setImage(Bitmap ImageIn) {
        this.Image = ImageIn;
    }
    public Bitmap getImage2() {
        return this.Image2;
    }

    public void setImage2(Bitmap ImageIn) {
        this.Image2 = ImageIn;
    }

}
