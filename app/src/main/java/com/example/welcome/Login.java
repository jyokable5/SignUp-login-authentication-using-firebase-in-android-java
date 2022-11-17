package com.example.welcome;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login<view> extends AppCompatActivity {

    Button btnlogin;
    EditText etemail, etpwd;
    FirebaseAuth mAuth;
    TextView tvreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etemail = findViewById(R.id.etemail);
        etpwd = findViewById(R.id.etpwd);
        tvreg = findViewById(R.id.tvreg);
        btnlogin = findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(view -> {

            loginUser();

        });
        tvreg.setOnClickListener(view ->
        {
            startActivity(new Intent(Login.this, Signup.class));
        });
    }


    private void loginUser(){
        String email = etemail.getText().toString();
        String password = etpwd.getText().toString();

        if (TextUtils.isEmpty(email)){
            etemail.setError("Email cannot be empty");
            etemail.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            etpwd.setError("Password cannot be empty");
            etpwd.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else{
                        Toast.makeText(Login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}
