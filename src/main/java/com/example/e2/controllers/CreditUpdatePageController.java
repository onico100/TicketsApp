package com.example.e2.controllers;
//class to handle when user want to update credit card-ui
import com.example.e2.HomePage;
import com.example.e2.handlers.DbHandlerUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreditUpdatePageController {

    @FXML
    private TextField txtCreditCardOwnerId;

    @FXML
    private TextField txtCreditCardNumber;

    @FXML
    private TextField txtCreditCardCVC;

    @FXML
    private TextField txtCreditCardValidity;

    @FXML
    private Label lblMessage;

    @FXML
    public void onUpdateCreditCard(ActionEvent event) {
        try {
            // Validate and parse inputs
            String creditCardOwnerId = txtCreditCardOwnerId.getText();
            String creditCardNumber = txtCreditCardNumber.getText();
            int creditCardCVC = Integer.parseInt(txtCreditCardCVC.getText());
            String creditCardValidity = txtCreditCardValidity.getText();

            // Input validation
            if (creditCardValidity.isEmpty() || !creditCardValidity.matches("\\d{2}/\\d{2}")) {
                lblMessage.setText("Invalid validity format. Use MM/YY.");
                return;
            }
            if(creditCardNumber.length() != 16) {
                lblMessage.setText("Invalid credit car number. Use 16 digits.");
                return;
            }
            if(creditCardCVC<100 || creditCardCVC>1000) {
                lblMessage.setText("Invalid credit cvc. Use 3 digits.");
                return;
            }
            if(creditCardOwnerId.length()!=9) {
                lblMessage.setText("Invalid id. Use 9 digits.");
                return;
            }

            // Set new values for the user's credit card
            HomePageController.user.setCreditCardOwnerId(creditCardOwnerId);
            HomePageController.user.setCreditCardNumber(creditCardNumber);
            HomePageController.user.setCreditCardCVC(creditCardCVC);
            HomePageController.user.setCreditCardValidity(creditCardValidity);

            // Update in the database
            DbHandlerUser.updateCreditCardDetails(HomePageController.user);

            lblMessage.setText("Credit card details updated successfully!");
        } catch (NumberFormatException e) {
            lblMessage.setText("Invalid input. Please check the fields.");
        }
    }

    public void goToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }
}
