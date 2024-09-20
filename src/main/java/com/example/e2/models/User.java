package com.example.e2.models;

public class User {
    private final String userName;
    private final String password;
    private int id;
    private String creditCardOwnerId;  // Integer type for ID
    private String creditCardNumber;  // Long number for card number
    private int creditCardCVC;      // Integer type for CVC
    private String creditCardValidity;  // Month/Year string

    public User(String name, String password) {
        this.userName = name;
        this.password = password;
        this.id = 0;
    }

    // Getters and Setters for credit card details
    public String getCreditCardOwnerId() {
        return creditCardOwnerId;
    }

    public void setCreditCardOwnerId(String creditCardOwnerId) {
        this.creditCardOwnerId = creditCardOwnerId;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCreditCardCVC() {
        return creditCardCVC;
    }

    public void setCreditCardCVC(int creditCardCVC) {
        this.creditCardCVC = creditCardCVC;
    }

    public String getCreditCardValidity() {
        return creditCardValidity;
    }

    public void setCreditCardValidity(String creditCardValidity) {
        this.creditCardValidity = creditCardValidity;
    }

    // Existing methods
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public void setId(int user_id) {
        this.id = user_id;
    }
}
