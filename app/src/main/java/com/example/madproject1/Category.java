package com.example.madproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

public class Category extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Button btn_expressway;
    Button btn_train;
    Button btn_movie;
    Button btn_concert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext() , profile.class));
                        overridePendingTransition(0 ,0);
                        return true;
                    case R.id.tickets:
                        startActivity(new Intent(getApplicationContext() , ShowTickets.class));
                        return true;
                }
                return false;
            }
        });



        btn_movie = findViewById(R.id.btn_movie);
        btn_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , AllMovies.class));
            }
        });
    }
}