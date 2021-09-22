package com.example.madproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btn_register , btn_login;
    EditText username  , password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Register.class));
            }
        });

        username = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract user data
                //validate
                if(username.getText().toString().isEmpty()){
                    username.setError("Email is missing");
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Password is missing");
                    return;
                }

                //data is valid
                //login user
//                firebaseAuth.signInWithEmailAndPassword(username.getText().toString() , password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        //login is success
//                        startActivity(new Intent(getApplicationContext() , VerifyEmail.class));
//                        finish();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Login.this , e.getMessage() , Toast.LENGTH_SHORT).show();
//                    }
//                });

                firebaseAuth.signInWithEmailAndPassword(username.getText().toString() , password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    startActivity(new Intent(getApplicationContext() , VerifyEmail.class));

                                }else{
                                    Toast.makeText(Login.this , "Failed" , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });




    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(FirebaseAuth.getInstance().getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext() , VerifyEmail.class));
//            finish();
//        }
//    }
}