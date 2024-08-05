package com.example.e2;

import java.util.ArrayList;

public class User {
    private String name;
    private String password;
    private ArrayList<String> invitesList;
    public User(String name,String password){
        this.name=name;
        this.password=password;
        invitesList=new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<String> getInvitesList() {
        return invitesList;
    }

    public void setInvitesList(ArrayList<String> invitesList) {
        this.invitesList = invitesList;
    }
}
