package com.example.messageapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SplashActivity extends AppCompatActivity {
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    Thread th;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.sp_progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        firebaseAuth=FirebaseAuth.getInstance();
        StartThread();

       if(firebaseAuth.getCurrentUser()!=null){
           Toast.makeText(this, "Yönlendiriliyorsunuz...", Toast.LENGTH_SHORT).show();
           progressBar.setVisibility(View.VISIBLE);
           th.start();

       }
       else{
           Toast.makeText(this, "Lütfen giriş yapınız..", Toast.LENGTH_SHORT).show();
       }


    }

    private void StartThread() {
        th =new Thread(){
            public void run() {
                try {
                    sleep(2000);
                    progressBar.setVisibility(View.VISIBLE);
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void LoginClick(View view) {
        Intent i =new Intent(this,LoginActivity.class);
        progressBar.setVisibility(View.VISIBLE);
        startActivity(i);
    }

    public void RegisterClick(View view) {
        Intent i =new Intent(this,RegisterActivity.class);
        progressBar.setVisibility(View.VISIBLE);
        startActivity(i);
    }



}