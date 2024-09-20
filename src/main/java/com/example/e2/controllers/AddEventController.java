package com.example.e2.controllers;

//class to add event-ui
import com.example.e2.HomePage;
import com.example.e2.SignedPlaces;
import com.example.e2.controllers.HomePageController;
import com.example.e2.controllers.SignedPlacesController;
import com.example.e2.handlers.DbHandlerEvent;
import com.example.e2.handlers.DbHandlerSignedPlaces;
import com.example.e2.models.Event;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;


public class AddEventController {

    public TextField txtEventName;
    public TextField txtEventPlace;
    public CheckBox checkboxIsSignedPlaces;
    public ComboBox<String> lblEventType;
    public Button btnAddEvent;
    public Label txtErrorMessage;
    public TextField NumberOfPlaces;
    public Canvas all;
    public Button btnAddPhoto;
    public Button bthBackToHomePage;
    public TextField txtNumberOfRows;
    public TextField txtEventCost;
    @FXML
    private DatePicker datePickerEventDate;


    private Event event;
    private boolean isSelectedDate = false;








    @FXML
    public void initialize() {
        lblEventType.setItems(FXCollections.observableArrayList("movie", "play"));
         event = new Event();//empty event
        System.out.println("");
    }

    //do validation and add the event to the database if everything is fine
    public void onAddEventButtonClicked(ActionEvent actionEvent) {
        event.setUser_id(HomePageController.user.getId());
        if (!event.isSigned()) {
            if (NumberOfPlaces.getText().isEmpty())
                txtErrorMessage.setText("Plese enter number of seats");
            else {
                int intNumberOfPlaces = Integer.parseInt(NumberOfPlaces.getText());
                event.setNumberOfSeats(intNumberOfPlaces);
                if (intNumberOfPlaces < 1)
                    txtErrorMessage.setText("Please number of seats");
            }
        }
        if (!isSelectedDate)
            txtErrorMessage.setText("please choose a date");
      LocalDate date= datePickerEventDate.getValue();
        LocalDate today=LocalDate.now();
        if(date.isBefore(today))
            txtErrorMessage.setText("the date must be in the future");
        else {
            event.setDate(date);
        }
        if(txtEventCost.getText().isEmpty())
            txtErrorMessage.setText("please enter cost");
        else{
            int cost=Integer.parseInt(txtEventCost.getText());
            event.setCost(cost);
        }
        String place = txtEventPlace.getText();
        if (place.isEmpty())
            txtErrorMessage.setText("PLEASE ENTER A EVENT PLACE");
        else
            event.setPlace(place);
        if (event.getType() == null)
            txtErrorMessage.setText("please enter event type");
        String name = txtEventName.getText();
        if (name.isEmpty()) {
            txtErrorMessage.setText("Please enter event name");
        }
        else {
            event.setName(name);
            int eventId= DbHandlerEvent.insertEvent(event);
            txtErrorMessage.setText("added event!");
            event.setID(eventId);
            if(event.isSigned()){
                event.setSignedPlaces(SignedPlacesController.getSeatsMat());
                event.setNumberOfSeats(Integer.parseInt(txtNumberOfRows.getText()));
                setSignedPlaces();
            }
        }
    }
    //set the signed places into the database
    private void setSignedPlaces(){
        int[][] signedPlaces= event.getSignedPlaces();
        for(int i=0;i<signedPlaces.length;i++)
            for (int j=0;j<signedPlaces[i].length;j++)
                DbHandlerSignedPlaces.insertSeat(event,i,j);
        event.setNumberOfSeats(DbHandlerSignedPlaces.getAvailableSeatsForEvent(event.getID()));
    }


    //open sence that will get the signed places data from the user.
    public void ifSignedPlaces(ActionEvent actionEvent) throws IOException {
        event.setSigned(true);
        int  numberOfRows=Integer.parseInt(txtNumberOfRows.getText());
        SignedPlaces.setNumberOfRows(numberOfRows);
        SignedPlaces x=new SignedPlaces();
        Stage stage=new Stage();
        x.start(stage);
    }

    public void onSelectType(ActionEvent actionEvent) {
        event.setType(lblEventType.getSelectionModel().getSelectedItem());
    }

    public void onChooseDate(ActionEvent actionEvent) {
        this.isSelectedDate = true;
    }

    public void onAddPhoto(ActionEvent actionEvent) {
        String name = txtEventName.getText();
        if (name.isEmpty()) {
            txtErrorMessage.setText("Please enter event name");
        }
        event.setName(name);
        chooseAndSavePhoto(event.getName());

    }

    private void chooseAndSavePhoto(String EventName) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a Photo");

        // Set extension filter
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
                "Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        Stage stage = new Stage();

        // Show open file dialog
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Specify the location where you want to save the photo
            File saveLocation = new File("C:/Users/אודיה/IdeaProjects/e2/src/main/resources/com/example/e2/EventsPhotos/" + EventName+".jpg");

            try {
                // Copy the file to the specified location
                Files.copy(selectedFile.toPath(), saveLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Photo saved to: " + saveLocation.getAbsolutePath());
            } catch (IOException ex) {
                System.err.println("Failed to save the photo: " + ex.getMessage());
            }
        }
    }

    public void onBackToHomePage(ActionEvent actionEvent) throws IOException {
        HomePage.moveToHomePageUser();
    }
}

