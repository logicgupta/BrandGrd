package com.logistic.splashactivity.View;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import com.logistic.splashactivity.R;

import com.logistic.splashactivity.View.Login.LoginActvity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String projectID = "brandnew-83b22";
        FirebaseOptions options=new FirebaseOptions.Builder()
                .setApplicationId("1:577433426395:android:d461937a0e182e4c")
                .setApiKey("AIzaSyALQOs5YKYCb7aE611waa7WfI5ekAfgkXI")
                .setDatabaseUrl("https://brandnew-83b22.firebaseio.com")
                .setProjectId(projectID)
                .setStorageBucket("brandnew-83b22.appspot.com")
                .build();

        FirebaseApp.initializeApp(getApplicationContext(),options);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(MainActivity.this, LoginActvity.class);
                startActivity(intent);
            }
        },2000);
    }

}
