package com.logistic.splashactivity.View.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.logistic.splashactivity.R;

public class CategoryOfProductList extends AppCompatActivity {
    CardView cardViewFruits, cardViewMilk, cardViewVegetables;
    CardView cardViewOil, cardViewBakery, cardViewPooja;
    CardView cardViewBread, cardViewbeauty, cardViewclean;
    CardView cardViewMedical, cardViewSweet, cardViewFast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.logistic.splashactivity.R.layout.activity_category_of_product_list);
        initViews();
        cardViewFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Fruits");
                startActivity(intent);

            }
        });

        cardViewMilk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Milk");
                startActivity(intent);
            }
        });

        cardViewVegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Vegetables");
                startActivity(intent);
            }
        });

        cardViewOil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Oil");
                startActivity(intent);

            }
        });

        cardViewBakery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Bakery");
                startActivity(intent);

            }
        });

        cardViewPooja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Pooja");
                startActivity(intent);

            }
        });

        cardViewBread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Bread");
                startActivity(intent);
            }
        });

        cardViewbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Beauty");
                startActivity(intent);

            }
        });

        cardViewclean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this,ViewProductActivity.class);
                intent.putExtra("category","Clean");
                startActivity(intent);

            }
        });

        cardViewMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Medical");
                startActivity(intent);
            }
        });

        cardViewSweet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Sweet");
                startActivity(intent);

            }
        });

        cardViewFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoryOfProductList.this, ViewProductActivity.class);
                intent.putExtra("category","Fast");
                startActivity(intent);

            }
        });

    }

    public void initViews(){
        cardViewFruits = findViewById(com.logistic.splashactivity.R.id.fruitCategoryCardView);
        cardViewMilk = findViewById(com.logistic.splashactivity.R.id.milkCardView);
        cardViewVegetables = findViewById(com.logistic.splashactivity.R.id.vegetableCardView);
        cardViewOil = findViewById(com.logistic.splashactivity.R.id.oilAndMassaleCategoryCardView);
        cardViewBakery = findViewById(com.logistic.splashactivity.R.id.beautyCardView);
        cardViewPooja = findViewById(com.logistic.splashactivity.R.id.poojaCardView);
        cardViewBread = findViewById(com.logistic.splashactivity.R.id.breadandeggsCategoryCardView);
        cardViewbeauty = findViewById(com.logistic.splashactivity.R.id.beautyCardView);
        cardViewclean = findViewById(com.logistic.splashactivity.R.id.cleaningCardView);

        cardViewMedical = findViewById(R.id.medicalCategoryCardView);
        cardViewSweet=findViewById(R.id.sweetCardView);
        cardViewFast=findViewById(R.id.fastCardView);

    }

}
