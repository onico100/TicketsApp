package com.example.e2.models;

public class Order {
    private int user_id;
    private int event_id;
    private boolean sign;
    private int seatRow;
    private int seatNum;

    public Order(int user_id,int event_id){
        this.user_id=user_id;
        this.event_id=event_id;
        sign=false;
    }

    public Order(int user_id,int event_id,int row, int seaNum){
        this.user_id=user_id;
        this.event_id=event_id;
        sign=true;
        seatRow=row;
        this.seatNum=seaNum;
    }

    public int getEvent_id() {
        return event_id;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public boolean isSign() {
        return sign;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public int getUser_id() {
        return user_id;
    }
}
