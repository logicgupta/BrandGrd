package com.logistic.splashactivity.View.Admin;

import Model.OffersList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.AdminAdapters.offerListAdapters;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSpecialDeals extends AppCompatActivity {

    ImageView imageView;
    EditText editTextTitle,editTextDesc;
    Button button;
     Uri uri;
    FirebaseFirestore firestore;
    FirebaseStorage storage;
    CollectionReference collectionReference;
    String downloadUrl;
    ProgressDialog progressDialog;
    StorageReference storageReference,storageReference2;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    offerListAdapters adapter;
    Dialog dialog;
    List<OffersList> offersLists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_special_deals);
        initViews();
        getData();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog=new Dialog(AddSpecialDeals.this);
                dialog.setContentView(R.layout.add_offers_dialog);
                dialog.setCancelable(false);
                dialog.show();
                initDilog();

            }
        });


    }
    public void initViews(){
        recyclerView=findViewById(R.id.recyclerViewOffers);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddSpecialDeals.this));

    }

    public void getData(){
        firestore= FirebaseFirestore.getInstance();
        collectionReference=firestore
                .collection("BrandMore")
                .document("AdminOffers")
                .collection("Offer1");
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){

                    for (QueryDocumentSnapshot documentSnapshot:task.getResult()){

                        Log.e("Data ",""+documentSnapshot);
                        OffersList offers=new OffersList();
                        offers.setOfferKey(documentSnapshot.getId());
                        offers.setImageUrl(documentSnapshot.getString("imageUrl"));
                        offers.setTitle(documentSnapshot.getString("title"));
                        offers.setDesc(documentSnapshot.getString("desc"));
                        offersLists.add(offers);
                    }
                    adapter=new offerListAdapters(AddSpecialDeals.this,recyclerView,offersLists);
                    recyclerView.setAdapter(adapter);
                }
                else {
                    Log.e("Data ","Failed ==-*-*-*-*-*-*-*-*-*-*");
                }
            }
        });
    }

    public void initDilog(){
        imageView=dialog.findViewById(R.id.addImage);
        editTextTitle=dialog.findViewById(R.id.titleEditText);
        editTextDesc=dialog.findViewById(R.id.descEditText);
        button=dialog.findViewById(R.id.offerButton);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTitle.getText().equals("")){
                    Toast.makeText(AddSpecialDeals.this, "Fill Title !", Toast.LENGTH_SHORT).show();
                }
                else if (editTextDesc.getText().equals("")){
                    Toast.makeText(AddSpecialDeals.this, "Fill Description !", Toast.LENGTH_SHORT).show();
                }
                else if (uri.equals("")){

                }else {
                    progressDialog=new ProgressDialog(AddSpecialDeals.this);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Saving ....");
                    progressDialog.show();
                    uploadImage();
                }

            }
        });
    }
    void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent
                , "Select Picture"), 1000);
    }
    public void saveData(){
        firestore= FirebaseFirestore.getInstance();
        collectionReference=firestore
                .collection("BrandMore")
                .document("AdminOffers")
                .collection("Offer1");
        Map<String,String> map=new HashMap<>();
        map.put("imageUrl",downloadUrl);
        map.put("title",editTextTitle.getText().toString());
        map.put("desc",editTextDesc.getText().toString());
        collectionReference.add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
                    progressDialog.dismiss();
                    Toast.makeText(AddSpecialDeals.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.dismiss();
                    progressDialog.dismiss();
                    Toast.makeText(AddSpecialDeals.this, "Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void uploadImage(){
        firestore= FirebaseFirestore.getInstance();
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        storageReference2=storageReference.child("Images/Admin/Offer/"+System.currentTimeMillis()+"."+getImageExtension(uri));
        Log.e("Tag","Firestoagra kjbvvjkb  101");
        storageReference2
                .putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Log.e("Tag","Firestoagra kjbvvjkb Success 102");
                        storageReference2
                                .getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                       downloadUrl=uri.toString();
                                       saveData();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("Tag","Firestore Error",e);

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
            }
        });

    }
    public String getImageExtension(Uri uri){

        ContentResolver contentResolver=this.getApplicationContext().getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1000 && resultCode== RESULT_OK && data != null) {
            uri = data.getData();
            if (uri!=null){
                uploadImage();
            }
            imageView.setImageURI(uri);
        } else {
            Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }



}
