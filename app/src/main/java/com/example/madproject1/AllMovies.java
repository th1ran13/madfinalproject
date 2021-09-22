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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AllMovies extends AppCompatActivity implements MovieItemClickListner{

    private RecyclerView MoviesAll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);

        MoviesAll = findViewById(R.id.all_movies);


        List<Movie> lstMovie = new ArrayList<>();
        lstMovie.add(new Movie("Snyder Cut JL" , R.drawable.zsjl , R.drawable.zack));
        lstMovie.add(new Movie("Suicide Squad" , R.drawable.suicidesquad , R.drawable.suicide));
        lstMovie.add(new Movie("Black Widow" , R.drawable.blackwidow));
        lstMovie.add(new Movie("Cruella" , R.drawable.cru));
        lstMovie.add(new Movie("Army of Dead" , R.drawable.aotd));



        MovieAdapter movieAdapter = new MovieAdapter(this , lstMovie , this);
        MoviesAll.setAdapter(movieAdapter);
        MoviesAll.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false));



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