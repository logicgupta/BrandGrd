package com.example.splashactivity.View.Admin;

import MyViewModel.CoverScreenImageViewModel;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.splashactivity.R;

public class CoverImagesActivity extends AppCompatActivity {

    ImageView imageView;
    Button button;
    EditText editTextTitle, editTextDescription;
    String title, desc;
    Uri uri;
    CoverScreenImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_images);

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
            imageViewModel.addCoverImages(uri.toString(),title,desc,CoverImagesActivity.this);

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
