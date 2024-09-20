package com.example.e2.controllers;

//class to buy specific seat-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DBHandlerOrder;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.handlers.DbHandlerSignedPlaces;
import com.example.e2.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class SignedPlaceBuyController {

    @FXML
    private GridPane gridPane; // Reference to the grid where seats are shown

    public  static int[][] seatMatrix;


    @FXML
    public void initialize() {
        seatMatrix= DbHandlerSignedPlaces.getSeatingMatrix(EventBuyPage.event.getID());
        createSeatMatrix(seatMatrix);
        System.out.println("");
    }

    // Create seat matrix UI in the grid
    private void createSeatMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                Button seatButton = new Button(); // Create a button for each seat
                seatButton.setMinSize(50, 50);   // Set size for each button (seat)

                // Style the button based on whether it's taken or not
                if (matrix[row][col] != 0) {
                    seatButton.setStyle("-fx-background-color: gray;");
                    seatButton.setDisable(true); // Taken seats cannot be clicked
                } else {
                    seatButton.setStyle("-fx-background-color: white;");
                    int currentRow = row;
                    int currentCol = col;
                    // Set the on-click event to handle seat click with the current row and column
                    seatButton.setOnAction(e -> {
                        try {
                            handleSeatClick(currentRow, currentCol);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }

                // Add the button to the grid at the correct position
                gridPane.add(seatButton, col, row);
            }
        }
    }

    // Handle click on an available seat
    private void handleSeatClick(int row, int col) throws IOException {

        Order order=new Order(HomePageController.user.getId(), EventBuyPage.event.getID(),row,col);
        DBHandlerOrder.insertSignedOrder(order);
        DbHandlerSignedPlaces.markSeatAsTaken(EventBuyPage.event.getID(),row,col);
        DbHandlerEvent.decreaseNumberOfSeats(EventBuyPage.event.getID());
        HomePage.moveToEventBuyPage(EventBuyPage.event);
    }
}

