package com.logistic.splashactivity.View.Admin;

import MyViewModel.CoverScreenImageViewModel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.logistic.splashactivity.R;

public class CoverImagesActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    EditText editTextTitle, editTextDescription;
    String title, desc;
    Uri uri;
    CoverScreenImageViewModel imageViewModel;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_images);
        progressDialog=new ProgressDialog(CoverImagesActivity.this);
        initViews();

        imageViewModel= ViewModelProviders.of(this)
                                    .get(CoverScreenImageViewModel.class);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTextFromEdiText();
                uploadImageDetails();

            }
        });


    }

    void initViews() {
        imageView = findViewById(R.id.addImages);
        editTextTitle = findViewById(R.id.editTextImageTitle);
        editTextDescription = findViewById(R.id.editTextImageDesc);
        button = findViewById(R.id.submitImages);
    }

    void getTextFromEdiText() {
        title = editTextTitle.getText().toString();
        desc = editTextDescription.getText().toString();
    }

    void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent
                , "Select Picture"), 1000);
    }

    void uploadImageDetails(){
        if (uri==null){
            Toast.makeText(this, "Please Select Image From Gallery !", Toast.LENGTH_SHORT).show();

        }
        else if (title.isEmpty()){

            Toast.makeText(this, "Please Enter Title", Toast.LENGTH_SHORT).show();

        }
        else if (desc.isEmpty()){
            Toast.makeText(this, "Please Enter Description !", Toast.LENGTH_SHORT).show();
        }
        else {
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Uploading Data ....");
            progressDialog.show();
            imageViewModel.addCoverImages(uri.toString(),title,desc,CoverImagesActivity.this);

            imageViewModel.getStatus().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if (aBoolean){
                        progressDialog.dismiss();
                        Toast.makeText(CoverImagesActivity.this, "Success !", Toast.LENGTH_SHORT).show();
                        finish();

                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(CoverImagesActivity.this, "Failed , Please Try Again !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });



        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1000 && resultCode== RESULT_OK && data != null) {
            uri = data.getData();
            imageView.setImageURI(uri);
        } else {
            Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}
