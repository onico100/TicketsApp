package com.example.e2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePage extends Application {
    private static Stage HomePageParmryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage=stage;
        HomePageParmryStage.setTitle("Ticets");
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }
    public static void closeHomePage(){
        HomePageParmryStage.close();
    }

    public static void moveToAddEvent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("AddEvent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }
    public static void moveToHomePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

}
