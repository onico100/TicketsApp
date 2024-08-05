package com.example.e2;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    public  Button btnSinIn;
    public Button btnAddEventHomePage;
    public  Canvas homePageCnvas;
    public Button btnSerch;
    public TextField txtSerchFiled;
    public static Label lblhelloUser=new Label();

    public void onSineInButton(ActionEvent actionEvent) throws IOException {
        SignIn x=new SignIn();
        Stage stage=new Stage();
        x.start(stage);
    }

    public void onbtnAddEventHomePage(ActionEvent actionEvent) throws IOException {

        HomePage.moveToAddEvent();


    }

    public void onSerchButton(ActionEvent actionEvent) {
    }

    public void onUserName(MouseEvent mouseEvent) {
        lblhelloUser.setText("helllllllllllo");
    }
      public static void setHelloButton(String text){

        lblhelloUser.setText(text);
      }
}
