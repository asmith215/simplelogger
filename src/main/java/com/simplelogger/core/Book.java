package com.simplelogger.core;

import java.time.LocalDate;

public class Book extends Entry {

    public Book(){
        super();
        this.setType(Type.BOOK);
    }

    public Book(String name, LocalDate date, Double rating, String comment){
        super(Type.BOOK,name,date,rating,comment);
    }

}
