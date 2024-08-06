package com.example.e2;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SignInController {
    public TextField txtUserName;
    public TextField txtUserPassword;
    public Button bthSignUp;
    public Button bthlogin;
    public Label tetEroorMesagge;

    public void onSignUP(ActionEvent actionEvent) {
        String username=txtUserName.getText();
        String userPassword=txtUserPassword.getText();
        if(username.isEmpty())
            tetEroorMesagge.setText("please enter username");
        if(username.length()<4)
            tetEroorMesagge.setText("username must have at least 4 letters");
        if(userPassword.isEmpty())
            tetEroorMesagge.setText("please enter password");
        if(userPassword.length()<4)
            tetEroorMesagge.setText("password must have at least 4 letters");
        User user=new User(username,userPassword);
        DbHandler.insertUser(user);



        SignIn.finish();
    }

    public void onLoginClicked(ActionEvent actionEvent) {
        String username=txtUserName.getText();
        String userPassword=txtUserPassword.getText();
        if(username.isEmpty())
            tetEroorMesagge.setText("please enter username");
        if(username.length()<4)
            tetEroorMesagge.setText("username must have at least 4 letters");
        if(userPassword.isEmpty())
            tetEroorMesagge.setText("please enter password");
        if(userPassword.length()<4)
            tetEroorMesagge.setText("password must have at least 4 letters");
        int check=DbHandler.login(username,userPassword);
        if(check==0)
            tetEroorMesagge.setText("username or password are wrong");
        else
            //sethellobuton

        SignIn.finish();
    }
}
