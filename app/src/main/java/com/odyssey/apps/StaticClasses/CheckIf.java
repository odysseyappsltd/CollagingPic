package com.odyssey.apps.StaticClasses;

import android.content.Context;

import com.odyssey.apps.IAP.IAPData;


/**
 * Created by admin on 2018-02-20.
 */

public class CheckIf {

    /*
        Note : Product List  . .

        background,
        sticker,
        admob,
        font,
        color,
        all

     */



    public static boolean isPurchased(String product , Context onContext){
        switch (product){
            case "admob" :
                product = IAPData.getSharedInstance().ADMOB;
                break;
            case "pattern" :
                product = IAPData.getSharedInstance().PATTERN;
                break;
            case "all" :
                product = IAPData.getSharedInstance().PRO;
                break;
        }
        return UserDefault.check( product , onContext) ;
    }
}
