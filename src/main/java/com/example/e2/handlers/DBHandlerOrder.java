package com.example.e2.handlers;
//class to handle calls to order table
import com.example.e2.models.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHandlerOrder {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tickets_db";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "password";

    // Method to get connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
    }

    // Method to insert unsigned orders
    public static void insertUnsignedOrder(Order order) {
        String query = "INSERT INTO orders (user_id, event_id) VALUES (?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setInt(2, order.getEvent_id());

            preparedStatement.executeUpdate();
            System.out.println("Unsigned order inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to insert signed orders
    public static void insertSignedOrder(Order order) {
        String query = "INSERT INTO orders (user_id, event_id, seat_sign, seat_row, seat_num) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, order.getUser_id());
            preparedStatement.setInt(2, order.getEvent_id());
            preparedStatement.setBoolean(3, order.isSign());
            preparedStatement.setInt(4, order.getSeatRow());
            preparedStatement.setInt(5, order.getSeatNum());

            preparedStatement.executeUpdate();
            System.out.println("Signed order inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all orders by user_id and return as an Order array
    public static Order[] getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int event_id = resultSet.getInt("event_id");
                boolean seatSign = resultSet.getBoolean("seat_sign");
                int seatRow = resultSet.getInt("seat_row");
                int seatNum = resultSet.getInt("seat_num");

                // Check if it's a signed order (seatSign == true)
                Order order;
                if (seatSign) {
                    order = new Order(userId, event_id, seatRow, seatNum);
                } else {
                    order = new Order(userId, event_id);
                }

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert List<Order> to Order[] array
        return orders.toArray(new Order[0]); // Returns an array of Orders
    }
}

