package com.example.madproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;


public class ShowTickets extends AppCompatActivity {

    RecyclerView recyclerView;
    TicketAdapter ticketAdapter;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tickets);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.tickets);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext() , Category.class));
                        overridePendingTransition(0 ,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext() , profile.class));
                        overridePendingTransition(0 ,0);
                        return true;
                    case R.id.tickets:
                        return true;
                }
                return false;
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv_ticket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MovieTicketModel> options =
                new FirebaseRecyclerOptions.Builder<MovieTicketModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Tickets"), MovieTicketModel.class)
                        .build();

        ticketAdapter = new TicketAdapter(options);
        recyclerView.setAdapter(ticketAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        ticketAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        ticketAdapter.startListening();
    }
}