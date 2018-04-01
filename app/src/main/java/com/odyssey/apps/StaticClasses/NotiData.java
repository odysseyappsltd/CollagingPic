package com.odyssey.apps.StaticClasses;


/**
 * Created by admin on 2018-03-19.
 */

public class NotiData {
    // Singleton Structure
    private static final NotiData instance = new NotiData();
    //private constructor to avoid client applications to use constructor
    private NotiData(){}
    public static NotiData getSharedInstance(){
        return instance;
    }

    public final String TIME_TO_PICK_COLOR = "colorpicker";
    public final String TIME_TO_PICK_PATTERN = "patternpicker";
    public final String TIME_TO_PICK_SHRINK_VALUE = "shrinkvaluepickerpicker";



}
