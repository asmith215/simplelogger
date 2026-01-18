package com.simplelogger.core;

import java.time.LocalDate;

public class Movie extends Entry {

    public Movie(){
        super();
        this.setType(Type.MOVIE);
    }


    public Movie(String name, LocalDate date, Double rating, String comment){
        super(Type.MOVIE,name,date,rating,comment);
    }

}

