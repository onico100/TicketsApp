package com.example.e2.handlers;

//class to handle calls to user table
import com.example.e2.models.User;

import java.sql.*;

public  class DbHandlerUser {
    private static final String
            DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";






    //insert new user
    public static void insertUser(User user) {
        String SQLUser = "INSERT INTO users (userName, password, credit_card_owner_id, credit_card_num, credit_card_cvc, credit_card_validity) VALUES (?,?,?,?,?,?)";

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQLUser)
        ) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getCreditCardOwnerId());
            preparedStatement.setString(4, user.getCreditCardNumber());
            preparedStatement.setInt(5, user.getCreditCardCVC());
            preparedStatement.setString(6, user.getCreditCardValidity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while inserting user: " + e.getMessage());
        }
    }

    //login to existing user-get his id
    public static int login(String username,String password) {
       char c='"';
        String serch = "SELECT user_id,password FROM users WHERE userName="+c+username+c;
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(serch)) {
            resultSet.next();
            int check = resultSet.getInt(2);
            int pass=Integer.parseInt(password);
            if (pass!= check)
                return 0;
            else
                return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println("an error in the values" + e.getMessage());
        }
        return 0;
    }


    //update credit card details
    public static void updateCreditCardDetails(User user) {
        String SQLUpdateCard = "UPDATE users SET credit_card_owner_id = ?, credit_card_num = ?, credit_card_cvc = ?, credit_card_validity = ? WHERE user_id = ?";

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdateCard)
        ) {
            preparedStatement.setString(1, user.getCreditCardOwnerId());
            preparedStatement.setString(2, user.getCreditCardNumber());
            preparedStatement.setInt(3, user.getCreditCardCVC());
            preparedStatement.setString(4, user.getCreditCardValidity());
            preparedStatement.setInt(5, user.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Credit card details updated successfully.");
            } else {
                System.out.println("Failed to update credit card details.");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while updating credit card details: " + e.getMessage());
        }
    }





}
