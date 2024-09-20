package com.example.e2;
//navigation class
import com.example.e2.controllers.EventBuyPage;
import com.example.e2.controllers.EventPageController;
import com.example.e2.models.Event;
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

        HomePageParmryStage=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePage.fxml"));
        HomePageParmryStage.setTitle("Ticets");
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

    public static void moveToEventBuyPage(Event event) throws IOException {
       EventBuyPage.event=event;
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("EventBuyPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
        EventBuyPage.event=event;
    }

    public static void moveToUserPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("UserPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

    public static void moveToSignedPlaceBuyPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("SignedPlaceBuy.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

    public static void moveToUpdatePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("creditUpdatePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();

    }




    public static void moveToAddEvent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("AddEvent.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }
    public static void moveToSignIn() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("SignIn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

    public static void moveToHomePageUser() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("HomePageUser.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
    }

    public static void moveToEventPage(String eventName) throws IOException {
        EventPageController.eventName=eventName;
        FXMLLoader fxmlLoader = new FXMLLoader(HomePage.class.getResource("EventPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        HomePageParmryStage.setScene(scene);
        HomePageParmryStage.show();
        EventPageController.eventName=eventName;

    }

}
