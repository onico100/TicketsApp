package com.example.e2.handlers;
//class to handle calls to signed invites table
import com.example.e2.models.Event;

import java.sql.*;

public class DbHandlerSignedPlaces {
    private static final String
            DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";


    private static final String SQLAddSeat = "INSERT INTO signedֹֹinvites (event_id,row_num,seat_num) VALUES (?, ?,?)";


    //insert empty seat
    public static void insertSeat(Event event, int row, int seat)
    {

        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQLAddSeat)) {

            preparedStatement.setInt(1,event.getID());
            preparedStatement.setInt(2,row);
            preparedStatement.setInt(3,seat);


            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("an error in the values"+e.getMessage());
        }
    }

    //get seat matrix of specific event
    public static int[][] getSeatingMatrix(int eventId) {
        // Query to get the max row and max seat in a row for the event
        String SQLGetMaxRowAndSeat = "SELECT MAX(row_num) AS max_row, MAX(seat_num) AS max_seat FROM signedֹֹinvites WHERE event_id = ?";
        String SQLGetSeats = "SELECT row_num, seat_num, is_taken FROM signedֹֹinvites WHERE event_id = ?";

        int maxRow = 0;
        int maxSeat = 0;

        // Get the maximum row and seat number for the given event
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement maxPreparedStatement = connection.prepareStatement(SQLGetMaxRowAndSeat)) {

            maxPreparedStatement.setInt(1, eventId);
            ResultSet resultSet = maxPreparedStatement.executeQuery();

            if (resultSet.next()) {
                maxRow = resultSet.getInt("max_row");
                maxSeat = resultSet.getInt("max_seat");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving max row and seat data: " + e.getMessage());
            return new int[0][0]; // Return an empty array in case of failure
        }

        // Initialize seating matrix with -1 for non-existent seats
        int[][] seatingMatrix = new int[maxRow + 1][maxSeat + 1];
        for (int i = 0; i <= maxRow; i++) {
            for (int j = 0; j <= maxSeat; j++) {
                seatingMatrix[i][j] = -1; // Mark all as non-existent (-1)
            }
        }

        // Populate seating matrix based on seat status (is_taken)
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement seatPreparedStatement = connection.prepareStatement(SQLGetSeats)) {

            seatPreparedStatement.setInt(1, eventId);
            ResultSet resultSet = seatPreparedStatement.executeQuery();

            while (resultSet.next()) {
                int row = resultSet.getInt("row_num");
                int seat = resultSet.getInt("seat_num");
                int isTaken = resultSet.getInt("is_taken");

                // Mark the seat as taken (1) or available (0) based on the is_taken column
                seatingMatrix[row][seat] = isTaken == 1 ? 1 : 0;
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving seating data: " + e.getMessage());
        }

        return seatingMatrix;
    }


    // Method to get the total number of seats for a specific event by event_id
    public static int getAvailableSeatsForEvent(int eventId) {
        int availableSeats = 0;
        String SQLGetAvailableSeatCountByEvent = "SELECT COUNT(*) AS available_seats FROM signedֹֹinvites WHERE event_id = ? AND is_taken = 0";

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQLGetAvailableSeatCountByEvent)) {

            preparedStatement.setInt(1, eventId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                availableSeats = resultSet.getInt("available_seats");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving available seat count: " + e.getMessage());
        }

        return availableSeats;
    }

    // Method to mark a seat as taken by event_id, row_num, and seat_num
    public static void markSeatAsTaken(int eventId, int rowNum, int seatNum) {
         String SQL_UPDATE_SEAT_STATUS =
                "UPDATE signedֹֹinvites SET is_taken = 1 WHERE event_id = ? AND row_num = ? AND seat_num = ?";



        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SEAT_STATUS)
        ) {
            // Set the parameters for the SQL query
            preparedStatement.setInt(1, eventId);
            preparedStatement.setInt(2, rowNum);
            preparedStatement.setInt(3, seatNum);

            // Execute the update query
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Seat at row " + rowNum + " and seat number " + seatNum + " for event " + eventId + " has been marked as taken.");
            } else {
                System.out.println("Seat not found or already taken.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating seat status: " + e.getMessage());
        }
    }
}


