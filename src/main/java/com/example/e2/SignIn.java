package com.example.e2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class SignIn extends Application {

    private static Stage signInParmryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        signInParmryStage=stage;
        signInParmryStage.setTitle("SignedPlaces");
        signInParmryStage.setScene(scene);
        signInParmryStage.show();
    }
    public static void finish(){
        signInParmryStage.close();
    }
}
