package com.example.e2.controllers;
//class to manage the home page-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.models.User;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;

public class HomePageController {

    public Button btnAddEventHomePage;
    public static User user;

    public  Canvas homePageCnvas;
    public Button btnSerch;
    public TextField txtSerchFiled;
    public AnchorPane all;
    public Button bthHello;
    public GridPane EventsBord;
    public Button bthSignIn;







    public void initialize() throws IOException {
        if (user != null) {
            bthHello.setText("Hello " + user.getUserName());
        }

        // Get the list of event names from the database
        String[] names = DbHandlerEvent.getEventsNames();
        int numEvents = names.length;

        // Determine the number of columns and rows dynamically
        int numColumns = (int) Math.ceil(Math.sqrt(numEvents));  // Aim for square-like grid
        int numRows = (int) Math.ceil((double) numEvents / numColumns);

        // Clear any previous content in the grid
        EventsBord.getChildren().clear();
        EventsBord.setVgap(10);  // Vertical gap between rows
        EventsBord.setHgap(10);  // Horizontal gap between columns

        // Create the grid dynamically based on the number of events
        for (int i = 0; i < numEvents && names[i] != null; i++) {
            // Load the event image from the file path
            Image eventImage = new Image("file:/C:/Users/אודיה/IdeaProjects/e2/src/main/resources/com/example/e2/EventsPhotos/" + names[i] + ".jpg");

            // Create an ImageView and bind it to the grid pane cell's size
            ImageView imageView = new ImageView(eventImage);
            imageView.setPreserveRatio(true);  // Keep aspect ratio

            // Bind the image size dynamically based on grid pane size and number of columns/rows
            imageView.fitWidthProperty().bind(EventsBord.widthProperty().divide(numColumns));
            imageView.fitHeightProperty().bind(EventsBord.heightProperty().divide(numRows));

            // Create the event name label
            Label eventLabel = new Label(names[i]);
            eventLabel.setFont(new Font("Arial", 30));  // Adjusted font size for better fit
            eventLabel.setTextFill(Color.WHITE); // Set text color to white
            eventLabel.setStyle("-fx-font-size: 25px;"); // Set the font size (optional)
            eventLabel.setAlignment(Pos.CENTER); // Center the text within the label
            eventLabel.setMaxWidth(Double.MAX_VALUE);  // Allow label to take the full width of the grid cell
            eventLabel.setMaxHeight(Double.MAX_VALUE); // Allow label to take the full height of the grid cell
            GridPane.setHalignment(eventLabel, HPos.CENTER); // Horizontally align the label to the center
            GridPane.setValignment(eventLabel, VPos.CENTER); // Vertically align the label to the center


            // Add click event
            String eventName = names[i];  // Capture the event name for lambda expression
            eventLabel.setOnMouseClicked(e -> {
                try {
                    getEventPage(eventName);  // Open the event page
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });


            EventsBord.add(imageView, i % numColumns, i / numColumns);
            EventsBord.add(eventLabel,i % numColumns,i / numColumns);
        }

        Button btnAddEventHomePage = new Button("Add Event");
        btnAddEventHomePage.setOnAction(e-> {
            try {
                onbtnAddEventHomePage();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Add the add button to the end of the grid pane, spanning all columns
        GridPane.setColumnIndex(btnAddEventHomePage, 0);
        GridPane.setRowIndex(btnAddEventHomePage, numRows);
        GridPane.setColumnSpan(btnAddEventHomePage, numColumns);

        // Add the add button to the grid pane
        EventsBord.getChildren().add(btnAddEventHomePage);
    }


    //open sense of the required event
    private void getEventPage(String name) throws IOException {
        if (user==null)
            HomePage.moveToSignIn();
        HomePage.moveToEventPage(name);
    }


    //open sense of signup/login
    public void onSineInButton(ActionEvent actionEvent) throws IOException {
        HomePage.moveToSignIn();
    }


    public void onbtnAddEventHomePage() throws IOException {
        if(user==null)//only if login
            HomePage.moveToSignIn();
        else
        HomePage.moveToAddEvent();
    }

    public void onSerchButton(ActionEvent actionEvent) throws IOException {
        String eventName=txtSerchFiled.getText();
        if(DbHandlerEvent.isExist(eventName))
            getEventPage(eventName);
        else
            txtSerchFiled.setText("didnt found event");
        System.out.println("");
    }



    //open sense of the required user page
    public void goToUserPage() throws IOException {
        HomePage.moveToUserPage();
    }

    public void onSineInButton2(MouseEvent mouseEvent) throws IOException {
        HomePage.moveToSignIn();
    }
}
