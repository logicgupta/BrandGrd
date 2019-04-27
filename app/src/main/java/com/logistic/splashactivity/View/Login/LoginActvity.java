package com.logistic.splashactivity.View.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.logistic.splashactivity.R;
import com.logistic.splashactivity.View.Admin.AdminPannel;
import com.logistic.splashactivity.View.Client.Clientpannel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.logistic.splashactivity.View.DeliveryBoy.HomeActivty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActvity extends AppCompatActivity {


    EditText editTextEmail,editTextPassword;
    Button buttonlogin;
    TextView textViewForgotPassword;
    TextView textViewRegister;
    ProgressDialog dialog;
    String email,password;
     FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);

        firestore=FirebaseFirestore.getInstance();

        initViews();

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showProgressDialog();
                email=editTextEmail.getText().toString();
                password=editTextPassword.getText().toString();
                if(email.isEmpty()){

                    Toast.makeText(LoginActvity.this, "Please Enter EmailId !" , Toast.LENGTH_SHORT).show();
                }
                else if (password.isEmpty()){

                    Toast.makeText(LoginActvity.this, "Please Enter Password !", Toast.LENGTH_SHORT).show();
                }
                else {

                    final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

                    firebaseAuth
                            .signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(Task<AuthResult> task) {

                                    if (task.isSuccessful()){

                                        SharedPreferences preferences=getApplicationContext()
                                                .getSharedPreferences("userType",0);
                                        SharedPreferences.Editor editor=preferences.edit();
                                        editor.putString("Type",email);

                                        editor.commit();
                                        updateUI(true);
                                        Toast.makeText(LoginActvity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                                        Log.e("Firebase Login","Firebase Login Success!");
                                    }
                                    else {
                                        updateUI(false);
                                        Log.e("Firebase Login","Firebase Login Failed!");
                                    }
                                }
                            });

                }

            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.forgot_password);
                final EditText emailEdit=findViewById(R.id.reset_email);
                Button button=findViewById(R.id.btn_reset_password);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (emailEdit.getText().toString().isEmpty()){
                            emailEdit.setError("Please Enter Email ID to reset Password !");
                        }
                        else {
                            FirebaseAuth.getInstance()
                                    .sendPasswordResetEmail(emailEdit.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(LoginActvity.this, " Success ,Please Check out Email ID  !", Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(),LoginActvity.class));
                                                finish();

                                            }
                                            else {
                                                Toast.makeText(LoginActvity.this, "Failed !", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });



                        }
                    }
                });

            }
        });
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActvity.this,RegisterActivity.class));
            }
        });

    }

    public void initViews(){
        editTextEmail=findViewById(R.id.userNameEditText);
        editTextPassword=findViewById(R.id.passwordEdiText);
        buttonlogin=findViewById(R.id.loginButton);
        textViewForgotPassword=findViewById(R.id.forgotPasswordTextView);
        textViewRegister=findViewById(R.id.signUpTextView);
    }

    public void showProgressDialog(){

        dialog=new ProgressDialog(LoginActvity.this);
        dialog.setMessage("Logging ...");
        dialog.setCancelable(false);
        dialog.show();

    }
    public void updateUI(boolean st){
        if (st){
            if (editTextEmail.getText().toString().equalsIgnoreCase("brandmore8963@gmail.com")){
               dialog.dismiss();
                startActivity(new Intent(this, AdminPannel.class));
                finish();
            }
            else {

               CollectionReference collectionReference=firestore
                        .collection("BrandMore")
                        .document("Deliver")
                        .collection(FirebaseAuth.getInstance().getUid());

               collectionReference
                       .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                   @Override
                   public void onComplete(@NonNull Task<QuerySnapshot> task) {

                       if (task.isSuccessful()){

                           for(QueryDocumentSnapshot snapshot:task.getResult()){
                               if (snapshot.getString("name")!=null){
                                   SharedPreferences preferences=getApplicationContext()
                                           .getSharedPreferences("userType",0);
                                   SharedPreferences.Editor editor=preferences.edit();
                                   editor.putString("Type",email);
                                   editor.putString("userType","Deliver");
                                   editor.commit();
                                   startActivity(new Intent(LoginActvity.this, HomeActivty.class));
                                   dialog.dismiss();
                                   finish();
                               }
                           }
                           Log.e("vjhbbvj","*-*-*-*-*-*-*-*");
                       }
                   }
               });

               CollectionReference collectionReference2=firestore
                        .collection("BrandMore")
                        .document("Shop")
                        .collection(FirebaseAuth.getInstance().getUid());
                collectionReference2
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            SharedPreferences preferences=getApplicationContext()
                                    .getSharedPreferences("userType",0);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("Type",email);
                            editor.putString("userType","Seller");
                            editor.commit();
                            dialog.dismiss();
                            startActivity(new Intent(LoginActvity.this, Clientpannel.class));
                            finish();
                            Log.e("vjhbbvj","*+***+*+**+*+*++*");
                        }
                    }
                });

            }

        }
        else {
                dialog.dismiss();
            Toast.makeText(LoginActvity.this, "Failed !", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
       if (FirebaseAuth.getInstance().getCurrentUser()!=null){
           SharedPreferences preferences=getApplicationContext()
                   .getSharedPreferences("userType",0);
                String eid=preferences.getString("Type",null);
           String userType=preferences.getString("userType",null);
           if(eid.equalsIgnoreCase("brandmore8963@gmail.com")){
               if (FirebaseAuth.getInstance().getCurrentUser().getEmail().equalsIgnoreCase("brandmore8963@gmail.com"))
                   startActivity(new Intent(LoginActvity.this, AdminPannel.class));
               else {

                   if (userType.equalsIgnoreCase("Seller")){
                       startActivity(new Intent(this, Clientpannel.class));
                       finish();
                   }
                   else if (userType.equalsIgnoreCase("Deliver")){
                       startActivity(new Intent(this, HomeActivty.class));
                       finish();
                   }
               }
           }
           else {
               if (userType.equalsIgnoreCase("Seller")){
                   startActivity(new Intent(this, Clientpannel.class));
                   finish();
               }
               else if (userType.equalsIgnoreCase("Deliver")){
                        startActivity(new Intent(this, HomeActivty.class));
                        finish();
               }
               else {

               }
           }

       }


    }
}
