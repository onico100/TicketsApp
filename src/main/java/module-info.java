module com.example.e2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.e2 to javafx.fxml;
    exports com.example.e2;
}