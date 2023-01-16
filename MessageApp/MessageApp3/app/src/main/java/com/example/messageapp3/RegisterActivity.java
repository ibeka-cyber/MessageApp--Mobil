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

import com.example.messageapp3.Models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
    EditText name,mail,password;
    TextView login,register;
    ProgressBar progress;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.rg_et_name);
        mail=findViewById(R.id.rg_et_mail);
        password=findViewById(R.id.rg_et_password);
        login = findViewById(R.id.rg_et_login);
        register=findViewById(R.id.rg_et_register);
        progress=findViewById(R.id.rg_progressBar);
        progress.setVisibility(View.INVISIBLE);
        firebaseAuth= FirebaseAuth.getInstance();
    }

    public void LoginClick(View view) {
        Intent i = new Intent(this,LoginActivity.class);
        progress.setVisibility(View.VISIBLE);
        startActivity(i);
    }

    public void SignupClick(View view) {
        String userName =name.getText().toString();
        String userEmail =mail.getText().toString();
        String userPassword =password.getText().toString();

        if(userName.isEmpty()){
            Toast.makeText(this, "Lütfen bir kullanıcı adı giriniz.", Toast.LENGTH_SHORT).show();
        }
        if(userEmail.isEmpty()){
            Toast.makeText(this, "Lütfen bir kullanıcı email adresi giriniz.", Toast.LENGTH_SHORT).show();
        }
        if(userPassword.isEmpty()|| password.length()<6){
            Toast.makeText(this, "Lütfen geçerli bir parola giriniz.", Toast.LENGTH_SHORT).show();
        }

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    UserModel um = new UserModel(userName,userEmail,userPassword);
                    progress.setVisibility(View.VISIBLE);
                    String uid = Objects.requireNonNull(task.getResult().getUser()).getUid();
                    Toast.makeText(RegisterActivity.this,"Kayıt Başarılı!", Toast.LENGTH_SHORT).show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users").child(uid);

                    myRef.setValue(um);
                    myRef.setValue("Hello, World!");

                    Intent i= new Intent(RegisterActivity.this,SplashActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(RegisterActivity.this,"Kayıt Başarısız!", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}