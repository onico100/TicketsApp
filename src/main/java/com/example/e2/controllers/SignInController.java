package com.example.e2.controllers;
//class to sign up/login user-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DbHandlerUser;
import com.example.e2.models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController {
    public TextField txtUserName;
    public TextField txtUserPassword;
    public Button bthSignUp;
    public Button bthlogin;
    public Label tetEroorMesagge;
    public TextField txtCreditCardOwnerId;
    public TextField txtCreditCardNumber;
    public TextField txtCreditCardCVC;
    public TextField txtCreditCardValidity;
    public TextField txtUserName1;
    public TextField txtUserPassword1;

    public void onSignUP(ActionEvent actionEvent) throws IOException {
        String username = txtUserName1.getText();
        String userPassword = txtUserPassword1.getText();

        // Parsing numeric fields
        String creditCardOwnerId = txtCreditCardOwnerId.getText();
        String creditCardNumber = txtCreditCardNumber.getText();
        int creditCardCVC = Integer.parseInt(txtCreditCardCVC.getText());
        String creditCardValidity = txtCreditCardValidity.getText();

        if (username.isEmpty()) {
            tetEroorMesagge.setText("Please enter username");
            return;
        }
        if (username.length() < 4) {
            tetEroorMesagge.setText("Username must have at least 4 letters");
            return;
        }
        if (userPassword.isEmpty()) {
            tetEroorMesagge.setText("Please enter password");
            return;
        }
        if (userPassword.length() < 4) {
            tetEroorMesagge.setText("Password must have at least 4 letters");
            System.out.println("");
            return;
        }

        // Input validation
        if (creditCardValidity.isEmpty() || !creditCardValidity.matches("\\d{2}/\\d{2}")) {
            tetEroorMesagge.setText("Invalid validity format. Use MM/YY.");
            return;
        }
        if(creditCardNumber.length() != 16) {
            tetEroorMesagge.setText("Invalid credit car number. Use 16 digits.");
            return;
        }
        if(creditCardCVC<100 || creditCardCVC>1000) {
            tetEroorMesagge.setText("Invalid credit cvc. Use 3 digits.");
            return;
        }
        if(creditCardOwnerId.length()!=9) {
            tetEroorMesagge.setText("Invalid id. Use 9 digits.");
            return;
        }


        // Set user data
        HomePageController.user = new User(username, userPassword);
        HomePageController.user.setCreditCardOwnerId(creditCardOwnerId);
        HomePageController.user.setCreditCardNumber(creditCardNumber);
        HomePageController.user.setCreditCardCVC(creditCardCVC);
        HomePageController.user.setCreditCardValidity(creditCardValidity);

        // Insert user into the database
        DbHandlerUser.insertUser(HomePageController.user);

        // Log the user in
        int userID = DbHandlerUser.login(username, userPassword);
        HomePageController.user.setId(userID);
        HomePage.moveToHomePageUser();
    }



    public void onLoginClicked(ActionEvent actionEvent) throws IOException {
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
        int check= DbHandlerUser.login(username,userPassword);
        if(check==0)
            tetEroorMesagge.setText("username or password are wrong");
        else{
            HomePageController.user=new User(username,userPassword);
            HomePageController.user.setId(check);
            HomePage.moveToHomePageUser();

        }


    }
}
