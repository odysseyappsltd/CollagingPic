package com.odyssey.apps.IAP;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.odyssey.apps.StaticClasses.CheckIf;
import com.odyssey.apps.StaticClasses.NotiData;
import com.odyssey.apps.StaticClasses.NotificationCenter;
import com.odyssey.apps.StaticClasses.UserDefault;
import com.odyssey.apps.collagingpic.R;
import com.odyssey.apps.iab.BillingProcessor;
import com.odyssey.apps.iab.TransactionDetails;


public class IAPActivity extends AppCompatActivity implements BillingProcessor.IBillingHandler {

    //UI Variables
    ListView iapTable ;

    //Billing Variables
    BillingProcessor bp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_iap);

        //Toast.makeText(IAPActivity.this, "Started", Toast.LENGTH_SHORT).show();

        // Cancel Button Setup . .
        Button cancelButton = (Button) findViewById(R.id.IAPCancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                finish();
            }
        });



        // Billing Process
        bp = new BillingProcessor(
                this,
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwUjiweljYhsZOo+d9vooSPOkFVAyiNw15eC7p8JzER1AiBHhn7f3O1dD+EfUR063r75c28c96ivWu21BUuV+0fLLh0Ukvnki9uiXQkkq/cD2hLY0vSmuXbggjxrM4G9AFlXuz7V/5s0P+kRQBWgaVn4FFNntTFnILM1PZ2M7XfPqqigjOVKSmKzB7PRhkn5oikvn+s/jnKIUqmd+l6CvoWCE9wU8z9SYvIFZDz9L7xRPdM26lu/N+pxkBKZ3D89NKJc5NIXtGJXE43FznKICV+BWGnc0h2o+6DKP0l0z9WZJ5dkfh0ehYwoNEzzv5kNYb2mpqUoTONykG/Wmmytt2wIDAQAB",
                this
        );
        // or bp = BillingProcessor.newBillingProcessor(this, "YOUR LICENSE KEY FROM GOOGLE PLAY CONSOLE HERE", this);

        TextView textBoard  = (TextView) findViewById(R.id.textBoard) ;
        textBoard.setText(

                        this.getString(R.string.PatternsSubitle)+"\n"+
                                this.getString( R.string.AdvertisementsSubitle)+"\n"
        );

        iapTable = (ListView) findViewById(R.id.IAPlistView);
        final IAPAdapter adapter = new IAPAdapter(IAPActivity.this,bp);
        iapTable.setAdapter(adapter);
        //System.out.println("CRASHED!!!!");






        iapTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String productID = IAPData.getSharedInstance().getGeneralProductIDAt(i);
                //Toast.makeText(IAPActivity.this, "Trying to buy: " + productID , Toast.LENGTH_SHORT).show();
                bp.purchase( IAPActivity.this , productID);
                if(bp.isPurchased(productID)){
                    //Toast.makeText(IAPActivity.this, "purchased !", Toast.LENGTH_SHORT).show();
                }
            }
        });





        //Pro Button Setup . .
        Button proButton = (Button) findViewById(R.id.IAPBuyButton);
        String titleString = proButton.getText().toString() + ": $2.99";
        proButton.setText("   "+titleString+"   ");
        proButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                bp.purchase( IAPActivity.this , IAPData.getSharedInstance().getProProductID());
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }

    // In App Billing Delegates
    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {

        // Called when requested PRODUCT ID was successfully purchased
        //System.out.print("Product Purchased Successfully");
        UserDefault.set(true,productId,this);
        if( productId == IAPData.getSharedInstance().getProProductID()){
            String[] idOthers = IAPData.getSharedInstance().getGeneralProductIDs() ;
            for(String eachID : idOthers){
                UserDefault.set(true,eachID,this);
            }
        }

        NotificationCenter.broadcast(NotiData.getSharedInstance().SOMETHING_JUST_PURCHASED,this);


    }

    @Override
    public void onPurchaseHistoryRestored() {

        /*
             Called when purchase history was restored and the list of all owned PRODUCT ID's
              was loaded from Google Play
        */

        String idBuyPro  = IAPData.getSharedInstance().getProProductID() ;
        String[] idOthers = IAPData.getSharedInstance().getGeneralProductIDs() ;

        boolean didRestored = false ;
        if(bp.isPurchased(idBuyPro) && !CheckIf.isPurchased(idBuyPro,this)){
            didRestored = true ;
            UserDefault.set(true,idBuyPro,this);
            for(String eachID : idOthers){
                UserDefault.set(true,eachID,this);
            }
        }
        else {
            for(String eachID : idOthers) {
                if (bp.isPurchased(eachID) && !CheckIf.isPurchased(eachID,this)) {
                    didRestored = true ;
                    UserDefault.set(true, eachID, this);
                }
            }
        }

        if(didRestored) {
            Toast.makeText(this, getString(R.string.RestoreToast), Toast.LENGTH_SHORT).show();
            NotificationCenter.broadcast(NotiData.getSharedInstance().SOMETHING_JUST_PURCHASED, this);
        }
    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {


        /*
            Called when some error occurred. See Constants class for more details
            Note - this includes handling the case where the user canceled the buy dialog:
            errorCode = Constants.BILLING_RESPONSE_RESULT_USER_CANCELED
        */

    }

    @Override
    public void onBillingInitialized() {

        //Toast.makeText(this, "Initialized", Toast.LENGTH_SHORT).show();
        //Called when BillingProcessor was initialized and it's ready to purchase
        onPurchaseHistoryRestored();
        iapTable.invalidateViews();
        Button proButton = (Button)findViewById(R.id.IAPBuyButton);
        proButton.setText("   "+getString(R.string.BuyPro)+": "+bp.getPurchaseListingDetails(IAPData.getSharedInstance().PRO).priceText+"   ");

    }
}

