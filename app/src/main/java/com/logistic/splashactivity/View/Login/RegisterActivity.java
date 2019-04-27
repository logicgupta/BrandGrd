package com.logistic.splashactivity.View.Login;

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
import android.widget.TextView;
import android.widget.Toast;

import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Admin.AdminPannel;
import com.logistic.splashactivity.View.Client.Clientpannel;
import com.logistic.splashactivity.View.FirebaseLoginOperations.FirebaseLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername,editTextEmailId,editTextMobileNumber
            ,editTextPassword,editTextAddress;
    Button buttonRegister;
     Dialog dialog;
     CircleImageView circleImageView;
     String address;
     Uri imageUri;
     FirebaseFirestore firestore;
     StorageReference storageReference,storageReference2;
     CollectionReference collectionReference;
     String downloadUrl;
     String name;
     String emailId;
     ProgressDialog progressDialog;
     FirebaseStorage storage;
     String phoneNumber;
     String password;
    TextView registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openDialogLocation();

        initViews();

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,RegisterDeliverActivity.class));
            }
        });


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name=editTextUsername.getText().toString();
                emailId=editTextEmailId.getText().toString();
                phoneNumber=editTextMobileNumber.getText().toString();
                password=editTextPassword.getText().toString();
                address=editTextAddress.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter your name", Toast.LENGTH_SHORT).show();

                }else if(emailId.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter your Email ID", Toast.LENGTH_SHORT).show();

                }
                else if (phoneNumber.isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Please Enter your mobile number", Toast.LENGTH_SHORT).show();


                }
                else if ((password.isEmpty())){
                    Toast.makeText(RegisterActivity.this, "Please Enter your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    final ProgressDialog dialog=new ProgressDialog(RegisterActivity.this);
                    dialog.setMessage("Creating Account ...");
                    dialog.setCancelable(false);
                    dialog.show();

                    FirebaseLogin firebaseLogin=new FirebaseLogin();
                    firebaseLogin.signUpOperation(emailId, password);
                    firebaseLogin.getSignUpStatus().observe(RegisterActivity.this, new Observer<Boolean>() {
                        @Override
                        public void onChanged(Boolean aBoolean) {
                            if (aBoolean) {
                                Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                progressDialog=new ProgressDialog(RegisterActivity.this);
                                progressDialog.setMessage("Loading ...");
                                progressDialog.setCancelable(false);
                                progressDialog.show();
                                uploadImage();
                            } else {
                                dialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }

    public void initViews(){

        editTextUsername=findViewById(R.id.userNameEditText);
        editTextEmailId=findViewById(R.id.emailEdiText);
        editTextMobileNumber=findViewById(R.id.mobileEdiText);
        editTextPassword=findViewById(R.id.passwordEdiText);
        buttonRegister=findViewById(R.id.registerButton);
        circleImageView=findViewById(R.id.addPhoto);
        editTextAddress=findViewById(R.id.addressEdiText);
        registerButton=findViewById(R.id.register);
    }
    public void updateUI(boolean st){
        if (st){
            if (editTextEmailId.getText().toString().equalsIgnoreCase("brandMore8963@gmail.com")){
                startActivity(new Intent(this, AdminPannel.class));
                finish();
            }
            else {
                startActivity(new Intent(this, Clientpannel.class));
                finish();
            }

        }

    }
    public void openDialogLocation(){
        dialog=new Dialog(RegisterActivity.this);

        dialog.setContentView(R.layout.pin_code_search_product_layout);

        dialog.setCancelable(false);
        dialog.getWindow().getAttributes().windowAnimations=R.style.DialogAnimation_1;
        final EditText editTextPinCode=dialog.findViewById(R.id.pincodeEdiText);
        Button button=dialog.findViewById(R.id.continuePinCodeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPinCode.getText().toString().isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter PinCode To Check Service", Toast.LENGTH_SHORT).show();
                }
                else {
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent
                , "Select Picture"), 1000);
    }

    public String getImageExtension(Uri uri){

        ContentResolver contentResolver=this.getApplicationContext().getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1000 && resultCode== RESULT_OK && data != null) {
            imageUri = data.getData();

            circleImageView.setImageURI(imageUri);
        } else {
            Toast.makeText(this, "Please Select Image !", Toast.LENGTH_SHORT).show();
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void uploadImage(){
        firestore= FirebaseFirestore.getInstance();
        storage= FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        storageReference2=storageReference.child("Images/Owner/UserPhoto/"+System.currentTimeMillis()+"."+getImageExtension(imageUri));
        Log.e("Tag","Firestoagra kjbvvjkb  101");
        storageReference2
                .putFile(imageUri)
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
    public void saveData()
    {
        firestore= FirebaseFirestore.getInstance();
        collectionReference=firestore
                .collection("BrandMore")
                .document("Shop")
                .collection(FirebaseAuth.getInstance().getUid());
        final Map<String,String> map=new HashMap<>();
        map.put("imageUrl",downloadUrl);
        map.put("name",name);
        map.put("emailId",emailId);
        map.put("phoneNumber",phoneNumber);
        map.put("address",address);
        collectionReference.add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){

                    CollectionReference collectionReference1=firestore
                            .collection("BrandMore")
                            .document("Shop").collection("AuthIds");
                    Map<String,String> map1=new HashMap<>();
                    map1.put("authId",FirebaseAuth.getInstance().getUid());
                    map1.put("name",name);
                    collectionReference1.add(map1).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                updateUI(true);
                                progressDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                     }
                else {
                    dialog.dismiss();
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
