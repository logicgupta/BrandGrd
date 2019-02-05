package com.example.splashactivity.View;

import android.content.Intent;
import android.os.Handler;

import android.os.Bundle;

import com.example.splashactivity.R;

import com.example.splashactivity.View.Login.LoginActvity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String projectID = "brandmore-532eb";
        FirebaseOptions options=new FirebaseOptions.Builder()
                .setApplicationId("1:629215182951:android:d461937a0e182e4c")
                .setApiKey("AIzaSyBFwXn-RiyGoqO5w56V_KRd3ATtoIMc8cc")
                .setDatabaseUrl("https://brandmore-532eb.firebaseio.com")
                .setProjectId(projectID)
                .setStorageBucket("brandmore-532eb.appspot.co")
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
