package com.example.e2.handlers;

//class to handle calls to review table
import com.example.e2.models.Review;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbHandlerReview {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";

    private static final String SQLInsertReview = "INSERT INTO reviews (event_name, content, rating) VALUES (?, ?, ?)";
    private static final String SQLGetReviewsByEvent = "SELECT content, rating FROM reviews WHERE event_name = ?";

    // Method to add a review
    public static void addReview(Review review) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQLInsertReview)) {

            preparedStatement.setString(1, review.getEventName());
            preparedStatement.setString(2, review.getContent());
            preparedStatement.setInt(3, review.getRating()); // Set rating

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("An error occurred while inserting the review: " + e.getMessage());
        }
    }

    // Method to get all reviews by event name
    public static String[] getReviewsByEvent(String eventName) {
        ArrayList<String> reviewsList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQLGetReviewsByEvent)) {

            preparedStatement.setString(1, eventName);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String reviewContent = resultSet.getString("content");
                int rating = resultSet.getInt("rating");
                reviewsList.add("Review: " + reviewContent + " | Rating: " + rating);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving reviews: " + e.getMessage());
        }

        // Convert the ArrayList to a String array
        return reviewsList.toArray(new String[0]);
    }
}

