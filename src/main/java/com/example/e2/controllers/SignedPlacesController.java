package com.example.e2.controllers;
//class to add signed places to an event-ui
import com.example.e2.HomePage;
import com.example.e2.SignedPlaces;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignedPlacesController {
    public Button btnAddMoreRows;
    public TextField txtFromRow;
    public TextField txtToRow;
    public TextField txtNumberOfSeatsInRow;
    public Button finish;
    private static int[][] seatsMat;
    private int fromRow=0;

    public static int[][] getSeatsMat() {
        return seatsMat;
    }


    public void initialize() {
        seatsMat=new int[SignedPlaces.numberOfRows][];
    }

    //keep the rows the user give and let him add more
    public void onAddMoreRows(ActionEvent actionEvent) {
        int toRow=Integer.parseInt(txtToRow.getText());
        int numberOfSeats=Integer.parseInt(txtNumberOfSeatsInRow.getText());
        for (int i=fromRow;i<=toRow;i++)
            seatsMat[i]=new int[numberOfSeats];
        fromRow=toRow+1;
        txtFromRow.setText(String.valueOf(fromRow));
        txtToRow.setText("");
        txtNumberOfSeatsInRow.setText("");
    }

    public void finish(ActionEvent actionEvent) {
        int toRow=Integer.parseInt(txtToRow.getText());
        int numberOfSeats=Integer.parseInt(txtNumberOfSeatsInRow.getText());
        for (int i=fromRow;i<toRow;i++)
            seatsMat[i]=new int[numberOfSeats];
        SignedPlaces.finish();

    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }
}
