package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    Button btnreg;
    EditText etemail,etpwd;
    FirebaseAuth mAuth;
    TextView tvlogin;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etemail = findViewById(R.id.etemail);
        etpwd = findViewById(R.id.etpwd);
        tvlogin = findViewById(R.id.tvreg);
        btnreg = findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();

        btnreg.setOnClickListener(view ->{
            createUser();
        });

        tvlogin.setOnClickListener(view ->{
            startActivity(new Intent(Signup.this, Login.class));
        });
    }

    private void createUser(){
        String email = etemail.getText().toString();
        String password = etpwd.getText().toString();

        if (TextUtils.isEmpty(email)){
            etemail.setError("Email cannot be empty");
            etemail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etpwd.setError("Password cannot be empty");
            etpwd.requestFocus();
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Signup.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(Signup.this, OtpverificationPage.class));
                    }else{
                        Toast.makeText(Signup.this, "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
