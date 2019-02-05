package com.example.splashactivity.View.Client;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import com.example.splashactivity.R;
import com.example.splashactivity.View.Client.AddItems.AddProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class Clientpannel extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CardView cardViewFruits, cardViewMilk, cardViewVegetables;
    CardView cardViewOil, cardViewBakery, cardViewPooja;
    CardView cardViewBread, cardViewbeauty, cardViewclean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientpannel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initViews();
        cardViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Fruits");
                startActivity(intent);

            }
        });

        cardViewMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Milk");
                startActivity(intent);
            }
        });

        cardViewVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Vegetables");
                startActivity(intent);
            }
        });

        cardViewOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Oil");
                startActivity(intent);

            }
        });

        cardViewBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Bakery");
                startActivity(intent);

            }
        });

        cardViewPooja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Pooja");
                startActivity(intent);

            }
        });

        cardViewBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Bread");
                startActivity(intent);
            }
        });

        cardViewbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Beauty");
                startActivity(intent);

            }
        });

        cardViewclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Clientpannel.this, AddProductActivity.class);
                intent.putExtra("category","Clean");
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_account) {

        } else if (id == R.id.nav_orders) {

        } else if (id == R.id.nav_shipping) {

        } else if (id == R.id.nav_contact) {

        } else if (id == R.id.nav_logOut) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void initViews(){
        cardViewFruits = findViewById(R.id.fruitCategoryCardView);
        cardViewMilk = findViewById(R.id.milkCardView);
        cardViewVegetables = findViewById(R.id.vegetableCardView);
        cardViewOil = findViewById(R.id.oilAndMassaleCategoryCardView);
        cardViewBakery = findViewById(R.id.beautyCardView);
        cardViewPooja = findViewById(R.id.poojaCardView);
        cardViewBread = findViewById(R.id.breadandeggsCategoryCardView);
        cardViewbeauty = findViewById(R.id.beautyCardView);
        cardViewclean = findViewById(R.id.cleaningCardView);


    }
}
