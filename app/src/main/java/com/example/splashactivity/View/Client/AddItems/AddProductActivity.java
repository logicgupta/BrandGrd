package com.example.splashactivity.View.Client.AddItems;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.splashactivity.R;

public class AddProductActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4;
    EditText editTextProductName,editTextProductDescription
            ,editTextProductOriginalPrice,editTextProductDiscountPrice,editTextQuantity;

    Button button;

    public static final int STATUS1=101;
    public static final int STATUS2=102;
    public static final int STATUS3=103;
    public static final int STATUS4=104;
    Uri uri1,uri2,uri3,uri4;
    String productName,productDescription;
    String productOriginalPrice,productDiscountPrice;
    String productQuantity;
    String category;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        bundle=getIntent().getExtras();
        category=bundle.getString("category");

        initView();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1(STATUS1);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1(STATUS2);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1(STATUS3);
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery1(STATUS4);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
                if (productName.isEmpty()){

                    Toast.makeText(AddProductActivity.this, "Please Enter Product Name !", Toast.LENGTH_SHORT).show();
                }
                else if (productDescription.isEmpty()){

                    Toast.makeText(AddProductActivity.this, "Please Enter Product Description !", Toast.LENGTH_SHORT).show();
                }
                else  if (productOriginalPrice.isEmpty()){

                    Toast.makeText(AddProductActivity.this, "Please Enter the Original Price !", Toast.LENGTH_SHORT).show();
                }
                else if (productDiscountPrice.isEmpty()){

                    Toast.makeText(AddProductActivity.this, "Please Enter Product Discount Price !", Toast.LENGTH_SHORT).show();
                }
                else if (productQuantity.isEmpty()){
                    Toast.makeText(AddProductActivity.this, "Please Enter the Product Quantity Available !", Toast.LENGTH_SHORT).show();

                }
                else if (uri1!=null){

                    Toast.makeText(AddProductActivity.this, "Please Select Image from Gallery", Toast.LENGTH_SHORT).show();

                }
                else if (uri2!=null){

                    Toast.makeText(AddProductActivity.this, "Please Enter Image from Gallery", Toast.LENGTH_SHORT).show();
                }
                else if (uri3!=null){

                    Toast.makeText(AddProductActivity.this, "Please Enter Image from Gallery", Toast.LENGTH_SHORT).show();

                }
                else if (uri4!=null){
                    Toast.makeText(AddProductActivity.this, "Please Enter Image from Gallery", Toast.LENGTH_SHORT).show();

                }
                else {



                }
            }
        });



    }

    public void initView(){
        imageView1=findViewById(R.id.imageUrl1);
        imageView2=findViewById(R.id.imageUrl2);
        imageView3=findViewById(R.id.imageUrl3);
        imageView4=findViewById(R.id.imageUrl4);

        editTextProductName=findViewById(R.id.editTextProductName);
        editTextProductDescription=findViewById(R.id.editTextProductDescription);
        editTextProductOriginalPrice=findViewById(R.id.editTextOriginalPrice);
        editTextProductDiscountPrice=findViewById(R.id.editTextDiscountPrice);
        editTextQuantity=findViewById(R.id.editTextQuatityAvailable);

        button=findViewById(R.id.buttonSubmit);
    }

    public void openGallery1(int requestCode){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent
                , "Select Picture"), requestCode);

    }

    public void getDataFromEditText(){

        productName=editTextProductName.getText().toString();
        productDescription=editTextProductDescription.getText().toString();
        productOriginalPrice=editTextProductOriginalPrice.getText().toString();
        productDiscountPrice=editTextProductDiscountPrice.getText().toString();
        productQuantity=editTextQuantity.getText().toString();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode==STATUS1 && resultCode==RESULT_OK && data !=null){
            uri1=data.getData();
            imageView1.setImageURI(uri1);
        }
        else {
            Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
        }
          if (requestCode==STATUS2 && resultCode==RESULT_OK && data !=null){

              uri2=data.getData();
              imageView2.setImageURI(uri2);

        }
          else {
              Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
          }
         if (requestCode==STATUS3 && resultCode==RESULT_OK && data !=null){

             uri3=data.getData();
             imageView3.setImageURI(uri3);
        }
         else {
             Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
         }
         if (requestCode==STATUS4 && resultCode==RESULT_OK && data !=null){

             uri4=data.getData();
             imageView4.setImageURI(uri4);
        }
        else {
            Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
