package com.example.madproject1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllMovies extends AppCompatActivity implements MovieItemClickListner{

    private RecyclerView MoviesAll;
    ViewPager viewPager;
    Button btn_ticket1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);
        MoviesAll = findViewById(R.id.all_movies);

        btn_ticket1 = findViewById(R.id.btn_ticket);

        btn_ticket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , ShowTickets.class));
                finish();
            }
        });


        List<Movie> lstMovie = new ArrayList<>();
        lstMovie.add(new Movie("Snyder Cut JL" , R.drawable.zsjl , R.drawable.zack));
        lstMovie.add(new Movie("Suicide Squad" , R.drawable.suicidesquad , R.drawable.suicide));
        lstMovie.add(new Movie("Black Widow" , R.drawable.blackwidow , R.drawable.blackwidowbanner1));
        lstMovie.add(new Movie("Cruella" , R.drawable.cru , R.drawable.cruellabanner1));
        lstMovie.add(new Movie("Army of Dead" , R.drawable.aotd , R.drawable.aotdbanner));



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
        intent.putExtra("name " , movie.getTitle());
        intent.putExtra("imgURL" , movie.getThumbnail());
        intent.putExtra("imgCover" , movie.getCoverPhoto());

        //animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AllMovies.this ,
                movieImageView , "sharedName");

        startActivity(intent , options.toBundle());


        //here we send movie information to detail activity
        Toast.makeText(this ,  movie.getTitle() , Toast.LENGTH_LONG ).show();
    }


}