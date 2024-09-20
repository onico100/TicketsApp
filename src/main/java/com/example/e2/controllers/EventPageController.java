package com.example.e2.controllers;
//class to show event -ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.handlers.DbHandlerReview;
import com.example.e2.models.Event;
import com.example.e2.models.Review;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;

public class EventPageController {
    public static String eventName;
    public ImageView eventImage = new ImageView();
    public GridPane eventsBoard;

    public AnchorPane all;
    public Text txt;

    public VBox ReviewList;
    public Text txtReview;
    public TextField reviewCo;
    public TextField reviewRa;
    public Button bthHelloUser;

    public void initialize() throws IOException {
        System.out.println("hello");

        bthHelloUser.setText("hello "+HomePageController.user.getUserName());
        txt.setText(eventName);
        Image image = new Image("C:\\Users\\אודיה\\IdeaProjects\\e2\\src\\main\\resources\\com\\example\\e2\\EventsPhotos\\"+eventName+".jpg");
        eventImage.setImage(image);
        Rectangle rec = new Rectangle(5, 5);

        ArrayList<Event> events = DbHandlerEvent.getEvents(eventName);

        // Clear the GridPane to handle dynamic event sizes
        eventsBoard.getChildren().clear();
        eventsBoard.getRowConstraints().clear();
        eventsBoard.getColumnConstraints().clear();

        // Define the size of each "cube"
        int numCols = 2;
        int numRows = (int) Math.ceil((double) events.size() / numCols);

        // Resize the grid based on the number of events
        for (int i = 0; i < numCols; i++) {
            eventsBoard.getColumnConstraints().add(new ColumnConstraints(150));  // Width for each column (adjust as needed)
        }
        for (int i = 0; i < numRows; i++) {
            eventsBoard.getRowConstraints().add(new RowConstraints(100));  // Height for each row (adjust as needed)
        }

        // Add event details to the grid
        for (int i = 0; i < events.size(); i++) {
             rec = new Rectangle(150, 100);  // Adjust size to match the grid cell size
            rec.setFill(Color.web("#1eb2d9"));  // Set the fill color to #1eb2d9
            rec.setStroke(Color.BLACK);         // Set the stroke (border) color to black
            rec.setStrokeWidth(2);
            Label eventLabel = new Label(events.get(i).getDate() + "\n" + events.get(i).getPlace());
            eventLabel.setTextFill(Color.WHITE); // Set text color to white
            eventLabel.setStyle("-fx-font-size: 14px;"); // Set the font size
            eventLabel.setAlignment(Pos.CENTER); // Center the text within the label
            eventLabel.setMaxWidth(Double.MAX_VALUE);  // Allow label to take the full width of the grid cell
            eventLabel.setMaxHeight(Double.MAX_VALUE); // Allow label to take the full height of the grid cell
            GridPane.setHalignment(eventLabel, HPos.CENTER); // Horizontally align the label to the center
            GridPane.setValignment(eventLabel, VPos.CENTER); // Vertically align the label to the center

            Event event = events.get(i);
            eventLabel.setOnMouseClicked(e -> {
                try {
                    getBuyPage(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            // Add the rectangle and the label to the grid
            eventsBoard.add(rec, i % numCols, i / numCols);  // Add rectangle to the grid
            eventsBoard.add(eventLabel, i % numCols, i / numCols);  // Add label to the grid
        }

        String[] reviews= DbHandlerReview.getReviewsByEvent(eventName);
        if (reviews.length==0)
            txtReview.setText("no reviews found");
        else
            ReviewList.getChildren().remove(txtReview);
        for (String value : reviews) {
            Label txtTemp = new Label("");
            txtTemp.setTextAlignment(TextAlignment.CENTER);
            txtTemp.setTextFill(Paint.valueOf("#2c0596"));
            txtTemp.setPrefSize(555, 40);
            txtTemp.setText(value);
           ReviewList.getChildren().add(txtTemp);
        }
    }

   //in case user want to go to a specific event to buy a ticket
    private void getBuyPage(Event event) throws IOException {
        if (HomePageController.user==null)
            HomePage.moveToSignIn();
        HomePage.moveToEventBuyPage(event);
    }

    public void goToUserPage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToUserPage();
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
        System.out.println("");
    }

    public void addReview(ActionEvent actionEvent) {
        int rating=Integer.parseInt(reviewRa.getText());
        if(rating<1 || rating>5) {
            reviewCo.setText("number between 1 to 5");
            return;
        }
        String con= reviewCo.getText();
        DbHandlerReview.addReview(new Review(eventName,con,rating));

    }

    public void onSineInButton(MouseEvent mouseEvent) throws IOException {
        HomePage.moveToSignIn();
    }
}

