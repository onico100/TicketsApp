package com.example.e2.models;

import java.time.LocalDate;
import java.util.Date;

public class Event {

    private int ID;
    private String name;
    private String place;
    private String type;
    private LocalDate date;
    private  boolean isSigned;
    private int numberOfSeats;
    private int[][] signedPlaces;
    private int user_id;

    private int cost;

    public Event(){
        name=null;
        place=null;
        type=null;
        date=null;
        isSigned=false;
        numberOfSeats=0;
        signedPlaces=null;
    }

    public Event(int eventId, String eventName, String eventPlace, String eventType, LocalDate eventDate, boolean isSigne, int userId,int numberOfSeats,int eventCost) {
        ID=eventId;
        name=eventName;
        place=eventPlace;
        type=eventType;
        date=eventDate;
        isSigned=isSigne;
        user_id=userId;
        cost=eventCost;
        this.numberOfSeats=numberOfSeats;
        signedPlaces=null;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    public LocalDate getDate() {
        return  date;
    }

    public void setDate(LocalDate date) {

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

    public void setSignedPlaces(int[][] signedPlace) {
        signedPlaces = signedPlace;
    }

    public int getID() {
        return this.ID;
    }
}
