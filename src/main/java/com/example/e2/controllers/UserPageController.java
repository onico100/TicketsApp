package com.example.e2.controllers;
//class to show user data-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DBHandlerOrder;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.models.Event;
import com.example.e2.models.Order;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.util.ArrayList;

public class UserPageController {
    public Label txtOrder;
    public VBox OrdersList;
    public VBox EventsList;
    public Label txtEvent;
    public Text txtUserName;


    public void initialize() throws IOException {
        System.out.println("hello");
        txtUserName.setText("HELLO "+HomePageController.user.getUserName());


        //all the events that the user creates
        ArrayList<Event> events = DbHandlerEvent.getEventsByUserId(HomePageController.user.getId());
        if (events.isEmpty())
            txtEvent.setText("no events found");
        else
           EventsList.getChildren().remove(txtEvent);
        for (Event value : events) {
            Label txtTemp = new Label("");
            txtTemp.setTextAlignment(TextAlignment.CENTER);
            txtTemp.setTextFill(Paint.valueOf("#2c0596"));
            txtTemp.setPrefSize(555, 40);
            txtTemp.setText(value.getName() + " date:" + value.getDate());
            Event event = value;
            txtTemp.setOnMouseClicked(e -> {
                try {
                    goToEventPage(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            EventsList.getChildren().add(txtTemp);
        }

        //all his orders
        Order[] orders= DBHandlerOrder.getOrdersByUserId(HomePageController.user.getId());
        if (orders.length==0)
            txtOrder.setText("no orders found");
        else
            OrdersList.getChildren().remove(txtOrder);
        for (Order value : orders) {
            Label txtTemp = new Label("");
            txtTemp.setTextAlignment(TextAlignment.CENTER);
            txtTemp.setTextFill(Paint.valueOf("#2c0596"));
            txtTemp.setPrefSize(555, 40);
            Event event=DbHandlerEvent.getEventById(value.getEvent_id());
            txtTemp.setText("event name: "+event.getName() + " date: " + event.getDate());
            if(event.isSigned())
                txtTemp.setText("event name:"+event.getName() + " date:" + event.getDate()+" seat row:"+value.getSeatRow()+" seat number:"+value.getSeatNum());
            txtTemp.setOnMouseClicked(e -> {
                try {
                    goToEventPage(event);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            OrdersList.getChildren().add(txtTemp);
        }
    }

    public void goToEventPage(Event event) throws IOException {
        HomePage.moveToEventPage(event.getName());
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }


    public void gotoaUpdatePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToUpdatePage();
    }

    public void onSineInButton(MouseEvent mouseEvent) throws IOException {
        HomePage.moveToSignIn();
    }
}
