package com.example.e2.controllers;
//class to buy ticket t specific event-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DBHandlerOrder;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.handlers.DbHandlerSignedPlaces;
import com.example.e2.models.Event;
import com.example.e2.models.Order;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class EventBuyPage {

    public static Event event;
    public AnchorPane all;
    public ImageView eventImage;
    public Text txtEventName;
    public Text txtEventDate;
    public Text txtSeatsLeft;
    public TextField NumberOFTickets;

    public Button bthHelloUser;
    public Text txtBuy;


    public void initialize() throws IOException {
        bthHelloUser.setText("hello "+HomePageController.user.getUserName());
        System.out.println("hello");
        txtEventName.setText(event.getName());
        Image image = new Image("C:\\Users\\אודיה\\IdeaProjects\\e2\\src\\main\\resources\\com\\example\\e2\\EventsPhotos\\"+event.getName()+".jpg");
        eventImage.setImage(image);
        txtEventDate.setText(event.getType()+"\n"+event.getDate()+""+"\n"+event.getPlace()+"\ncost:"+event.getCost());
        txtSeatsLeft.setText("seats left:"+event.getNumberOfSeats());
        if (event.isSigned())
            txtSeatsLeft.setText("seats left:"+ DbHandlerSignedPlaces.getAvailableSeatsForEvent(event.getID()) );
        }

   //function to update the buying in the needed tables
    public void buyTicket(ActionEvent actionEvent) throws IOException {
        int numberOfTickets;
        if(!event.isSigned()){
            if(NumberOFTickets.getText().isEmpty())
                numberOfTickets=Integer.parseInt(NumberOFTickets.getPromptText());
            else
                numberOfTickets=Integer.parseInt(NumberOFTickets.getText());
            for(int i=0;i<numberOfTickets;i++) {
                Order order = new Order(HomePageController.user.getId(), event.getID());
                DBHandlerOrder.insertUnsignedOrder(order);
                DbHandlerEvent.decreaseNumberOfSeats(event.getID());
                txtBuy.setText("the ticket is yours!");
            }
    }
        else {
            SignedPlacesBuy();
            txtBuy.setText("the ticket is yours!");
        }
    }

   //open a sense to collect seat data
    private void SignedPlacesBuy() throws IOException {
        HomePage.moveToSignedPlaceBuyPage();
        System.out.println("");
    }

    public void goToUserPage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }

    public void onSineInButton(MouseEvent mouseEvent) throws IOException {
        HomePage.moveToSignIn();
    }
}
