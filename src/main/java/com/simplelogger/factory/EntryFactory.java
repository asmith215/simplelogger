package com.simplelogger.factory;

import com.simplelogger.core.Entry;
import com.simplelogger.core.Show;
import com.simplelogger.core.Movie;

import java.time.LocalDate;

import com.simplelogger.core.Book;
import com.simplelogger.core.Other;
import com.simplelogger.core.Type;


public class EntryFactory {

    public static Entry createEntry(Type type, String name, LocalDate date, Double rating, String comment){
        return switch(type){
            case SHOW -> new Show(name,date,rating,comment);
            case MOVIE -> new Movie(name,date,rating,comment);
            case BOOK -> new Book(name,date,rating,comment);
            case OTHER -> new Other(name,date,rating,comment);
        };  
    }


}
