package com.simplelogger.core;

import java.time.LocalDate;

public class Show extends Entry {

    public Show(){
        super();
        this.setType(Type.SHOW);
    }

    public Show(String name, LocalDate date, Double rating, String comment){
        super(Type.SHOW,name,date,rating,comment);
    }

}
