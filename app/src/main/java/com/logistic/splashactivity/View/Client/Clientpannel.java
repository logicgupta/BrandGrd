package com.logistic.splashactivity.View.Client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.api.Api;
import com.google.firebase.messaging.FirebaseMessaging;
import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Client.AddItems.AddProductClientCategory;
import com.logistic.splashactivity.View.Client.DealOfDay.AddDealProductActivity;
import com.logistic.splashactivity.View.Client.Delivery.AllDeliverPersonActivity;
import com.logistic.splashactivity.View.Client.Delivery.UpdateDeliveryOrderListActivity;
import com.logistic.splashactivity.View.Client.MilkDiary.MilkDairyCategory;
import com.logistic.splashactivity.View.Client.MilkDiary.MilkDiaryActivity;
import com.logistic.splashactivity.View.Client.MyAccount.ProfileActivity;
import com.logistic.splashactivity.View.Client.OrderRequest.ClientOrderListActivity;
import com.logistic.splashactivity.View.Client.ViewMilkProducts.ViewMilkProductActivity;
import com.logistic.splashactivity.View.Client.ViewProduct.ProductList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.logistic.splashactivity.View.Login.LoginActvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Clientpannel extends AppCompatActivity {

CardView cardViewProfile,cardViewAddProduct,cardViewOrderRequest,
            cardViewViewProducts,cardViewOrderDetails,cardViewDelivery
        ,cardViewMilkDairy,cardViewmilkSee,cardViewOrderApproved,cardViewbestDetails,cardViewsummerDetails;

CollectionReference collectionReference;
FirebaseFirestore firestore;
FirebaseAuth firebaseAuth;
String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientpannel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();

        FetchStoreSellerName();

        FirebaseMessaging.getInstance().subscribeToTopic("addProduct")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = getString(R.string.msg_subscribed);
                        if (!task.isSuccessful()) {
                            msg = getString(R.string.msg_subscribe_failed);
                        }
                        Log.d("ClientActivity", msg);
                        Toast.makeText(Clientpannel.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        cardViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Clientpannel.this, ProfileActivity.class));
            }
        });

        cardViewAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clientpannel.this, AddProductClientCategory.class));

            }
        });

        /*
                Deal Of the Day ------------------------------------
         */

        cardViewOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Clientpannel.this, AddDealProductActivity.class));
            }
        });

        cardViewDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Clientpannel.this, AllDeliverPersonActivity.class));

            }
        });

        cardViewViewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clientpannel.this, ProductList.class));
            }
        });

        cardViewOrderRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Clientpannel.this,ClientOrderListActivity.class);
                intent.putExtra("activity","No");
                startActivity(intent);
            }
        });

        cardViewMilkDairy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clientpannel.this, MilkDairyCategory.class));

            }
        });

        cardViewmilkSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Clientpannel.this, ViewMilkProductActivity.class));

            }
        });

        cardViewOrderApproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this,ClientOrderListActivity.class);
                intent.putExtra("activity","Yes");
                startActivity(intent);
            }
        });
        cardViewbestDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this,BestSellingActivity.class);
                intent.putExtra("activity","Yes");
                startActivity(intent);
            }
        });
        cardViewsummerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this,SummerDealActivity.class);
                intent.putExtra("activity","Yes");
                startActivity(intent);
            }
        });



    }

    private Boolean exit = false;
    @Override
    public void onBackPressed() {

        if (exit) {
            //  super.onBackPressed();
            moveTaskToBack(true);
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exit = false;
            }
        }, 3 * 1000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.clientpannel, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
             /*
                    UserType Key
             */
            SharedPreferences preferences=getApplicationContext()
                    .getSharedPreferences("userType",0);
            SharedPreferences.Editor editor=preferences.edit();
            editor.clear().apply();

            /*
                        Seller Key
             */
            SharedPreferences pre=getApplicationContext()
                    .getSharedPreferences("product_seller",0);
            SharedPreferences.Editor edit=pre.edit();
            edit.clear().commit();

            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(Clientpannel.this, LoginActvity.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void initViews(){

        cardViewProfile = findViewById(R.id.myprofile);
        cardViewAddProduct= findViewById(R.id.addProduct);
        cardViewOrderRequest = findViewById(R.id.orderRequest);
        cardViewOrderApproved = findViewById(R.id.approvedOrders);
        cardViewOrderDetails= findViewById(R.id.orderDetails);
        cardViewViewProducts = findViewById(R.id.viewProduct);
        cardViewDelivery = findViewById(R.id.deliever);
        cardViewMilkDairy = findViewById(R.id.milkRequest);
        cardViewmilkSee = findViewById(R.id.milkSee);
        cardViewbestDetails = findViewById(R.id.bestDetails);
        cardViewsummerDetails=findViewById(R.id.summerDetails);
    }

    public void FetchStoreSellerName(){
        firestore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

        collectionReference=firestore
                .collection("BrandMore")
                .document("Shop")
                .collection(firebaseAuth.getUid());
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot :task.getResult()){

                        Log.e("Profile ",""+documentSnapshot.getData());


                        name=documentSnapshot.getString("name");
                        SharedPreferences preferences=getApplicationContext()
                                .getSharedPreferences("product_seller",0);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("productSeller",name);
                        editor.putString("sellerAddress",documentSnapshot.getString("address"));
                        editor.putString("sellerNumber",documentSnapshot.getString("phoneNumber"));
                        editor.commit();
                    }
                }
            }
        });
    }
}
