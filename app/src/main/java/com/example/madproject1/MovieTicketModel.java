package com.example.madproject1;

import java.util.Date;

public class MovieTicketModel {

    String movieName ;
    //String userName;
    String theater;
    String noOfTickets;
    String time;
    String movieDate;
    int Price;

    MovieTicketModel(){}

    public MovieTicketModel(String movieName, String time, String movieDate) {
        this.movieName = movieName;
        this.time = time;
        this.movieDate = movieDate;
    }

    public MovieTicketModel(String movieName, String theater, String noOfTickets, String time, String movieDate, int price) {
        this.movieName = movieName;
        this.theater = theater;
        this.noOfTickets = noOfTickets;
        this.time = time;
        this.movieDate = movieDate;
        Price = price;
    }

    //================================= uncomment
//    public MovieTicketModel(String movieName, String time, String movieDate) {
//        this.movieName = movieName;
//        this.time = time;
//        this.movieDate = movieDate;
//    }
//
//    public MovieTicketModel(String movieName, String userName, String noOfTickets, String time, String movieDate, int price) {
//        this.movieName = movieName;
//        this.userName = userName;
//        this.noOfTickets = noOfTickets;
//        this.time = time;
//        this.movieDate = movieDate;
//        Price = price;
//    }
    //================================= uncomment

//    public MovieTicketModel(String movieName, String userName, String noOfTickets, String movieDate, int price) {
//        this.movieName = movieName;
//        this.userName = userName;
//        this.noOfTickets = noOfTickets;
//        this.movieDate = movieDate;
//        Price = price;
//    }
//
//    public MovieTicketModel(String movieName, String userName, String noOfTickets, String movieDate) {
//        this.movieName = movieName;
//        this.userName = userName;
//        this.noOfTickets = noOfTickets;
//        this.movieDate = movieDate;
//    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }

    public String getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(String noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public String  getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(String movieDate) {
        this.movieDate = movieDate;
    }
}
