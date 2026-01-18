package com.simplelogger.core;

import java.time.LocalDate;

public class Other extends Entry{

    public Other(){
        super();
        this.setType(Type.OTHER);
    }

    public Other(String name, LocalDate date, Double rating, String comment){
        super(Type.OTHER,name,date,rating,comment);
    }

}
