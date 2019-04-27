package com.logistic.splashactivity.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Client.AddItems.AddProductActivity;

public class AdminAddProductsActivity extends AppCompatActivity {
    CardView cardViewFruits, cardViewMilk, cardViewVegetables;
    CardView cardViewOil, cardViewBakery, cardViewPooja;
    CardView cardViewBread, cardViewbeauty, cardViewclean;
    CardView cardViewMedical, cardViewSweet, cardViewFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_products);
        initViews();
        cardViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Fruits");
                startActivity(intent);

            }
        });

        cardViewMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Milk");
                startActivity(intent);
            }
        });

        cardViewVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Vegetables");
                startActivity(intent);
            }
        });

        cardViewOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Oil");
                startActivity(intent);

            }
        });

        cardViewBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Data ","*-*-*-*-*-");
                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Bakery");
                startActivity(intent);

            }
        });

        cardViewPooja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Pooja");
                startActivity(intent);

            }
        });

        cardViewBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Bread");
                startActivity(intent);
            }
        });

        cardViewbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this, AdminAddItems.class);
                intent.putExtra("category","Beauty");
                startActivity(intent);

            }
        });

        cardViewclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this,AdminAddItems.class);
                intent.putExtra("category","Clean");
                startActivity(intent);

            }
        });

        cardViewMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminAddProductsActivity.this, AddProductActivity.class);
                intent.putExtra("category","Medical");
                startActivity(intent);
            }
        });

        cardViewSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(AdminAddProductsActivity.this, AddProductActivity.class);
                intent.putExtra("category","Sweet");
                startActivity(intent);

            }
        });

        cardViewFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AdminAddProductsActivity.this, AddProductActivity.class);
                intent.putExtra("category","Fast");
                startActivity(intent);

            }
        });

    }

    public void initViews(){
        cardViewFruits = findViewById(R.id.fruitCategoryCardView);
        cardViewMilk = findViewById(R.id.milkCardView);
        cardViewVegetables = findViewById(R.id.vegetableCardView);
        cardViewOil = findViewById(R.id.oilAndMassaleCategoryCardView);
        cardViewBakery = findViewById(R.id.bakeryCardView);
        cardViewPooja = findViewById(R.id.poojaCardView);
        cardViewBread = findViewById(R.id.breadandeggsCategoryCardView);
        cardViewbeauty = findViewById(R.id.beautyCardView);
        cardViewclean = findViewById(R.id.cleaningCardView);
        cardViewMedical = findViewById(R.id.medicalCategoryCardView);
        cardViewSweet=findViewById(R.id.sweetCardView);
        cardViewFast=findViewById(R.id.fastCardView);




    }

}
