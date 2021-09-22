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
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    Button btn_login1;
    EditText registerName  , registerEmail , registerPassword;
    Button userRegisterBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerName = findViewById(R.id.et_name);
        registerEmail = findViewById(R.id.et_email1);
        registerPassword = findViewById(R.id.et_password1);
        userRegisterBtn = findViewById(R.id.btn_register1);

        fAuth = FirebaseAuth.getInstance();

        btn_login1 = findViewById(R.id.btn_login1);
        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , Login.class));
                finish();
            }
        });

        userRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //extract the data from the form
                String userName = registerName.getText().toString();
                String userEmail = registerEmail.getText().toString();
                String userPassword = registerPassword.getText().toString();

                if(userName.isEmpty()){
                    registerName.setError("User Name is Required");
                    return;
                }

                if(userEmail.isEmpty()){
                    registerEmail.setError("Email is Required");
                    return;
                }

                if(userPassword.isEmpty()){
                    registerPassword.setError("Password is Required");
                    return;
                }

                //Toast.makeText(Register.this , "Data Validated" , Toast.LENGTH_SHORT).show();

//                fAuth.createUserWithEmailAndPassword(userEmail , userPassword).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        //send user to next page
//                        startActivity(new Intent(getApplicationContext() , Login.class));
//                        finish();
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Register.this , e.getMessage() , Toast.LENGTH_SHORT).show();
//                    }
//                });

                fAuth.createUserWithEmailAndPassword(userEmail , userPassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull  Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    User user = new User(userName , userEmail , userPassword );

                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                                 if(task.isSuccessful()){
                                                     Toast.makeText(Register.this , "Successfully Registered " , Toast.LENGTH_SHORT).show();

                                                     startActivity(new Intent(getApplicationContext() , Login.class));
                                                 }else{
                                                     Toast.makeText(Register.this , "Failed " , Toast.LENGTH_SHORT).show();
                                                 }
                                        }
                                    });
                                }else{
                                    Toast.makeText(Register.this , "Failed " , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}