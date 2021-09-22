package com.example.madproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.AllPermission;

public class Category extends AppCompatActivity {

    Button btn_expressway;
    Button btn_train;
    Button btn_movie;
    Button btn_concert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

    }
}