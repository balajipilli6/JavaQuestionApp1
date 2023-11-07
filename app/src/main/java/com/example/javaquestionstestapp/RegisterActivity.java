package com.example.javaquestionstestapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
TextView RegEmail;
    TextView Regpassword;
    Button signup;
   Button signin;
   FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        RegEmail = findViewById(R.id.RegEmail);
        Regpassword = findViewById(R.id.Regpassword);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.Signin);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(View ->
        {
            createUser();
        });
        signin.setOnClickListener(view ->
        {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

        });



    }

    private void createUser()
    {
        String email=RegEmail.getText().toString();
        String password=Regpassword.getText().toString();
        if(TextUtils.isEmpty(email))
        {
           RegEmail.setError("Email cannot be Empty");
           RegEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
         Regpassword.setError("Password cannot be empty");
         Regpassword.requestFocus();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful())
                   {
                       Toast.makeText(RegisterActivity.this, "User registered Successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                   }
                   else {
                       Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                   }
                }
            });
        }
    }
}