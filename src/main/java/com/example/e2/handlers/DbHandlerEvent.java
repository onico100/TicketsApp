package com.example.e2.handlers;

//class to handle calls to the event table
import com.example.e2.models.Event;

import java.sql.*;
import java.util.ArrayList;


public  class DbHandlerEvent {
    private static final String
            DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";

    //add new event
    public static int insertEvent(Event event)
    {
        String SQLEvent = "INSERT INTO events (event_name,event_place,event_type,event_date,isSigne,user_id,numRow,cost) VALUES (?, ?,?,?,?,?,?,?)";

        int generatedId=-1;//if can't insert

        try(
                Connection connection = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(SQLEvent,Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, event.getName());
            preparedStatement.setString(2,event.getPlace());
            preparedStatement.setString(3,event.getType());
            Date sqlDate=Date.valueOf( event.getDate());
            preparedStatement.setDate(4,sqlDate );
            preparedStatement.setBoolean(5, event.isSigned());
            preparedStatement.setInt(6,event.getUser_id());
            preparedStatement.setInt(7,event.getNumberOfSeats());
            preparedStatement.setInt(8,event.getCost());

            //preparedStatement.SignedPlaces
            int a=preparedStatement.executeUpdate();
            if(a>0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        }
        catch (SQLException e)
        {
            System.out.println("an error in the values "+e.getMessage());
        }
        return generatedId;
    }


    //get all the event names in the database-for the home page
    public static String[] getEventsNames(){
        String[] events=new String[11];
        String serch="select distinct event_name from events";
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(serch)) {
            for(int i=0;i<12 && resultSet.next();i++) {
                events[i] = resultSet.getString(1);
                System.out.println(events[i]);
            }


        } catch (SQLException e) {
            System.out.println("an error in the values" + e.getMessage());
        }
        return events;
    }


    //to help search button
    public static boolean isExist(String eventName) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM events WHERE event_name = ?";

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set the event name parameter
            preparedStatement.setString(1, eventName);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check the result
            if (resultSet.next()) {
                exists = resultSet.getInt(1) > 0; // If count > 0, the event exists
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return exists;
    }


    //get a list of specific events from event name
    public static ArrayList<Event> getEvents(String eventName) {
        String query = "SELECT * FROM events WHERE event_name = ? AND event_date > CURDATE()";
        ArrayList<Event> eventsList = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, eventName);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the results
            while (resultSet.next()) {
                // Create an Event object from the result set
                Event event = new Event(
                        resultSet.getInt("event_id"),           // Assuming you have event_id column
                        resultSet.getString("event_name"),
                        resultSet.getString("event_place"),
                        resultSet.getString("event_type"),
                        resultSet.getDate("event_date").toLocalDate(),
                        resultSet.getBoolean("isSigne"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("numRow"),
                        resultSet.getInt("cost")
                );

                // Add the event to the list
                eventsList.add(event);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        // Convert the ArrayList to an array and return it
        return eventsList;

    }


    //get the list of events the user created
    public static ArrayList<Event> getEventsByUserId(int userId) {
        String query = "SELECT * FROM events WHERE user_id = ?";
        ArrayList<Event> eventsList = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Loop through the results
            while (resultSet.next()) {
                // Create an Event object from the result set
                Event event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("event_place"),
                        resultSet.getString("event_type"),
                        resultSet.getDate("event_date").toLocalDate(),
                        resultSet.getBoolean("isSigne"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("numRow"),
                        resultSet.getInt("cost")
                );

                // Add the event to the list
                eventsList.add(event);
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return eventsList;
    }


    //update number of seats in case of buy ticket return the new
    public static int decreaseNumberOfSeats(int eventId) {
        String updateQuery = "UPDATE events SET numRow = numRow - 1 WHERE event_id = ? AND numRow > 0";
        String selectQuery = "SELECT numRow FROM events WHERE event_id = ?";
        int newNumberOfSeats = -1;

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {

            // Set the event id parameter
            updateStatement.setInt(1, eventId);

            // Execute the update (decrease the seats by 1)
            int rowsAffected = updateStatement.executeUpdate();

            if (rowsAffected > 0) {
                // If the update was successful, fetch the new number of seats
                selectStatement.setInt(1, eventId);
                ResultSet resultSet = selectStatement.executeQuery();

                if (resultSet.next()) {
                    newNumberOfSeats = resultSet.getInt("numRow");
                }

                resultSet.close();
            }

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return newNumberOfSeats;
    }


    //get specific event by event id
    public static Event getEventById(int eventId) {
        String query = "SELECT * FROM events WHERE event_id = ?";
        Event event = null; // This will hold the event if found

        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, eventId);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("");

            // Check if an event is found
            if (resultSet.next()) {
                // Create an Event object from the result set
                event = new Event(
                        resultSet.getInt("event_id"),
                        resultSet.getString("event_name"),
                        resultSet.getString("event_place"),
                        resultSet.getString("event_type"),
                        resultSet.getDate("event_date").toLocalDate(),
                        resultSet.getBoolean("isSigne"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("numRow"),
                        resultSet.getInt("cost")
                );
            }

            resultSet.close();

        } catch (SQLException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return event; // Return the found event or null if not found
    }



}
