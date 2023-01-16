package com.example.messageapp3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText name,password;
    TextView login,register;
    ProgressBar progress;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=findViewById(R.id.lg_et_email);
        password=findViewById(R.id.lg_et_password);
        login=findViewById(R.id.lg_tv_login);
        register=findViewById(R.id.lg_et_register);
        progress = findViewById(R.id.lg_progressBar);
        progress.setVisibility(View.INVISIBLE);
        firebaseAuth=FirebaseAuth.getInstance();

    }

    public void RegisterClick(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        progress.setVisibility(View.VISIBLE);
        startActivity(i);
    }

    public void signupClick(View view) {
        String userEmail = name.getText().toString();
        String userPassword = password.getText().toString();

        if(userEmail.isEmpty()){
            Toast.makeText(this, "Lütfen email adresinizi giriniz.", Toast.LENGTH_SHORT).show();
        }
        if(userPassword.isEmpty()|| password.length()<6){
            Toast.makeText(this, "Lütfen parolanızı giriniz.", Toast.LENGTH_SHORT).show();
        }
        firebaseAuth.signInWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  Toast.makeText(LoginActivity.this, "Giriş Başarılı ", Toast.LENGTH_SHORT).show();
                  progress.setVisibility(View.VISIBLE);

                  Intent i= new Intent(LoginActivity.this,MainActivity.class);
                  startActivity(i);
              }
              else{
                  Toast.makeText(LoginActivity.this, "Giriş Başarısız ", Toast.LENGTH_SHORT).show();
              }
            }
        });

    }
}