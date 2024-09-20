package com.example.e2.models;

public class Review {

    private String eventName;
    private String content;
    private int rating;

    public Review(String eventName, String content, int rating) {
        this.eventName = eventName;
        this.content = content;
        this.rating = rating;

        System.out.println("");

    }



    public String getEventName() {
        return eventName;
    }

    public String getContent() {
        return content;
    }

    public int getRating() {
        return rating;
    }
}
