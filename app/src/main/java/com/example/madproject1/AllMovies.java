package com.example.madproject1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AllMovies extends AppCompatActivity implements MovieItemClickListner{

    private RecyclerView MoviesAll;
    ViewPager viewPager;
    Button btnticket1;

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);
        MoviesAll = findViewById(R.id.all_movies);

//        btnticket1 = findViewById(R.id.btn_ticket);
//
//        btnticket1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //startActivity(new Intent(getApplicationContext() , ShowTickets.class));
//                openActivity();
//
//            }
//        });

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


        List<Movie> lstMovie = new ArrayList<>();
        lstMovie.add(new Movie("Snyder Cut JL" , "Bruce Wayne and Diana Prince try to bring the metahumans of Earth together after the death of Clark Kent. Meanwhile, Darkseid sends Steppenwolf to Earth with a vast army to subjugate humans" , R.drawable.zsjl , R.drawable.zack ));
        lstMovie.add(new Movie("Suicide Squad" ,"Assemble a team of the world's most dangerous, incarcerated Super Villains, provide them with the most powerful arsenal at the government's disposal, and send them off on a mission to defeat an enigmatic, insuperable entity. U.S. intelligence officer Amanda Waller has determined only a secretly convened group of disparate, despicable individuals with next to nothing to lose will do. ", R.drawable.suicidesquad , R.drawable.suicide));
        lstMovie.add(new Movie("Black Widow" ,"Natasha Romanoff, a former KGB spy, is shocked to find out that her ex handler, General Dreykov, is still alive. While evading capture by Taskmaster, she is forced to confront her dark past." ,  R.drawable.blackwidow , R.drawable.blackwidowbanner1));
        lstMovie.add(new Movie("Cruella" , "Estella is a young and clever grifter who's determined to make a name for herself in the fashion world. She soon meets a pair of thieves who appreciate her appetite for mischief, and together they build a life for themselves on the streets of London. ",R.drawable.cru , R.drawable.cruellabanner1));
        lstMovie.add(new Movie("Army of Dead" ,"After a zombie outbreak in Las Vegas, a group of mercenaries take the ultimate gamble and venture into the quarantine zone in hopes of pulling off an impossible heist.", R.drawable.aotd , R.drawable.aotdbanner));



        MovieAdapter movieAdapter = new MovieAdapter(this , lstMovie , this);
        MoviesAll.setAdapter(movieAdapter);
        MoviesAll.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));

        viewPager = (ViewPager)findViewById(R.id.myViewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {

        Intent intent = new Intent(this , MovieDetail.class);
        //send movie Information to the detailsActivity
        intent.putExtra("name" , movie.getTitle());
        intent.putExtra("imgURL" , movie.getThumbnail());
        intent.putExtra("imgCover" , movie.getCoverPhoto());
        intent.putExtra("description" , movie.getDescription());

        //animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AllMovies.this ,
                movieImageView , "sharedName");

        startActivity(intent , options.toBundle());


        //here we send movie information to detail activity
        Toast.makeText(this ,  movie.getTitle() , Toast.LENGTH_LONG ).show();
    }

    public void openActivity(){
        Intent intent = new Intent(this , ShowTickets.class);
        startActivity(intent);
    }


}