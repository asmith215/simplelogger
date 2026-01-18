package com.simplelogger.core;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
    @JsonSubTypes.Type(value = Book.class, name = "Book"),
    @JsonSubTypes.Type(value = Show.class, name = "Show"),
    @JsonSubTypes.Type(value = Other.class, name = "Other")
})

public abstract class Entry{
    
    @JsonIgnore        
    private Type type;


    private String name;
    private LocalDate date;
    private Double rating;
    private String comment;

    public Entry(){}

    public Entry(Type type, String name, LocalDate date, Double rating, String comment){
        this.type = type;
        this.name = name;
        this.date = date;
        this.rating = rating;
        this.comment = comment;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString(){
        String out = "";
        out += "Type: " + this.type + "\n" + "Name: " + this.name + "\nDate Finished: " + this.date + "\nRating: " + this.rating + "\nComments: " + this.comment + "\n";
        return out;
    }



}