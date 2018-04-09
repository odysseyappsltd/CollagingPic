package com.odyssey.apps.IAP;


import com.odyssey.apps.collagingpic.R;

/**
 * Created by admin on 2018-02-17.
 */




public class IAPData {
    // Singleton Structure
    private static final IAPData instance = new IAPData();
    //private constructor to avoid client applications to use constructor
    private IAPData(){}
    public static IAPData getSharedInstance(){
        return instance;
    }


    // Icons
    private int[] icons = new int[]{


            R.drawable.settingunlockpattern,
            R.drawable.settingofflineuseageremoveads

    };

    public int getNumberOfIcons(){
        return icons.length ;
    }

    public int[] getIcons(){
        return icons;
    }

    //Titles
    private int[] titles = new int[]{

            R.string.PatternsTitle,
            R.string.AdvertisementsTitle

    };

    public int getTitlesSize(){
        return titles.length ;
    }

    public int[] getTitles(){
        return titles;
    }

    //Sub Titles
    private int[] subTitles = new int[]{


            R.string.PatternsSubitle,
            R.string.AdvertisementsSubitle
    };

    public int getSubTitlesSize(){
        return subTitles.length ;
    }

    public int[] getSubTitles(){
        return subTitles;
    }

    //Professional Product IDs
    public String PRO = "collaging.pro";

    //General Product IDs
    public String PATTERN = "collaging.pattern";
    public String ADMOB = "collaging.admob";

    private String[] generalProductIDs = new String[]{

            PATTERN,
            ADMOB
    };

    public int getGeneralProductIDsSize(){
        return generalProductIDs.length ;
    }
    public String[] getGeneralProductIDs(){
        return generalProductIDs;
    }
    public String getGeneralProductIDAt(int index ){
        return generalProductIDs[index];
    }
    public String getProProductID(){
        return PRO;
    }

    // Demo Price List
    private String[] demoPriceList = new String[]{
            "$2.99",
            "$2.99"
    };
    public int getDemoPriceListSize(){
        return demoPriceList.length ;
    }

    public String[] getDemoPriceList(){
        return demoPriceList;
    }





}
