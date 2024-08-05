package com.example.e2;

import com.sun.javafx.scene.control.IntegerField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;


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
    @FXML
    private DatePicker datePickerEventDate;


    private Event event;
    private boolean isSelectedDate = false;



//    public Button rec;
//    public Button tri;
//    public VBox all;
//    public Button ex;
//
//    private Button restart= new Button("restart");
//
//    private Button drawTriangular;
//    private Button fScope;
//
//
//
//    @FXML
//    private Label chooseOption;
//
//    private TextField length;
//
//    private TextField wide;
//    private static int len,wid;

    @FXML
    protected void close() {
        System.exit(0);
    }


    @FXML
    public void initialize() {
        lblEventType.setItems(FXCollections.observableArrayList("movie", "play"));
         event = new Event();
    }

    public void onAddEventButtonClicked(ActionEvent actionEvent) {
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
        //Date date=datePickerEventDate
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
        else
            event.setName(name);
    }


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
            File saveLocation = new File("C:\\Users\\אודיה\\IdeaProjects\\e2\\src\\main\\resources\\com\\example\\e2\\EventsPhotos" + EventName);

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
        HomePage.moveToHomePage();
    }
}
/*<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.CheckBox?>
<?import javafx.scene.control.ComboBox?>
<?import javafx.scene.control.DatePicker?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.VBox?>
<VBox fx:id="all" alignment="CENTER" prefHeight="412.0" prefWidth="348.0" spacing="20.0" xmlns="http://javafx.com/javafx/21" xmlns:fx="http://javafx.com/fxml/1" fx:controller="com.example.e2.AddEventController">
    <padding>
        <Insets bottom="20.0" left="20.0" right="20.0" top="20.0" />
    </padding>
    <TextField fx:id="txtEventName" promptText="Enter event name" />
    <ComboBox fx:id="lblEventType" onAction="#onSelectType" promptText="Select event type" />
    <TextField fx:id="txtEventPlace" promptText="Enter event place" />
    <DatePicker fx:id="datePickerEventDate" onAction="#onChooseDate" promptText="choose date" />
    <CheckBox fx:id="checkboxIsSignedPlaces" onAction="#ifSignedPlaces" text="Signed Places" />
    <TextField fx:id="NumberOfPlaces" promptText="enter number of seats" />
   <Button fx:id="btnAddPhoto" mnemonicParsing="false" onAction="#onAddPhoto" text="Add Photo" textFill="#040114" />
    <Button fx:id="btnAddEvent" onAction="#onAddEventButtonClicked" text="Add Event" />
    <Label fx:id="txtErrorMessage" textFill="#c91212" />
</VBox>*/
