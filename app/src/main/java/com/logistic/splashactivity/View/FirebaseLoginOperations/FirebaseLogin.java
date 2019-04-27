package com.logistic.splashactivity.View.FirebaseLoginOperations;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.lifecycle.MutableLiveData;

public class FirebaseLogin {

    MutableLiveData<Boolean> loginStatus=new MutableLiveData<>();
    MutableLiveData<Boolean> signUpStatus=new MutableLiveData<>();

    public FirebaseLogin(){


    }
    public void loginFirebase(String email,String password){

         final FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuth
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()){
                  loginStatus.setValue(true);
                    Log.e("Firebase Login","Firebase Login Success!");
                }
                else {

                    loginStatus.setValue(false);
                }
            }
        });

    }

    public MutableLiveData<Boolean> getLoginStatus(){
        return loginStatus;
    }


    public  void signUpOperation(String email,String password){


        Log.e("SignUp ","Starting");
       FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.e("Login ","Success");
                            signUpStatus.setValue(true);
                        }
                        else {

                            Log.e("Login ","Failed");
                            signUpStatus.setValue(false);
                        }
                    }
                });
    }
    public MutableLiveData<Boolean> getSignUpStatus(){
        return signUpStatus;
    }




}
