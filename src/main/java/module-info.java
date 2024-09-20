module com.example.e2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.e2 to javafx.fxml;
    exports com.example.e2;
    exports com.example.e2.handlers;
    opens com.example.e2.handlers to javafx.fxml;
    exports com.example.e2.models;
    opens com.example.e2.models to javafx.fxml;
    exports com.example.e2.controllers;
    opens com.example.e2.controllers to javafx.fxml;
}