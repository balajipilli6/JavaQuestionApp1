package com.example.javaquestionstestapp;

import static com.example.javaquestionstestapp.R.id.signup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView LoginEmail;
    Button signup;
    Button signin;
    TextView LoginPassword;
FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginPassword = findViewById(R.id.LoginPassword);
        LoginEmail = findViewById(R.id.LoginEmail);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_login);
        signin.setOnClickListener(view->
        {
            loginUser();
        });
        signup.setOnClickListener(view->
        {
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        });
    }

    private void loginUser() {
        String email=LoginEmail.getText().toString();
        String password=LoginPassword.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            LoginEmail.setError("Email cannot be Empty");
            LoginEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            LoginPassword.setError("Password cannot be empty");
            LoginPassword.requestFocus();
        }
        else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(LoginActivity.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}