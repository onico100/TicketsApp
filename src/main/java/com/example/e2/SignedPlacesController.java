package com.example.e2;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignedPlacesController {
    public Button btnAddMoreRows;
    public TextField txtFromRow;
    public TextField txtToRow;
    public TextField txtNumberOfSeatsInRow;
    public Button finish;
    private int[][] seatsMat;
    private int fromRow=0;

    public int[][] getSeatsMat() {
        return seatsMat;
    }

    public void setSeatsMat(int[][] seatsMat) {
        this.seatsMat = seatsMat;
    }
    public void initialize() {
        seatsMat=new int[SignedPlaces.numberOfRows][];
    }

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
        for (int i=fromRow;i<=toRow;i++)
            seatsMat[i]=new int[numberOfSeats];
        SignedPlaces.finish();
    }
}
