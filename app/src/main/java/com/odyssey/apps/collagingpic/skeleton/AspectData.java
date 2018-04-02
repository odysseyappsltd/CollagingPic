package com.odyssey.apps.collagingpic.skeleton;



public class AspectData {

    // Singleton Structure
    private static final AspectData instance = new AspectData();
    //private constructor to avoid client applications to use constructor
    private AspectData(){}
    public static AspectData getSharedInstance(){
        return instance;
    }


    //aspect picker
    private float aspectValue = 1.0f;
    public float getAspectValue(){return aspectValue; }
    public void saveAspectValue(float value){this.aspectValue = value ;}
}
