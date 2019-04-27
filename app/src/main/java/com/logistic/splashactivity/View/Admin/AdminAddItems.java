package com.logistic.splashactivity.View.Admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Client.ClientViewModel.ProductViewModel;

import java.io.IOException;

public class AdminAddItems extends AppCompatActivity {
    ImageView imageView1,imageView2,imageView3,imageView4;
    EditText editTextProductName,editTextProductDescription
            ,editTextProductOriginalPrice,editTextProductDiscountPrice,editTextQuantity,editTextPinCode,editTextQty;

    Button button;

    public static final int STATUS1=101;
    public static final int STATUS2=102;
    public static final int STATUS3=103;
    public static final int STATUS4=104;
    Uri uri1,uri2,uri3,uri4;
    String productName,productDescription;
    String productOriginalPrice,productDiscountPrice;
    String productQuantity;
    String category,pinCode,qty;
    Bundle bundle;
    ProgressDialog progressDialog;
    String pincode;

    ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_items);

        bundle=getIntent().getExtras();
        category=bundle.get("category").toString();
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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromEditText();
                progressDialog=new ProgressDialog(AdminAddItems.this);
                progressDialog.setMessage("Uploading Data ....");
                progressDialog.setCancelable(false);
                progressDialog.show();
                if (uri1==null){
                    Toast.makeText(AdminAddItems.this, "Please Add Product Image", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else if (uri2==null){
                    progressDialog.dismiss();
                }
                if (uri3==null){
                    Toast.makeText(AdminAddItems.this, "Please Add Product Image", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else if (uri4==null){
                    Toast.makeText(AdminAddItems.this, "Please Add Product Image", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else if (productName.equals("")){
                    progressDialog.dismiss();

                }else if (productDescription.equals("")){
                    progressDialog.dismiss();

                }
                else if (productOriginalPrice.equals("")){
                    progressDialog.dismiss();

                }
                else if (productDiscountPrice.equals("")){
                    progressDialog.dismiss();

                }
                else if (productQuantity.equals("")){
                    progressDialog.dismiss();

                }
                else if (qty.equalsIgnoreCase("")){
                    progressDialog.dismiss();
                }
                else {
                    Log.e("AdminAddItems Activty","Making Process ------");
                    viewModel= ViewModelProviders.of(AdminAddItems.this).get(ProductViewModel.class);

                    viewModel.addProduct(AdminAddItems.this,uri1.toString(),uri2.toString(),uri3.toString(),uri4.toString()
                            ,productName,productDescription,
                            productOriginalPrice
                            ,productDiscountPrice,productQuantity,pinCode
                            ,"BrandMore",qty,category);

                    viewModel.getStatus().observe(AdminAddItems.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {

                            if (aBoolean){
                                Toast.makeText(AdminAddItems.this, "Success ! ", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            else {
                                Toast.makeText(AdminAddItems.this, "Failed !", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });

                }
            }
        });
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
        pinCode=editTextPinCode.getText().toString();
        qty=editTextQty.getText().toString();
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
        editTextQuantity=findViewById(R.id.editTextQuatityAvailable);                     // Quantity At Store

        editTextPinCode=findViewById(R.id.editTextPinCode);
        editTextQty=findViewById(R.id.editquantity);


        button=findViewById(R.id.buttonSubmit);
    }


    public static Bitmap getScaledDownBitmap(Bitmap bitmap, int threshold, boolean isNecessaryToKeepOrig){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int newWidth = width;
        int newHeight = height;

        if(width > height && width > threshold){
            newWidth = threshold;
            newHeight = (int)(height * (float)newWidth/width);
        }

        if(width > height && width <= threshold){
            //the bitmap is already smaller than our required dimension, no need to resize it
            return bitmap;
        }

        if(width < height && height > threshold){
            newHeight = threshold;
            newWidth = (int)(width * (float)newHeight/height);
        }

        if(width < height && height <= threshold){
            //the bitmap is already smaller than our required dimension, no need to resize it
            return bitmap;
        }

        if(width == height && width > threshold){
            newWidth = threshold;
            newHeight = newWidth;
        }

        if(width == height && width <= threshold){
            //the bitmap is already smaller than our required dimension, no need to resize it
            return bitmap;
        }

        return getResizedBitmap(bitmap, newWidth, newHeight, isNecessaryToKeepOrig);
    }

    private static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight, boolean isNecessaryToKeepOrig) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        if(!isNecessaryToKeepOrig){
            bm.recycle();
        }
        return resizedBitmap;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == STATUS1 && resultCode == RESULT_OK && data != null) {
            uri1 = data.getData();
            try {
                Bitmap mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri1);
                Bitmap newBitmap=getScaledDownBitmap(mBitmap,500,false);
                imageView1.setImageBitmap(newBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (requestCode == STATUS2 && resultCode == RESULT_OK && data != null) {

            uri2 = data.getData();
            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
                Bitmap newBitmap=getScaledDownBitmap(mBitmap,500,false);
                imageView2.setImageBitmap(newBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode==STATUS3 && resultCode==RESULT_OK && data !=null){

            uri3 = data.getData();
            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri3);
                Bitmap newBitmap=getScaledDownBitmap(mBitmap,500,false);
                imageView3.setImageBitmap(newBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (requestCode==STATUS4 && resultCode==RESULT_OK && data !=null){

            uri4 = data.getData();
            Bitmap mBitmap = null;
            try {
                mBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri4);
                Bitmap newBitmap=getScaledDownBitmap(mBitmap,500,false);
                imageView4.setImageBitmap(newBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}
