package com.example.madproject1.model;

import android.content.Intent;

public class BannerMovies {

    Integer id;
    String movieName;
    String imgUrl;

    public BannerMovies(Integer id, String movieName, String imgUrl) {
        this.id = id;
        this.movieName = movieName;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
