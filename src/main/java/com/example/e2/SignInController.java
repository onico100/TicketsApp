package com.example.e2;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class SignInController {
    public TextField txtUserName;
    public TextField txtUserPassword;
    public Button bthSignIn;
    public Button bthlogin;

    public void onSignIn(ActionEvent actionEvent) {
        String username=txtUserName.getText();
        String userPassword=txtUserPassword.getText();
        User user=new User(username,userPassword);
        HomePageController.setHelloButton("hello"+username);
        //add user to db
        //change label
        SignIn.finish();
    }

    public void onLoginClicked(ActionEvent actionEvent) {
        //serch user
        SignIn.finish();
    }
}
