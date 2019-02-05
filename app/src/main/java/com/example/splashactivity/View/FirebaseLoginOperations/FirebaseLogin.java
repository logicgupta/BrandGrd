package com.example.splashactivity.View.FirebaseLoginOperations;

import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseLogin {

    static boolean statusLogin;
    static boolean statusRegister;
    static FirebaseAuth firebaseAuth;

    FirebaseLogin(){

    }

    public static void initFirebaseInstance(Context context){
      /*  FirebaseOptions options=new FirebaseOptions.Builder()
                .setApplicationId("1:629215182951:android:d461937a0e182e4c")
                .setApiKey("AIzaSyBFwXn-RiyGoqO5w56V_KRd3ATtoIMc8cc")
                .build();

        FirebaseApp.initializeApp(context,options);*/
        firebaseAuth= FirebaseAuth.getInstance();
    }

    public static boolean loginFirebase(String email,String password){
        firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuth
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> task) {

                if (task.isSuccessful()){
                    statusLogin=true;
                }
                else {
                    statusLogin=false;
                }
            }
        });


        return statusLogin;
    }

    public static boolean signUpOperation(String email,String password){

        firebaseAuth= FirebaseAuth.getInstance();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            statusRegister=true;
                        }
                    }
                });
        return statusRegister;

    }

}
