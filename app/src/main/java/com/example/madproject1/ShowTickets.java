package com.example.madproject1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class ShowTickets extends AppCompatActivity {

    RecyclerView recyclerView;
    TicketAdapter ticketAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_tickets);

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