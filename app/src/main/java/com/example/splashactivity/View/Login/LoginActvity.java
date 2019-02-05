package com.example.splashactivity.View.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.splashactivity.R;
import com.example.splashactivity.View.Admin.AdminPannel;
import com.example.splashactivity.View.Client.Clientpannel;
import com.example.splashactivity.View.FirebaseLoginOperations.FirebaseLogin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActvity extends AppCompatActivity {


    EditText editTextEmail,editTextPassword;
    Button buttonlogin;
    TextView textViewForgotPassword;
    TextView textViewRegister;
    ProgressDialog dialog;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actvity);

        initViews();

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseLogin.initFirebaseInstance(getApplicationContext());
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

                    if (FirebaseLogin.loginFirebase(email,password)){
                        updateUI(true);
                        Toast.makeText(LoginActvity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        updateUI(false);

                    }

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
            if (editTextEmail.getText().toString().endsWith("brandmore8963@gmail.com")){
               dialog.dismiss();
                startActivity(new Intent(this, AdminPannel.class));
                finish();
            }
            else {
                dialog.dismiss();
                startActivity(new Intent(this, Clientpannel.class));
                finish();
            }

        }
        else {
                dialog.dismiss();
            Toast.makeText(LoginActvity.this, "Failed !", Toast.LENGTH_SHORT).show();
        }

    }
}
