package com.example.e2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignedPlaces extends Application {
    private static Stage signedPlacesParmryStage;
    public static int numberOfRows;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("SignedPlaces.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        signedPlacesParmryStage=stage;
        signedPlacesParmryStage.setTitle("SignedPlaces");
        signedPlacesParmryStage.setScene(scene);
        signedPlacesParmryStage.show();
        System.out.println("");

    }
    public static void finish(){
        signedPlacesParmryStage.close();
    }

    public static void setNumberOfRows(int numberOfRows) {
        SignedPlaces.numberOfRows = numberOfRows;
    }
}
