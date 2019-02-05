package com.example.splashactivity.View.Login;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashactivity.R;
import com.example.splashactivity.View.Admin.AdminPannel;
import com.example.splashactivity.View.Client.Clientpannel;
import com.example.splashactivity.View.FirebaseLoginOperations.FirebaseLogin;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextUsername,editTextEmailId,editTextMobileNumber,editTextPassword;
    Button buttonRegister;
     Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        openDialogLocation();

        initViews();
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseLogin.initFirebaseInstance(getApplicationContext());
                String name,emaiId,phone,password;
                name=editTextUsername.getText().toString();
                emaiId=editTextEmailId.getText().toString();
                phone=editTextMobileNumber.getText().toString();
                password=editTextPassword.getText().toString();

                if (name.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter your name", Toast.LENGTH_SHORT).show();

                }else if(emaiId.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter your Email ID", Toast.LENGTH_SHORT).show();

                }
                else if (phone.isEmpty()){

                    Toast.makeText(RegisterActivity.this, "Please Enter your mobile number", Toast.LENGTH_SHORT).show();


                }
                else if ((password.isEmpty())){
                    Toast.makeText(RegisterActivity.this, "Please Enter your password", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (FirebaseLogin.signUpOperation(emaiId, password)) {
                        Toast.makeText(RegisterActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        updateUI(true);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
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
    }
    public void updateUI(boolean st){
        if (st){
            if (editTextEmailId.getText().toString().endsWith("brandMore8963@gmail.com")){
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
}
