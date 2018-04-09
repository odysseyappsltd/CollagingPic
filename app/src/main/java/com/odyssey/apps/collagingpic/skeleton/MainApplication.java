package com.odyssey.apps.collagingpic.skeleton;
import android.app.Application;



public class MainApplication extends Application{

    static final String CREATIVE_SDK_CLIENT_ID      = "49e1f680666f4f7fb17b7129fa6ad230";
    static final String CREATIVE_SDK_CLIENT_SECRET  = "90289592-97a3-42c9-aae0-b8461501fbd8";
    static final String CREATIVE_SDK_REDIRECT_URI   = "ams+156983e397d530c5b23e72fd9b18ff4804a75aa0://adobeid/49e1f680666f4f7fb17b7129fa6ad230";
    static final String[] CREATIVE_SDK_SCOPES       = {"email", "profile", "address"};

    @Override public void onCreate(){
        super.onCreate();
        //AdobeCSDKFoundation.initializeCSDKFoundation(getApplicationContext());
    }


}
