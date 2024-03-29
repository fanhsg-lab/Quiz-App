package com.example.duolingo;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashActivity extends AppCompatActivity {

    private TextView appName;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appName= findViewById(R.id.app_name);

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.myanim);
        appName.setAnimation(anim);

        mAuth = FirebaseAuth.getInstance();

        //αρχικοποίηση της βάσης
        DbQuery.g_firestore = FirebaseFirestore.getInstance();

        new Thread(){

            @Override
            public void run() {

                // use alt + enter at sleep to create the try
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(mAuth.getCurrentUser() != null)
                {

                       DbQuery.loadData(new MyCompleteListener() {
                           @Override
                           public void onSuccess() {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    SplashActivity.this.finish();
                           }

                           @Override
                           public void onFailure() {
                Toast.makeText(SplashActivity.this,"Somethink went wrong ! Please Try Again",Toast.LENGTH_SHORT).show();

                           }
                       });



                }else
                {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
                SplashActivity.this.finish();
                }
            }
        }.start();


    }
}