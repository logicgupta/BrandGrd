package com.logistic.splashactivity.View.Admin;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Admin.ClientCancel.ClientApprovedOrderRequest;
import com.logistic.splashactivity.View.Admin.ClientCancel.ClientCancelOrderActivity;
import com.logistic.splashactivity.View.Admin.NewProduct.NewProductRequestActivity;
import com.logistic.splashactivity.View.Login.LoginActvity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class AdminPannel extends AppCompatActivity {

    CardView cardViewSellerList, cardViewSellerRequest, cardViewCoverImages,
            cardViewDailyReport, cardViewSetleReport, cardViewCoupons,
            cardViewSellers,cardViewSellerCancel,cardViewnewOrder,cardVieworderCancelled,cardViewdeliever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pannel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();


        // Event On CardViews ....

        cardViewSellerList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminPannel.this,AdminAddProductsActivity.class));
            }
        });

        cardViewSellerRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPannel.this,CancelOrderList.class));

            }
        });

        cardViewCoverImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminPannel.this,CoverImagesActivity.class));

            }
        });

        cardViewDailyReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminPannel.this,CategoryOfProductList.class);
                startActivity(intent);

            }
        });

        cardViewSetleReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminPannel.this,OrderListActivity.class);
                startActivity(intent);
            }
        });

        cardViewCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPannel.this,AddSpecialDeals.class));

            }
        });

        cardViewSellers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminPannel.this,SellersListActivity.class));
            }
        });

        cardViewSellerCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPannel.this, ClientApprovedOrderRequest.class));

            }
        });

        cardViewnewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPannel.this, NewProductRequestActivity.class));

            }
        });

        cardVieworderCancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPannel.this, ClientCancelOrderActivity.class));
            }
        });

        cardViewdeliever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AdminPannel.this,DeliverPersonListActivity.class));
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
        getMenuInflater().inflate(R.menu.admin_pannel, menu);
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
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminPannel.this, LoginActvity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void initViews(){
        cardViewSellerList = findViewById(R.id.listSaloon);
        cardViewSellerRequest = findViewById(R.id.appointment);
        cardViewCoverImages = findViewById(R.id.customer);
        cardViewDailyReport = findViewById(R.id.dailyReport);
        cardViewSetleReport = findViewById(R.id.settleReport);
        cardViewCoupons = findViewById(R.id.employees);
        cardViewSellers=findViewById(R.id.sellers);
        cardViewdeliever=findViewById(R.id.deliever);
        cardViewSellerCancel=findViewById(R.id.sellerCancel);
        cardVieworderCancelled=findViewById(R.id.orderCancelled);
        cardViewnewOrder=findViewById(R.id.newOrder);
    }



}
