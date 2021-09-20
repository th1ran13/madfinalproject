package com.example.madproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class VerifyEmail extends AppCompatActivity {

    Button btn_logout;
    TextView verifyEmail;
    Button btn_verify;
    FirebaseAuth auth;
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);

        auth = FirebaseAuth.getInstance();
        verifyEmail = findViewById(R.id.verifyEmail);
        btn_verify = findViewById(R.id.btn_verify);

        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext() , Login.class));
                finish();
            }
        });

        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Category.class));
            }
        });

        if(!auth.getCurrentUser().isEmailVerified()){
            btn_verify.setVisibility(View.VISIBLE);
            verifyEmail.setVisibility(View.VISIBLE);
//            startActivity(new Intent(getApplicationContext() , VerifyEmail.class));

        }

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send verification email
                auth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(VerifyEmail.this , "Check your Email" , Toast.LENGTH_SHORT).show();
                        btn_verify.setVisibility(View.GONE);
                        verifyEmail.setVisibility(View.GONE);
                        //startActivity(new Intent(getApplicationContext() , Login.class));
                    }
                });
            }
        });
    }
}