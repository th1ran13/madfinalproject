package com.example.madproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {

    private ImageView MovieThumnailImg , MovieCoverImg;
    private TextView tv_title , tv_description ;
    Button btn_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        btn_pay = findViewById(R.id.btn_pay);
        btn_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext() , EnterTicketDetail.class));
            }
        });

        //iniViews
        iniViews();


    }

    void iniViews(){

        //get the data
        String movieTitle = getIntent().getExtras().getString("name");
        int imageResource = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");

        MovieThumnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResource).into(MovieThumnailImg);
        MovieThumnailImg.setImageResource(imageResource);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);

        Glide.with(this).load(imagecover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        //getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(R.id.detail_movie_desc);

    }
}