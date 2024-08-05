package com.example.e2;

import java.util.Date;

public class Event {
    private String name;
    private String place;
    private String type;
    private Date date;
    private  boolean isSigned;
    private int numberOfSeats;
    private int[][] signedPlaces;

    public Event(){
        name=null;
        place=null;
        type=null;
        date=null;
        isSigned=false;
        numberOfSeats=0;
        signedPlaces=null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public java.sql.Date getDate() {
        return (java.sql.Date) date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public int[][] getSignedPlaces() {
        return signedPlaces;
    }

    public void setSignedPlaces(int[][] signedPlaces) {
        signedPlaces = signedPlaces;
    }
}
