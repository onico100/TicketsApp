package com.example.e2;

//import java.sql.Connection;
import java.io.File;
import java.sql.*;
//import java.sql.DriverManaager;
//import java.sql.PreparedStement;
//import java.sql.SQLException;

public  class DbHandler {
    private static final String
            DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";

    private static final String SQLEvent = "INSERT INTO events (event_name,event_place,event_type,event_date,isSigned,evevnt_Signed_places) VALUES (?, ?,?,?,?,?)";
    private static final String SQLUser = "INSERT INTO users (userName,password) VALUES (?,?)";


    public static void insertEvent(Event event)
    {

        try(
          Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATABASE_PASSWORD);
          PreparedStatement preparedStatement = connection.prepareStatement(SQLEvent)) {

            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2,event.getPlace());
            preparedStatement.setString(3,event.getType()
            );
            preparedStatement.setDate(4,event.getDate());
            preparedStatement.setBoolean(5, event.isSigned());

            //preparedStatement.SignedPlaces
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("an error in the values");
        }
    }
    public static void insertUser(User user)
    {

        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQLUser)) {

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2,user.getPassword());


            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("an error in the values"+e.getMessage());
        }
    }
    public static int login(String username,String password){
        String serch="SELECT password FROM users WHERE userName="+username;
        String sucssesSerch="SELECT user_id FROM users WHERE userName="+username;
        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(serch)) {
            password=preparedStatement.
            if(password!=check)
                return 0;
            else
              //return user_id


        }
        catch (SQLException e)
        {
            System.out.println("an error in the values"+e.getMessage());
        }
    }
    }
}
